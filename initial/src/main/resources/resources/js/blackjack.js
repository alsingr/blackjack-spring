
var player;
var players ;
$(document).ready(Onload);








function addCard(id, card){
	var cardName = card.sign + '_' + card.value + '.png';
	//alert(cardName);
	//alert('#' + id);

	$("#" + id).append('<img th:src="@{/img/' + cardName + '}" src="../img/' + cardName + '" class="uneCarte">');
}

function majCarteJoueur(){
	for(var i = 0 ; i < player.cards.length ; i++)
	addCard('ZoneCartesJoueur', player.cards[i]);
}
function majCarteCroupier(){
	for(var i = 0 ; i < players[0].cards.length ; i++)
	addCard('ZoneCartesCroupier', players[0].cards[i]);
}
function majMise()
{
}
function displayPlayerInfo()
{
	$("#zoneScore").text('Score : ' + player.score );	
	$("#zoneGain").text ( 'Gain : ' + player.gain) ;
	$("#zoneMise").text ('Mise : ' + player.bet );

	if(player.score >21)
	{

	}

	$('#ZoneCartesJoueur').text('');
	for(var i = 0 ; i < player.cards.length ; i++)
		addCard('ZoneCartesJoueur', player.cards[i]);
}


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
		});
	});

	updateView();
}

function updateView()
{
	setInterval(function() {
		initPlayers();
}, 5000);
}

function initPlayer(){
	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/subscribe",
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			player = data;
			displayPlayerInfo();
			initPlayers();
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
			player = data;
			displayPlayerInfo();

			$('#ZoneCartesJoueur').text('');
			for(var i = 0 ; i < player.cards.length ; i++)
				addCard('ZoneCartesJoueur', player.cards[i]);
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
			players = data;
			$('#ZoneCartesCroupier').text('');
			for(var i = 0 ; i < players[0].cards.length -1 ; i++)
				addCard('ZoneCartesCroupier', players[0].cards[i]);
			//Update other players
			$('#zoneAutresJoueur').text('');
			for(var j=1 ; j<players.length ; j++)
			{
				if(players[j].id != player.id)
				{
					$('#zoneAutresJoueur').append('<div id="joueur_'+players[j].id+'"></div>');
					for(var i = 0 ; i < players[j].cards.length ; i++)
						addCard('joueur-'+players[j].id, players[0].cards[i]);
				}
			}
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
	if(player.bet != 0  && player.canPlay)
	{
		$.ajax({
			type: 'GET', // Ou Post
			url: "/play/"+ getRoom() + "/actions",
			data: "id=" + data.id + "&actions=" + data.action,
			dataType: 'json', // Ou text, ou html
			success: function(data) {
				player = data; 
				displayPlayerInfo() ;
				
			},
			error: function() {
				alert('La requête n\'a pas abouti'); 
			}
		});
	}
	
}

function doBet(data){

	$.ajax({
		type: 'GET', // Ou Post
		url: "/play/"+ getRoom() + "/bet",
		data: "id=" + data.id + "&bet=" + data.bet,
		dataType: 'json', // Ou text, ou html
		success: function(data) {
			player = data; 
			displayPlayerInfo();
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


