let socket = new WebSocket("ws://localhost:8081")
new DOMParser();
let currentUserChat
const userProtos = []

function onSendMessageAppear(id, text) {
    const element = document.createElement('div')
    let dateWithouthSecond = new Date();
    let formattedTime = dateWithouthSecond.toLocaleTimeString(navigator.language, {hour: '2-digit', minute:'2-digit'});
    element.style = "display: block; height: 10vh;"
    element.className = "message"
    const u = document.createElement('span')
    const t = document.createElement('span')
    const p = document.createElement('p')
    u.innerHTML = `${id}`
    p.innerHTML = `${text}`
    t.innerHTML = `${formattedTime}`
    element.appendChild(u)
    element.appendChild(p)
    element.appendChild(t)
    return element
}

document.addEventListener('DOMContentLoaded', () => {
    const users = Array.from(document.getElementsByClassName('user_chat'))
    const chat = document.getElementById('chat_container')
    const userChats = []

    const createContainer = (id) => {
        const element = document.createElement('div')
        element.style = "display: block; height: 88vh;"
        element.className = "container"
        element.setAttribute('id', id)
        return element
    }

    users.forEach(element => {
        userChats.push(element.id)
        userProtos.push({id: element.id, node: createContainer(element.id)})
        element.addEventListener('click', () => {
            chat.innerHTML = ''
            chat.appendChild(userProtos.filter(object => object.id === element.id)[0].node)
            currentUserChat = element.id
        })
    });

    socket.onmessage = (event) => {
        console.log('event', event)
        const data = JSON.parse(event.data)
        console.log(event.data);
        switch (data.type) {
            case 'NEW_MESSAGE':
                const fromChat = userProtos.filter(object => {
                    const u = `user_${data.from}`
                    console.log(object.id + ' - ' + typeof object.id + ' - ' + typeof u + ' - ' + u)
                    console.log('result: ', object.id === u)
                    return object.id === u
                })
                console.log(fromChat[0])
                fromChat[0].node.appendChild(onSendMessageAppear(data.from, data.text))
                break
        }
    }

    socket.onopen = function () {
        const onInitData = {
            type: "START",
            userId
        }
        socket.send(JSON.stringify(onInitData))
    }

    chat.append(userProtos[0].node)
    currentUserChat = userProtos[0].id
})

function sendMessage() {
    const textArea = document.getElementById("textarea2")
    const message = textArea.value.trim()
    if (message.length === 0) return

    const msg = {
        type: "SEND_MESSAGE",
        from: userId,
        to: currentUserChat.replace('user_', ''),
        text: message
    }

    const u = `user_${msg.to}`

    const fromChat = userProtos.filter(object => {
        console.log(object.id + ' - ' + typeof object.id + ' - ' + typeof u + ' - ' + u)
        console.log('result: ', object.id === u)
        return object.id === u
    })

    fromChat[0].node.appendChild(onSendMessageAppear(u, msg.text))
    socket.send(JSON.stringify(msg))
    textArea.value = ""
}