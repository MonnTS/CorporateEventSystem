package com.eventsystem.controller;

import com.eventsystem.entities.Event;
import com.eventsystem.entities.EventParticipants;
import com.eventsystem.entities.Login;
import com.eventsystem.service.EventParticipantsService;
import com.eventsystem.service.EventService;
import com.eventsystem.service.LoginService;
import com.eventsystem.service.QrCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class EventController {
    private final EventService eventService;
    private final EventParticipantsService eventParticipantsService;
    private final LoginService loginService;
    private final PasswordEncoder encoder;

    @Autowired
    public EventController(EventService eventService, EventParticipantsService eventParticipantsService,
                           LoginService loginService, PasswordEncoder encoder) {
        this.eventService = eventService;
        this.eventParticipantsService = eventParticipantsService;
        this.loginService = loginService;
        this.encoder = encoder;
    }

    @GetMapping("/events")
    public String eventsForm(ModelMap map) {
        List<Event> eventList = eventService.getEntities();
        map.addAttribute("events", eventList);
        return "events";
    }

    @GetMapping("/managerEvents")
    public String managerEventsForm(ModelMap map) {
        List<Event> eventList = eventService.getEntities();
        map.addAttribute("events", eventList);
        return "adminEvents";
    }

    @GetMapping("/createEvent")
    public ModelAndView createEventForm() {
        return new ModelAndView("createEvent", "command", new Event());
    }

    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute(name = "event") Event event) {
        MultipartFile pic = event.getFile();
        event.setPicture(eventService.encodeImageToBase64(pic));
        eventService.createEntity(event);
        return "redirect:/redirect";
    }

    @GetMapping("/updateEvent/{id}")
    public ModelAndView updateEventForm(@PathVariable Integer id, ModelMap model) {
        Optional<Event> tmp = eventService.getEntityById(id);
        Event event;
        if (tmp.isPresent()) {
            event = tmp.get();
            model.addAttribute("pic", event.getImage());
            return new ModelAndView("updateEvent", "command", event);
        }
        return new ModelAndView("redirect:/error");
    }

    @PostMapping("/updateEvent")
    public ModelAndView updateEvent(@ModelAttribute Event event) {
        MultipartFile file = event.getFile();

        if (file == null || file.getOriginalFilename().equals("")) {
            Optional<Event> tmp = eventService.getEntityById(event.getId());
            tmp.ifPresent(value -> event.setPicture(value.getPicture()));
            eventService.updateEntity(event);
            return new ModelAndView("redirect:/redirect");
        }

        event.setPicture(eventService.encodeImageToBase64(file));
        eventService.updateEntity(event);
        return new ModelAndView("redirect:/redirect");
    }

    @PostMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        Optional<Event> tmp = eventService.getEntityById(id);
        List<EventParticipants> lT = eventParticipantsService.getEntities();
        if (tmp.isPresent()) {
            Event event = tmp.get();
            for (var item :
                    lT) {
                if (Objects.equals(item.getEventId().getId(), event.getId())) {
                    eventParticipantsService.deleteEntity(item);
                }
            }
            eventService.deleteEntity(event);
            return "redirect:/redirect";
        }
        return "redirect:/error";
    }

    @GetMapping("/history")
    public String historyForm(ModelMap map) {
        Login loggedUser = loginService.getLoggedUser();
        List<Event> eventList = new ArrayList<>();
        List<EventParticipants> events = eventParticipantsService.getEntities()
                .stream()
                .filter(user -> Objects.equals(user.getPersonId().getId(), loggedUser.getId()))
                .toList();
        for (var item :
                events) {
            Event tmp = item.getEventId();
            eventList.add(tmp);
        }
        map.addAttribute("allParticipatedEvents", eventList);
        return "history";
    }

    @GetMapping("/history/{id}")
    public String participatedEventForm(@PathVariable Integer id, ModelMap map) {
        Optional<Event> tmp = eventService.getEntityById(id);
        if (tmp.isPresent()) {
            Event event = tmp.get();
            map.addAttribute("event", event);
            map.addAttribute("pic", event.getImage());
        }
        return "visitedEvent";
    }

    @GetMapping(value = "/generateQrCode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody BufferedImage generateCodeForEvent(@PathVariable Integer id) throws Exception {
        Login loggedUser = loginService.getLoggedUser();
        Optional<Event> evt = eventService.getEntityById(id);
        Event event = null;

        if (evt.isPresent())
            event = evt.get();

        EventParticipants eventParticipants = new EventParticipants(event, loggedUser);
        eventParticipantsService.createEntity(eventParticipants);
        String key = eventParticipantsService.createSecretKeyAccess(eventParticipants, loggedUser.getEmail());
        return QrCodeGeneratorService.generateQRCodeImage(key);
    }

    @GetMapping("/readQrCode")
    public String readQrCodeForm() {
        return "readCode";
    }

    @PostMapping("/readQrCode")
    public String readQrCode(@RequestParam(name = "code") String code) {
        List<EventParticipants> tmp = eventParticipantsService
                .getEntities()
                .stream()
                .filter(participant -> encoder.matches(code, participant.getKeyAccess()))
                .toList();

        if (!tmp.isEmpty()) {
            return "redirect:/redirect";
        }
        return "redirect:/error";
    }
}
