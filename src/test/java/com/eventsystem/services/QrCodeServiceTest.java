package com.eventsystem.services;

import com.eventsystem.service.QrCodeGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class QrCodeServiceTest {
    @Test
    void test_onGeneratingQrCode() throws Exception {
        int height = 450;
        int width = 450;
        BufferedImage qrCode = QrCodeGeneratorService.generateQRCodeImage("test");
        assertEquals(qrCode.getHeight(), height);
        assertEquals(qrCode.getWidth(), width);
    }
}
