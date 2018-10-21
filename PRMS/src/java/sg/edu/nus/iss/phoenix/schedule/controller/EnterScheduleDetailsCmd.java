/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Action("enterps")
public class EnterScheduleDetailsCmd implements Perform {

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ScheduleDelegate del = new ScheduleDelegate();
        ProgramSlot ps = new ProgramSlot();
//        ProgramSlot oldPs = new ProgramSlot();

        ps.setRpname(req.getParameter("rpname"));
        ps.setPresenter(req.getParameter("presenter"));
        ps.setProducer(req.getParameter("producer"));

        String date = req.getParameter("date");
        Date d_date = Date.valueOf(date);
        ps.setDate(d_date);

//        String oldDate = req.getParameter("oldDate");
//        System.out.print("oldDate + " + oldDate);
//        Date d_oldDate = Date.valueOf(oldDate);
//        oldPs.setDate(d_oldDate);
        String sttime = req.getParameter("sttime");
        System.out.print("sttime + " + sttime);
        Time t_sttime = Time.valueOf(sttime);
        ps.setSttime(t_sttime);

//        String oldSttime = req.getParameter("oldSttime");
//        System.out.print("oldSttime + " + oldSttime);
//        Time t_oldSttime = Time.valueOf(oldSttime);
//        oldPs.setSttime(t_oldSttime);
        String dur = req.getParameter("duration");
        Time t_dur = Time.valueOf(dur);
        ps.setDuration(t_dur);

        System.out.println(ps.toString());

        String ins = (String) req.getParameter("ins");
        Logger.getLogger(getClass().getName()).log(Level.INFO,
                "Insert Flag: " + ins);

//        del.checkScheduleOverlap(d_date, t_sttime, t_dur)
        if (true) {
            if (ins.equalsIgnoreCase("true")) {
                del.processCreate(ps);
            } else {
                ps.setId(Integer.valueOf(req.getParameter("id")));
                del.processModify(ps);

            }
            ReviewSelectScheduleDelegate psdel = new ReviewSelectScheduleDelegate();
            List<ProgramSlot> data = psdel.reviewSelectSchedule();
            req.setAttribute("pss", data);
            return "/pages/crudps.jsp";

        } else {
//            req.setAttribute("err", "true");
            return "/pages/setupps.jsp";
        }

//        if (ins.equalsIgnoreCase("true")) {
//            del.processCreate(ps);
//        } else {
//            del.processModify(ps, oldPs);
//
//        }
//
//        ReviewSelectScheduleDelegate psdel = new ReviewSelectScheduleDelegate();
//        List<ProgramSlot> data = psdel.reviewSelectSchedule();
//        req.setAttribute("pss", data);
//        return "/pages/crudps.jsp";
    }
}
