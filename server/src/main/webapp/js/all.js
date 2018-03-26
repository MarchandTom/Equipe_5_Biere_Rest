/*$(document).ready(function(){
		console.log("initialisation");
		retourMenu();
});*/

var identificationLogin, motDePasse;

var uno_utilisateur_actu;
var type_utilisateur_actu;
var utilisateur_actu=null;

function pageLog(){
	if(utilisateur_actu==null){
		document.getElementById("hautDePage").style.display = 'none';
		$("#contenuPage").load("Identification.html");
		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
			'background-image' :'url(https://thumbs.dreamstime.com/b/fond-vert-et-jaune-44679306.jpg)',
			'background-size':'cover'
		});
	}else{
		identificationLogin="";
		motDePasse="";
		uno_utilisateur_actu=-1;
		type_utilisateur_actu="";
		utilisateur_actu=null;
		document.getElementById('boutonLogBarreAccueil').innerHTML="Se connecter";
	}
}

function getUser(name) {
	getUserGeneric(name, "v1/Utilisateur/");
}

function getUserGeneric(name, url) {
	$.getJSON(url + name, function(data) {
		afficheUser(data);
	});
}

function login() {
	identificationLogin = $("#userlogin").val();
	motDePasse = $("#passwdlogin").val();
	console.log(identificationLogin+"  "+motDePasse);
	getWithAuthorizationHeader("v1/Utilisateur", function(data){
		if(data!=null){
		console.log("on est rentré dans le data != null");
			var mot="";
			for(var i=0;i<data.length; i++){
				if(data[i].email==identificationLogin && data[i].mdp==motDePasse){
					type_utilisateur_actu=data[i].type;
					uno_utilisateur_actu=data[i].uno;
					utilisateur_actu=data[i];
					document.getElementById('boutonLogBarreAccueil').innerHTML="Déconnexion";
					if(data[i].type=="barman"){
						pageBarman();
					}else if(data[i].type=="brasseur"){
						pageBrasseur();
					}else if(data[i].type=="admin"){
						pageAdmin();
					}else{
						pageComiteEnt();
					}
					break;
				}
			}
		}
		/*if (data.name == "Anonymous") {
			alert("Le mot de passe ou l'dentifiant est incorrect");
			$("userlogin").val("yo");
			$("passwdlogin").val("yo");
			console.log("c'est faux");
		}
		$("#login_form").hide();
		afficheUser(data);*/
	});
}

function pageBarman(){
	if(type_utilisateur_actu=="barman"){
			document.getElementById("hautDePage").style.display = 'none';

			$("#contenuPage").load("Liste_biere_barman.html");

			document.getElementById("piedDePage").innerHTML = "";

			$("body").css({
			'background-image' :'url(https://fac.img.pmdstatic.net/fit/http.3A.2F.2Fwww.2Efemmeactuelle.2Efr.2Fvar.2Ffemmeactuelle.2Fstorage.2Fimages.2Fcuisine.2Fguides-cuisine.2Ftout-savoir-sur-la-biere-24220.2F13639150-1-fre-FR.2Ftout-savoir-sur-la-biere.2Ejpg/748x372/quality/80/crop-from/center/tout-savoir-sur-la-biere.jpeg)',
			'background-size':'cover'
		});
		}else if(type_utilisateur_actu!=""){
			retourMenu();
		}else{
			pageLog();
		}
}

function pageBrasseur(){
	if(type_utilisateur_actu=="brasseur"){
		document.getElementById("hautDePage").style.display = 'none';

		$("#contenuPage").load("Liste_biere_brasseur.html");

		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
		'background-image' :'url(https://fac.img.pmdstatic.net/fit/http.3A.2F.2Fwww.2Efemmeactuelle.2Efr.2Fvar.2Ffemmeactuelle.2Fstorage.2Fimages.2Fcuisine.2Fguides-cuisine.2Ftout-savoir-sur-la-biere-24220.2F13639150-1-fre-FR.2Ftout-savoir-sur-la-biere.2Ejpg/748x372/quality/80/crop-from/center/tout-savoir-sur-la-biere.jpeg)',
		'background-size':'cover'
	});
	}else if(type_utilisateur_actu!=""){
		retourMenu();
	}else{
		pageLog();
	}
}

function pageDepotBiere(){
	if(type_utilisateur_actu=="brasseur"){
		document.getElementById("hautDePage").style.display = 'none';

		$("#contenuPage").load("Depot_biere.html");

		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
		'background-image' :'url(https://thumbs.dreamstime.com/b/fond-vert-et-jaune-44679306.jpg)',
		'background-size':'cover'
	});
	}else if(type_utilisateur_actu!=""){
		retourMenu();
	}else{
		pageLog();
	}
}

function pageAffichageToutesLesBieresAdmin(){
	if(type_utilisateur_actu=="admin"){
		document.getElementById("hautDePage").style.display = 'none';

		$("#contenuPage").load("Affichage_biere.html");
		console.log("ici on doit afficher l'html");

		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
		'background-image' :'url(https://thumbs.dreamstime.com/b/fond-vert-et-jaune-44679306.jpg)',
		'background-size':'cover'
	});
	}
	else if(type_utilisateur_actu!=""){
		retourMenu();
	}else{
		pageLog();
	}
}

function pageAffichageToutesLesBieres(){
	if(type_utilisateur_actu=="barman"){
		document.getElementById("hautDePage").style.display = 'none';

		$("#contenuPage").load("Affichage_biere.html");
		console.log("ici on doit afficher l'html");

		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
		'background-image' :'url(https://thumbs.dreamstime.com/b/fond-vert-et-jaune-44679306.jpg)',
		'background-size':'cover'
	});
	}
	else if(type_utilisateur_actu!=""){
		retourMenu();
	}else{
		pageLog();
	}
}

function pageComiteEnt(){
	
}

function pageAdmin(){
	if(type_utilisateur_actu=="admin"){
		document.getElementById("hautDePage").style.display = 'none';

		$("#contenuPage").load("AdminPageAccueil.html");

		document.getElementById("piedDePage").innerHTML = "";

		$("body").css({
		'background-image' :'url(https://fac.img.pmdstatic.net/fit/http.3A.2F.2Fwww.2Efemmeactuelle.2Efr.2Fvar.2Ffemmeactuelle.2Fstorage.2Fimages.2Fcuisine.2Fguides-cuisine.2Ftout-savoir-sur-la-biere-24220.2F13639150-1-fre-FR.2Ftout-savoir-sur-la-biere.2Ejpg/748x372/quality/80/crop-from/center/tout-savoir-sur-la-biere.jpeg)',
		'background-size':'cover'
	});
	}else if(type_utilisateur_actu!=""){
		retourMenu();
	}else{
		pageLog();
	}
}

function profile() {
	getWithAuthorizationHeader("v1/profile", function (data) {
		afficheUser(data);});
}

function getWithAuthorizationHeader(url, callback) {
	if( identificationLogin != "" ) {
		$.ajax
		({
			type: "GET",
			url: url,
			dataType: 'json',
			beforeSend : function(req) {
				req.setRequestHeader("Authorization", "Basic " + btoa(identificationLogin + ":" + motDePasse));
			},
			success: callback,
			error : function(jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
	} else {
		$.getJSON(url, function(data) {
			afficheUser(data);
		});
	}
}

function postUser(type, nom, prenom,enseigne,siret, email, mdp,adresse,tel) {
	postUserGeneric(type, nom, prenom,enseigne,siret, email, mdp,adresse,tel, 'v1/Utilisateur/')
}
function postBiere(nom, prix) {
	postBiereGeneric(nom, prix, uno_utilisateur_actu, 'v1/Biere/')
}

function postBiereGeneric(nom, prix, uno, url) {
	console.log("postBiereGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"nom" : nom,
			"prix" : prix,
			"uno" : uno,
			"id" : 0
		}),
		success : function(data, textStatus, jqXHR) {
			alert("La bière a bien été créée.");
			pageBrasseur();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postBiere error: ' + textStatus);
		}
	});
}
function postUserGeneric(type, nom, prenom,enseigne,siret, email, mdp,adresse,tel, url) {
	console.log("postUserGeneric " + url)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"type": type,
			"nom" : nom,
			"prenom" : prenom,
			"enseigne" : enseigne,
			"siret" : siret,
			"email" : email,
			"mdp" : mdp,
			"adresse" : adresse,
			"tel" : tel,
			"id" : 0
		}),
		success : function(data, textStatus, jqXHR) {
			alert("L'utilisateur a bien été créé.");
/*			afficheUser(data);*/
			retourMenu();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function listUsers() {
	listUsersGeneric("v1/Utilisateur/");
}
function listBieres() {
	listUsersGeneric("v1/Biere/");
}


function listUsersGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListUsers(data)
	});
}

function afficheUser(data) {
	console.log(data);
	$("#reponse").html(userStringify(data));
}
function afficheBiere(data) {
	console.log(data);
	$("#reponse").html(BiereStringify(data));
}



function afficheListUsers(data) {
	var ul = document.createElement('ul');
	ul.className = "list-group";
	var index = 0;
	for (index = 0; index < data.length; ++index) {
		var li = document.createElement('li');
		li.className = "list-group-item";
		li.innerHTML = userStringify(data[index]);
		ul.appendChild(li);
	}
	$("#reponse").html(ul);
}

function userStringify(user) {
	return user.id + ". " + user.name + " &lt;" + user.email + "&gt;" + " (" + user.alias + ")";
}
function BiereStringify(biere) {
	return biere.id + ". " + biere.nom + " &lt;" + biere.prix+ "&gt;";
}

function afficheCreation() {
	document.getElementById("hautDePage").style.display = 'none';
/*	document.getElementById("hautDePage").innerHTML = "";*/
	$("#contenuPage").load("Creation.html");
	document.getElementById("piedDePage").innerHTML = "";

	$("body").css({
		'background-image' :'url(https://thumbs.dreamstime.com/b/fond-vert-et-jaune-44679306.jpg)',
		'background-size':'cover'
	});
}

function getUnoActu(){
	return uno_utilisateur_actu;
}

function retourMenu() {
	document.getElementById("hautDePage").style.display = 'block';
	/*$("#hautDePage").load("Barre_accueil.html"); */
	$("#contenuPage").load("Sliders_photo.html"); 
	$("#piedDePage").load("Pied_de_page.html"); 
	$("body").css('background-image','none');
	/*document.getElementById('deposerBiere').innerHTML="bonjour toi";*/
/*	$("#creation").hide();
$("#identification").show();*/
}
