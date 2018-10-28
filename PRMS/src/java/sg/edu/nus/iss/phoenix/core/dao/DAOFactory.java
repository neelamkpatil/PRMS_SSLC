/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;
import sg.edu.nus.iss.phoenix.user.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;

/**
 *
 * @author projects
 */
public interface DAOFactory {

    ProgramDAO getProgramDAO();

    ScheduleDAO getScheduleDAO();

    RoleDao getRoleDAO();

    UserDao getUserDAO();
    
    AuditTrailDao getAuditTrailDAO();

}
