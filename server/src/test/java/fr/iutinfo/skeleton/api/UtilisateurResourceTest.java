package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.Helper.*;
import static fr.iutinfo.skeleton.api.Helper.createLinus;
import static fr.iutinfo.skeleton.api.Helper.createRms;
import static fr.iutinfo.skeleton.api.Helper.createRob;
import static fr.iutinfo.skeleton.api.Helper.createUserWithName;
import static fr.iutinfo.skeleton.api.Helper.createUserWithPassword;
import static fr.iutinfo.skeleton.api.Helper.listUserResponseType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

public class UtilisateurResourceTest extends JerseyTest{
    private static final String PATH = "/Utilisateur";//ALERTE ! DANS LA CLASSE BARMANRESSOURCE LE PATH /user EST DIFFERENT DE PATH /Biereman
    private UtilisateurDao dao = BDDFactory.getDbi().open(UtilisateurDao.class);

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        Helper.initDb();//Helper initialise User et non pas barman! voir cette classe 
    }

    @Test
    public void read_should_return_a_utilisateur_as_object() {
    	Utilisateur u = createFullUtilisateur("adrien", "durand", "CGI", "siretNum", "a.do@gmail.com", "mdp", "164 rue d", "0604080998", "barman"); //Attention !import de java.util.logging.Logger != org.slf4j.LoggerFactory
        UtilisateurDto utilisateur = target(PATH + "/"+u.getUno()).request().get(UtilisateurDto.class);
        assertEquals(u.getUno(), utilisateur.getUno());
    }

    @Test
    public void read_user_should_return_good_name() {
    	Utilisateur u = createFullUtilisateur("adrien", "durand", "CGI", "siretNum", "a.d@gmail.com", "mdp", "164 rue d", "0604080998", "barman");
        UtilisateurDto utilisateur = target(PATH + "/"+u.getUno()).request().get(UtilisateurDto.class);//Chaque Rest ajoute un personnage sans enlever
        assertEquals("adrien",utilisateur.getPrenom());
    }
    
    @Test
    public void read_all_user() {
    	createFullUtilisateur("adrien", "durand", "CGI", "siretNum", "a.b@gmail.com", "mdp", "164 rue d", "0604080998", "barman");	
    	List<UtilisateurDto> users = target(PATH + "/").request().get(listUtilisateurResponseType);
     	assertTrue(users.toString(),users.size()>=1);
    }
    
    @Test
    public void should_delete_utilisateur() {
		UtilisateurDao utilisateurDao = BDDFactory.getDbi().open(UtilisateurDao.class);
		utilisateurDao.dropUtilisateurTable();
		utilisateurDao.createUtilisateurTable();
        Utilisateur u = createUtilisateurWithName("toto");
        
        target(PATH + "/" + u.getUno()).request().delete();
        Utilisateur utilisateur = dao.findById(u.getUno());
        Assert.assertEquals(null, utilisateur);
    }
    
    public static Utilisateur createFullUtilisateur(String prenom, String nom, String enseigne, String siret, String email,String adresse,
    		String passwdHash, String tel, String type) {
    	Utilisateur utilisateur = new Utilisateur(prenom,nom,enseigne,siret,email,passwdHash,adresse,tel, type);
        UtilisateurDao utilisateurDao=BDDFactory.getDbi().open(UtilisateurDao.class);
        int id = utilisateurDao.insert(utilisateur);
        utilisateur.setUno(id);
        return utilisateur;
    }
    
    
}
