/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {

    @Context
    private UriInfo context;

    private ScheduleService service;

    /**
     * Creates a new instance of ScheduleRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    /**
     * Retrieves representation of an instance of resource
     *
     * @return an instance of resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlot() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlots getAllSchedules() {
        ArrayList<ProgramSlot> pslist = service.findAllPS();
        ProgramSlots pssList = new ProgramSlots();
        pssList.setPsList(new ArrayList<ProgramSlot>());

        for (int i = 0; i < pslist.size(); i++) {
            pssList.getPsList().add(
                    new ProgramSlot(pslist.get(i).getId(),
                            pslist.get(i).getRpname(),
                            pslist.get(i).getDate(),
                            pslist.get(i).getSttime(),
                            pslist.get(i).getDuration(),
                            pslist.get(i).getPresenter(),
                            pslist.get(i).getProducer()));
        }

        return pssList;
    }

    /**
     * PUT method for updating or creating an instance of resource
     *
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSchedule(ProgramSlot ps) {
        if (service.processModify(ps)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.CONFLICT).build();
        }

    }

    /**
     * POST method for creating an instance of resource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSchedule(ProgramSlot ps) {
        System.out.println("REST create " + ps.getRpname());

        if (service.processCreate(ps)) {
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.CONFLICT).build();
        }
    }

    /**
     * DELETE method for deleting an instance of resource
     *
     * @param name name of the resource Mia: incorrect, long path
     */
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(@PathParam("id") String id) {
        String id2;
        try {
            id2 = URLDecoder.decode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        service.processDelete(Integer.valueOf(id2));
    }
}
