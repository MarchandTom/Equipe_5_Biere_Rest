$(document).ready(function() {
                 console.log("yo");
                $("#identification").hide();
                $("#creation").hide();
                $("#barre_log").hide();
                debugger;
               
                $("#creer").click(function(event) {
                    event.preventDefault();
                    afficheCreation();
                });
                $("#retour").click(function(event) {
                    event.preventDefault();
                    document.getElementById("email2").value = "";
                    retourMenu();
                });
                $("#log").click(function(event) {
                  var accueil = new Array("#barre_accueil","#site-slider","#services","#site-footer");
                  var i;
                  for (i = 0 ; i < accueil.length ; i++) {
                    $(accueil[i]).hide();

                  }
                $("#identification").show();


                });
				$("#get").click(function (event) {
                    event.preventDefault();
				    getUser($('#user').val(), $('#alias').val())
				    });
				$("#post").click(function (event) {
                    event.preventDefault();
                    postUser(
                        $('#user').val(),
                        $('#alias').val(),
                        $('#enseigne').val(),
                        $('#siret').val(),
                        $('#email').val(),
                        $('#passwd').val(),
                        $('#adresse').val(),
                        $('#tel').val())});
                $("#buttonPostBiere").click(function (event) {
                    event.preventDefault();
                    postBiere(
                        $('#nomBiere').val(),
                        $('#prix').val())});
				$("#list").click(function (event) {
                    event.preventDefault();
				    listUsers()
				    });
				$("#login").click(function (event) {
                    event.preventDefault();
				    login();
                    document.getElementById("userlogin").value = "";
                    document.getElementById("passwdlogin").value = "";
				    });
				$("#profile").click(function (event) {
                    event.preventDefault();
				    profile()
				    });
			});
