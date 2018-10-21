/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.entity.Users;
import sg.edu.nus.iss.phoenix.user.restful.UserRESTService;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectUserService;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author wangzuxiu
 */
@Path("user")
@RequestScoped
public class UserRESTService {

    @Context
    private UriInfo context;

    private UserService service;
    private ReviewSelectUserService reviewSelectUserService;

    /**
     * Creates a new instance of UserRESTService
     */
    public UserRESTService() {
        service = new UserService();
        reviewSelectUserService = new ReviewSelectUserService();
    }

    /**
     * Retrieves representation of an instance of resource
     *
     * @return an instance of resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getAllUsers() {
        ArrayList<User> urlist = service.findAllUR();
        Users ursList = new Users();
        ursList.setUrList(new ArrayList<User>());

        for (int i = 0; i < urlist.size(); i++) {
            ursList.getUrList().add(
                    new User(urlist.get(i).getId(),
                            urlist.get(i).getPassword(),
                            urlist.get(i).getName(),
                            urlist.get(i).getRoles()));
        }

        return ursList;
    }

    @GET
    @Path("/all/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getUsersByRole(@PathParam("role") String role) {
        String role2 = null;
        try {
            role2 = URLDecoder.decode(role, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ArrayList<User> urlist = reviewSelectUserService.findURByRole(role2);
        Users ursList = new Users();
        ursList.setUrList(new ArrayList<User>());

        for (int i = 0; i < urlist.size(); i++) {
            ursList.getUrList().add(
                    new User(urlist.get(i).getId(),
                            urlist.get(i).getPassword(),
                            urlist.get(i).getName(),
                            urlist.get(i).getRoles()));
        }

        return ursList;
    }

    /**
     * PUT method for updating or creating an instance of resource
     *
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User ur) {
        service.processModify(ur);
    }

    /**
     * POST method for creating an instance of resource
     *
     * @param content representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User ur) throws SQLException {
        String name2;
        boolean flag = true;
        flag = service.checkIsExist(ur);
        System.out.println("create user rest " + flag);
        if (!flag) {
            service.processCreate(ur);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();

    }

    /**
     * DELETE method for deleting an instance of resource
     *
     * @param name name of the resource
     */
    @DELETE
    @Path("/delete/{urname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("urname") String name) throws SQLException {
        String name2;
        boolean flag = true;
        try {
            name2 = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        User user = service.findUR(name2);
        flag = service.checkIsAssigned(user);
        System.out.print("del user rest flag" + flag);
        if (!flag) {
            System.out.print("del user rest ok");
            service.processDelete(name2);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}
