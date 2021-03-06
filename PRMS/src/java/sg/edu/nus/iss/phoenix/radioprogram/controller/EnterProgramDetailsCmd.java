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
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Action("enterrp")
public class EnterProgramDetailsCmd implements Perform {

    private DAOFactoryImpl factory = new DAOFactoryImpl();
    private AuditTrailDao audao = factory.getAuditTrailDAO();

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        boolean error = false;
        String programExist = "";
        String isDescVaild = "";
        String isDurationVaild = "";
        Map<String, String> messageName = new HashMap<String, String>();
        Map<String, String> messageDescription = new HashMap<String, String>();
        req.setAttribute("messageName", messageName);
        req.setAttribute("messageDescription", messageDescription);
        ProgramDelegate del = new ProgramDelegate();
        RadioProgram rp = new RadioProgram();
        String rpname = req.getParameter("name");
        String rpDescription = req.getParameter("description");
        if (!rpname.matches("^(\\w+ ?)*$")) {
            messageName.put("enterrp", "* Please enter alphanumeric characters only");
            error = true;
        } else {
            if (rpname.length() > 15) {
                messageName.put("enterrp", "* Max limit is 15");
                error = true;
            } else {
                rp.setName(req.getParameter("name"));
            }
        }
        if (rpDescription.length() > 45) {
            messageDescription.put("enterrp", "* Max limit is 45");
            error = true;
        } else {
            rp.setDescription(req.getParameter("description"));
        }
        String dur = req.getParameter("typicalDuration");
//        System.out.println(rp.toString());
        Time t;
        try {
            t = Time.valueOf(dur);
            rp.setTypicalDuration(t);
        } catch (Exception e) {
            error = true;
            req.setAttribute("isDurationVaild", "false");
        }
        String ins = (String) req.getParameter("insert");
        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Insert Flag: " + ins);

        User user = getCurrentUser(req);
        String userId = (user == null ? "null" : user.getId());

        try {
            if (!error) {
                if (ins.equalsIgnoreCase("true")) {
                    if (!del.checkIsExist(rp)) {
                        del.processCreate(rp);
                        audao.auditMaintain("create program", userId, true, "name=" + rp.getName());
                    } else {
                        req.setAttribute("programExist", "false");
                        return "/pages/setuprp.jsp";
                    }
                } else {
                    del.processModify(rp);
                    audao.auditMaintain("update program", userId, true, "name=" + rp.getName());
                }
                ReviewSelectProgramDelegate rsdel = new ReviewSelectProgramDelegate();
                List<RadioProgram> data = rsdel.reviewSelectRadioProgram();
                req.setAttribute("rps", data);
                return "/pages/crudrp.jsp";
            } else {
                System.out.println(error);
            }

            return "/pages/setuprp.jsp";

        } catch (SQLException ex) {
            Logger.getLogger(EnterProgramDetailsCmd.class.getName()).log(Level.SEVERE,
                    "Maintain program audit error", ex);
        }
        return "/pages/setuprp.jsp";
    }

    private User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        return user;
    }
}
