var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/ticket', function (ticket) {
            showBooked(JSON.parse(ticket.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/cinema", {}, JSON.stringify({'name': $("#name").val(), 'surname': $("#surname").val()}));
}


function showBooked(ticket) {

    var d = new Date(ticket.time);
    $("#greetings").append("<tr>")
                    .append("<td>" + ticket.id + "</td>")
                    .append("<td>" + ticket.name + "</td>")
                    .append("<td>" + d.toUTCString() + "</td>")
                    .append("<td>" + ticket.seat + "</td>")
                    .append("<td>" + ticket.price.amount+ " " + ticket.price.currency + "</td>")
                    .append("<td>" + ticket.user.name +" " + ticket.user.name+ "</td>")
                    .append("</tr>");

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() {
        connect();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});