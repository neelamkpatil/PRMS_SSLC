package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

public class ScheduleService {

    DAOFactoryImpl factory;
    ScheduleDAO psdao;

    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed. mia what????
        factory = new DAOFactoryImpl();
        psdao = factory.getScheduleDAO();
    }

    public ArrayList<ProgramSlot> findAllPS() {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) psdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;

    }

    public boolean processCreate(ProgramSlot ps) {
        try {
            if (!psdao.checkOverlap(ps)) {
                System.out.println("Service create " + ps.getRpname());
                psdao.create(ps);
                return true;
            } else {
                System.out.println("service create false");
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public boolean processModify(ProgramSlot ps) {

        try {
            System.out.println(ps.toString());

            if (!psdao.checkOverlap(ps)) {
                System.out.println("Service update " + ps.getId());
                psdao.save(ps);
                return true;
            } else {
                System.out.println("service update false");
                return false;
            }

        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public void processDelete(int id) {

        try {

            psdao.delete(id);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
