package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ReviewSelectScheduleService {

    DAOFactoryImpl factory;
    ScheduleDAO psdao;

    public ReviewSelectScheduleService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }

    public List<ProgramSlot> reviewSelectSchedule() {
        List<ProgramSlot> data = null;
        try {
            data = psdao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
