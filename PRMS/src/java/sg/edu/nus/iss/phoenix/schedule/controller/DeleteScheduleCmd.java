/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.schedule.delegate.ScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.delegate.ReviewSelectScheduleDelegate;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Peiyan
 */
@Action("deleteps")
public class DeleteScheduleCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        int id = Integer.valueOf(req.getParameter("id"));

//        String rpname = req.getParameter("rpname");
//        String date = req.getParameter("date");
//        Date d_date = Date.valueOf(date);
//        String sttime = req.getParameter("sttime");
//        Time t_sttime = Time.valueOf(sttime);
        del.processDelete(id);
//        del.processDelete(rpname, d_date, t_sttime);

        ReviewSelectScheduleDelegate psDel = new ReviewSelectScheduleDelegate();
        List<ProgramSlot> data = psDel.reviewSelectSchedule();
        req.setAttribute("pss", data);
        return "/pages/crudps.jsp";
    }
}
