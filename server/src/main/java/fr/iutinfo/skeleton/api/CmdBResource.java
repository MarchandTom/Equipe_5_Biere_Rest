package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.*;
import fr.iutinfo.skeleton.common.dto.CmdBDto;

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


@Path("/CmdB")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CmdBResource {
	final static Logger logger = LoggerFactory.getLogger(CmdBResource.class);
    private static CmdBDao dao = getDbi().open(CmdBDao.class);
    
    public CmdBResource() throws SQLException {
        if (!tableExist("cmdb")) {
            logger.debug("Create table cmdb");
            dao.createCmdBTable();
            dao.insert(new CmdB(1,1,10));
        }
        
    }
    @POST 
    public CmdBDto createCmdB(CmdBDto dto) { 
        CmdB cmdB = new CmdB(); 
        cmdB.initFromDto(dto); 
        int id = dao.insert(cmdB);
        logger.debug(cmdB.toString());
        dto.setCno(id);
        logger.debug(""+dto.getBno());
        return dto; 
    }   
    
    @GET
    @Path("/{cno}")
    public CmdB getCmdBByCno(@PathParam("cno")int cno) {
    	CmdB cmdB = dao.findByCno(cno);
        if (cmdB == null) {
            throw new WebApplicationException(404);
        }	
        System.out.println(cmdB);
    	return cmdB;
    }
    
    @GET
    @Path("/CmdB/{uno}")
    public List<CmdB> getCmdBByUno(@PathParam("uno")String uno) {
    	List<CmdB> cmdB = dao.search(uno);
        if (cmdB == null) {
            throw new WebApplicationException(404);
        }	
        logger.debug(cmdB.get(0).toString());
    	return cmdB;
    }
    
    @GET 
    public List<CmdBDto> getAllCmdB(@QueryParam("q") String query) { 
        List<CmdB> cmdB; 
        if (query == null) { 
            cmdB = dao.all(); 
        } else { 
            logger.debug("Search CmdB with query: " + query); 
            cmdB = dao.search("%" + query + "%"); 
        } 
        return cmdB.stream().map(CmdB::convertToDto).collect(Collectors.toList()); 
    }
    
    @DELETE 
    @Path("/{cno}") 
    public void deleteCmdB(@PathParam("cno") int cno) { 
        dao.delete(cno); 
    } 
}