/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author Neelam
 */
@Action("inaccessible")
public class Inaccessible implements Perform {
    
     public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate ad = new AuthenticateDelegate();
       User user=(User) req.getSession().getAttribute("user");
       
       
        if (null != user) {
            req.setAttribute("msg", "noPrivilege");
        } else {
            req.setAttribute("msg", "noAccess");
        }
        return "/pages/inaccessible.jsp";
    }
}
