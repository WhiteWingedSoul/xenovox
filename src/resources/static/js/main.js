'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var listPage = document.querySelector('#list-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var listArea = document.querySelector('#listArea');
var connectingElement = document.querySelector('.connecting');
var onlineButton = document.querySelector('#online-button');
var backButton = document.querySelector('#back-button');

var stompClient = null;
var username = null;

const DOMAIN = "http://web-lb-790242101.ap-northeast-1.elb.amazonaws.com"

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        usernamePage.classList.add('hidden');
        listPage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS(DOMAIN + '/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


async function onConnected() {
    // Get recent messages
    var response = await axios.get(DOMAIN + '/messages');
    for (var message of response.data) {
        displayMessage(message);
    }
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/group', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/join",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    displayMessage(message);
}

function displayMessage(message) {

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

function displayUser(user) {
    var userElement = document.createElement('li');

    userElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(user.name[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(user.name);

    userElement.appendChild(avatarElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(user.name);
    usernameElement.appendChild(usernameText);
    userElement.appendChild(usernameElement);

    listArea.appendChild(userElement);
    listArea.scrollTop = listArea.scrollHeight;
}

async function showOnline() {
    usernamePage.classList.add('hidden');
    listPage.classList.remove('hidden');
    chatPage.classList.add('hidden');

    listArea.innerHTML = "";

    var response = await axios.get(DOMAIN + '/users');
    for (var user of response.data) {
        displayUser(user)
    }

}

function backToChat() {
    usernamePage.classList.add('hidden');
    listPage.classList.add('hidden');
    chatPage.classList.remove('hidden');
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
onlineButton.addEventListener('click', showOnline, true)
backButton.addEventListener('click', backToChat, true)