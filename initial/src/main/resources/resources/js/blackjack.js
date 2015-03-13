var myURL = "/play/" + getRoom() + "/subscribe";


function launchQuery() {
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

