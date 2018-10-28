/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author boonkui
 */
@Action("logout")
public class LogoutCmd implements Perform {

    private DAOFactoryImpl factory = new DAOFactoryImpl();
    private AuditTrailDao audao = factory.getAuditTrailDAO();

    //  @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        try {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                audao.auditLogout(user.getId(), true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogoutCmd.class.getName()).log(Level.SEVERE, "logout audit error", ex);
        }
        session.invalidate();
        return "/pages/logout.jsp";
    }
}
