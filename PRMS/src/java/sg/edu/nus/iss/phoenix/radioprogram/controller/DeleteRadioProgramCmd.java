/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author boonkui
 */
@Action("deleterp")
public class DeleteRadioProgramCmd implements Perform {
    
    private DAOFactoryImpl factory = new DAOFactoryImpl();
    private AuditTrailDao audao = factory.getAuditTrailDAO();

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ProgramDelegate del = new ProgramDelegate();
        String name = req.getParameter("name");
        User user = getCurrentUser(req);
        String userId = (user == null ? "null" : user.getId());
        try {
            del.processDelete(name);
            audao.auditMaintain("delete program", userId, true, "name=" + name);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteRadioProgramCmd.class.getName()).log(Level.SEVERE, 
                    "Maintain program audit error", ex);
        }

        ReviewSelectProgramDelegate rsDel = new ReviewSelectProgramDelegate();
        List<RadioProgram> data = rsDel.reviewSelectRadioProgram();
        req.setAttribute("rps", data);
        return "/pages/crudrp.jsp";
    }
    
    private User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        return user;
    }
}
