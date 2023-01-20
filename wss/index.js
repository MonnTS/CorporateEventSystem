const WebSocket = require('ws')
const wss = new WebSocket.Server({port: 8081})

class User {
    constructor(ws) {
        this.ws = ws
        this.id = ''
    }
    error = (msg) => {
        const json = JSON.stringify({type: 'error', message: msg})
        console.log(`Send ERROR to ${this.id} - ${json}`)
        this.ws.send(json)
    }
    send = (data) => {
        const json = JSON.stringify(data)
        console.log(`Send to ${this.id} - ${json}`)
        this.ws.send(json)
    }
}

function setId(id, user) {
    user.id = id
}

const users = []
console.log(`Chat Server listen destination: 0.0.0.0:${8081}`)
wss.on('connection', ws => {
    const user = new User(ws)
    users.push(user)
    ws.on('message', data => {
        console.log(`Received from ${user.id} - ${data}`)
        try {
            const msg = JSON.parse(data)
            switch (msg.type) {
                case 'START':
                    setId(msg.userId, user)
                    break
                case 'SEND_MESSAGE':
                    const { from, to, text } = msg
                    const toUser = users.filter(user => user.id === to)
                    toUser[0].send({ type: 'NEW_MESSAGE', from, text })
                    break
                default:
                    console.error(`unknown command: ${msg.type}`)
                    break
            }
        } catch (e) {
            if (e instanceof SyntaxError) return user.error('json parse')
            console.error(user)
            console.error(e)
        }
    })
    ws.on('close', () => {
        if (user.lobby) user.lobby.leave(user)
    })
})