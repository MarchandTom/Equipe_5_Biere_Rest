package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UserDto;
import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class Helper {
    private final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private static final UserDao dao = BDDFactory.getDbi().open(UserDao.class);
    private static final UtilisateurDao daoU = BDDFactory.getDbi().open(UtilisateurDao.class);
    static GenericType<List<UserDto>> listUserResponseType = new GenericType<List<UserDto>>() {
    };
    static GenericType<List<UtilisateurDto>> listUtilisateurResponseType = new GenericType<List<UtilisateurDto>>() {
    };

    public static void initDb() {
        dao.dropUserTable();
        dao.createUserTable();
    }
    
    static Utilisateur createUtilisateurWithName(String name) {
        Utilisateur utilisateur = new Utilisateur(name);
        return createUtilisateur(utilisateur);
    }

    static User createUserWithName(String name) {
        User user = new User(0, name);
        return createUser(user);
    }

    static User createUserWithAlias(String name, String alias) {
        User user = new User(0, name, alias);
        return createUser(user);
    }

    static User createUserWithEmail(String name, String email) {
        User user = new User(0, name);
        user.setEmail(email);
        return createUser(user);
    }

    public static User createUserWithPassword(String name, String passwd, String salt) {
        User user = new User(0, name);
        user.setSalt(salt);
        user.setPassword(passwd);
        logger.debug("createUserWithPassword Hash : " + user.getPasswdHash());
        return createUser(user);
    }

    private static User createUser(User user) {
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }
    
    private static Utilisateur createUtilisateur(Utilisateur utilisateur) {
        int id = daoU.insert(utilisateur);
        utilisateur.setUno(id);
        return utilisateur;
    }


    private static User createFullUSer(String name, String alias, String email, String paswword) {
        User user = new User(0, name);
        user.setAlias(alias);
        user.setEmail(email);
        user.setPassword(paswword);
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }
    
    public static Utilisateur createFullUtilisateur(String prenom, String nom, String enseigne, String siret, String email,String adresse,
			String passwdHash, String tel, String type) {
    	Utilisateur utilisateur = new Utilisateur(prenom,nom,enseigne,siret,email,passwdHash,adresse,tel, type);
        UtilisateurDao utilisateurDao=BDDFactory.getDbi().open(UtilisateurDao.class);
        int id = utilisateurDao.insert(utilisateur);
        utilisateur.setUno(id);
        return utilisateur;
    }

    static void createRms() {
        createFullUSer("Richard Stallman", "RMS", "rms@fsf.org", "gnuPaswword");
    	//createFullUtilisateur("john", "null", "a", "a", "a", "a", "a", "a", "barman");
    }

    static User createRob() {
        return createFullUSer("Robert Capillo", "rob", "rob@fsf.org", "paswword");
    }

    static User createLinus() {
        return createFullUSer("Linus Torvalds", "linus", "linus@linux.org", "paswword");
    }

    static User createIan() {
        return createFullUSer("Ian Murdock", "debian", "ian@debian.org", "mot de passe");
    }
}
