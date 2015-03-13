
var player;
var players = [];
$(document).ready(Onload);

function Onload ()
{
	initPlayer();

	$("#bouttons input").each(function(){
		$(this).click(function(){
			myAction = $(this).attr('id');
			if(myAction != 'Bet'){
				doAction({
					id: player.id,
					action: myAction
				});
			} else {
				doBet({
					id: player.id,
					bet: 150
				});
			}

			launchQuery(myURL);
		});
	});
}

function initPlayer(){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/subscribe",
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data);
			player = data; 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}

function majPlayer(){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/me",
		data: 'id=' + player.id,
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data);
			player = data; 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}

function initPlayers(){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/players",
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data);
			players = data; 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}
/*
*
* id  = player.id
*
* Actions possible : NONE, Card, Stop, Double, Abort, Split
*
*/
function doAction(data){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/actions",
		data: "id=" + data.id + "&actions=" + data.action,
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data);
			player = data; 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}
function doBet(data){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/bet",
		data: "id=" + data.id + "&bet=" + data.bet,
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data);
			player = data; 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}

function launchQuery(myURL) {
	$.ajax({
		type: 'GET', // Ou Post
		url: myURL,
		data: 'PARAMETER=' + 'VALUE' + '&PARAMETER2=' + 'VALUE',
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			alert(""+data); 
		},
		error: function() {
			alert('La requête n\'a pas abouti'); 
		}
	});
}

function getRoom(){
	var roomName = window.location.href;
	var tab = roomName.split("play/");
	var room = tab[1].split("/")[0];
	return room;
}
function click() {

}