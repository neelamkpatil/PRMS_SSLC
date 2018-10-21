/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author Peiyan
 */
@Action("searchrp")
public class SearchRadioProgramCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ReviewSelectProgramDelegate del = new ReviewSelectProgramDelegate();
        RadioProgram rp = new RadioProgram();
        rp.setName(req.getParameter("name"));
        rp.setDescription(req.getParameter("description"));
        List<RadioProgram> data = del.searchRadioProgram(rp);
        req.setAttribute("searchrplist", data);
        req.setAttribute("date", req.getParameter("date"));
        req.setAttribute("oldDate", req.getParameter("oldDate"));
        req.setAttribute("sttime", req.getParameter("sttime"));
        req.setAttribute("oldSttime", req.getParameter("oldSttime"));
        req.setAttribute("duration", req.getParameter("duration"));
        req.setAttribute("presenter", req.getParameter("presenter"));
        req.setAttribute("producer", req.getParameter("producer"));
        req.setAttribute("insert", req.getParameter("insert"));
        return "/pages/searchrp.jsp";
    }
}
