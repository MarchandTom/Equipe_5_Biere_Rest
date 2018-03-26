package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class HelpU {
    private final static Logger logger = LoggerFactory.getLogger(HelpU.class);
    private static final UtilisateurDao dao = BDDFactory.getDbi().open(UtilisateurDao.class);
    static GenericType<List<UtilisateurDto>> listUtilisateurResponseType = new GenericType<List<UtilisateurDto>>() {
    };
    
    static Utilisateur createUtilisateurWithName(String name) {
        Utilisateur utilisateur = new Utilisateur(name);
        return createUtilisateur(utilisateur);
    }

    private static Utilisateur createUtilisateur(Utilisateur utilisateur) {
        int id = dao.insert(utilisateur);
        utilisateur.setUno(id);
        return utilisateur;
    }



}
