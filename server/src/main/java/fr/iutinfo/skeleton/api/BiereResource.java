package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.*;
import fr.iutinfo.skeleton.common.dto.BiereDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/Biere")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BiereResource {
	final static Logger logger = LoggerFactory.getLogger(BiereResource.class);
    private static BiereDao dao = getDbi().open(BiereDao.class);
    
    public BiereResource() throws SQLException {
        if (!tableExist("bieres")) {
            logger.debug("Create table biere");
            dao.createBiereTable();
            dao.insert(new Biere("Biere de la mort qui tue",10));
        }
        
    }
    @POST 
    public BiereDto createBiere(BiereDto dto) { 
        Biere Biere = new Biere(); 
        Biere.initFromDto(dto); 
        int id = dao.insert(Biere); 
        dto.setBno(id); 
        return dto; 
    }   
    
    @GET
    @Path("/{bno}")
    public Biere getBiereByBno(@PathParam("bno")int bno) {
    	Biere biere = dao.findByBno(bno);
        if (biere == null) {
            throw new WebApplicationException(404);
        }	
        System.out.println(biere);
    	return biere;
    }
    
    @GET
    @Path("/brasseur/{uno}")
    public List<Biere> getBieresByUno(@PathParam("uno")int uno) {
    	List<Biere> bieres = dao.findByUno(uno);
        if (bieres == null) {
            throw new WebApplicationException(404);
        }	
    	return bieres;
    }
    
    @GET 
    public List<BiereDto> getAllBieres(@QueryParam("q") String query) { 
        List<Biere> Bieres; 
        if (query == null) { 
            Bieres = dao.all(); 
        } else { 
            logger.debug("Search Biere with query: " + query); 
            Bieres = dao.search("%" + query + "%"); 
        } 
        return Bieres.stream().map(Biere::convertToDto).collect(Collectors.toList()); 
    }
    
    @DELETE 
    @Path("/{id}") 
    public void deleteBiere(@PathParam("id") int id) { 
        dao.delete(id); 
    } 
    
   
}