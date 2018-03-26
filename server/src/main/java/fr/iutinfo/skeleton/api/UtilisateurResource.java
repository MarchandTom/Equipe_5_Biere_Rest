package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

@Path("/Utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurResource {
    final static Logger logger = LoggerFactory.getLogger(UtilisateurResource.class);
    private static UtilisateurDao dao = getDbi().open(UtilisateurDao.class);

    public UtilisateurResource() throws SQLException {
        if (!tableExist("utilisateur")) {
            logger.debug("Create table utilisateur");
            dao.createUtilisateurTable();
            dao.insert(new Utilisateur("Margaret", "Thatcher", "la Dame de fer", "siretnumero", "margaret@a.com", "passwrd", "1 rue b", "0000000000" , "barman"));
        }
        
    }

    @POST
    public UtilisateurDto createUtilisateur(UtilisateurDto dto) {
    	logger.debug("--------------"+dto.toString());
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.initFromDto(dto);
//        utilisateur.resetPasswordHash();
        int uno = dao.insert(utilisateur);
        dto.setUno(uno);
        return dto;
    }

    @GET
    @Path("/{uno}")
    public UtilisateurDto getUtilisateur(@PathParam("uno") int uno) {
        Utilisateur utilisateur = dao.findById(uno);
        if (utilisateur == null) {
            throw new WebApplicationException(404);
        }
        return utilisateur.convertToDto();
    }
    
    @GET
    @Path("/{email}/{mdp}")
    public UtilisateurDto getUtilisateur(@PathParam("email") String email, @PathParam("mdp") String mdp) {
        Utilisateur utilisateur = dao.findByLog(email, mdp);
        if (utilisateur == null) {
            throw new WebApplicationException(404);
        }
        logger.debug(utilisateur.toString());
        return utilisateur.convertToDto();
    }
    
//    @GET
//    @Path("/{nom}")
//    public UtilisateurDto getUtilisateurByNom(@PathParam("nom") String nom) {
//        Utilisateur utilisateur = dao.findByNom(nom);
//        if (utilisateur == null) {
//            throw new WebApplicationException(404);
//        }
//        return utilisateur.convertToDto();
//    }

    @GET
    public List<UtilisateurDto> getAllUtilisateurs(@QueryParam("q") String query) {
        List<Utilisateur> utilisateurs;
        if (query == null) {
            utilisateurs = dao.all();
        } else {
            logger.debug("Search Utilisateurs with query: " + query);
            utilisateurs = dao.search("%" + query + "%");
        }
        return utilisateurs.stream().map(Utilisateur::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{uno}")
    public void deleteUtilisateur(@PathParam("uno") int uno) {
        dao.delete(uno);
    }
}