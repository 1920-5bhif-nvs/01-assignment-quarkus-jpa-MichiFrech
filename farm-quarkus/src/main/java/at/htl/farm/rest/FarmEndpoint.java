package at.htl.farm.rest;

import at.htl.farm.model.Farm;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("farm")
public class FarmEndpoint {

    @Inject
    EntityManager em;

    @Path("findall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findAll(){
        return Response.ok(em.createNamedQuery("Farm.findAll", Farm.class).getResultList()).build();
    }

    @Path("find/{id}")
    @GET
    @Transactional
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response find(@PathParam("id") long id) {
        Farm farm = em.createNamedQuery("Farm.findById", Farm.class).setParameter(1, id).getSingleResult();

        if(farm != null) {
            return Response.ok(farm).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("id") long id) {
        try{
            Farm f = em.find(Farm.class, id);
            if(f != null){
                em.remove(f);
            }
        }catch (Exception e){
            return Response.status(404).build();
        }
        return Response.ok().build();
    }

}
