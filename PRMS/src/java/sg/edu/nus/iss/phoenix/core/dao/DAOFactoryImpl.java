package sg.edu.nus.iss.phoenix.core.dao;

import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;
import sg.edu.nus.iss.phoenix.audittrail.dao.impl.AuditTrailDaoImpl;
import sg.edu.nus.iss.phoenix.user.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.user.dao.impl.RoleDaoImpl;
import sg.edu.nus.iss.phoenix.user.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.dao.impl.ProgramDAOImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDAOImpl;

public class DAOFactoryImpl implements DAOFactory {

    private UserDao userDAO = new UserDaoImpl();
    private RoleDao roleDAO = new RoleDaoImpl();
    private ProgramDAO rpdao = new ProgramDAOImpl();
    private ScheduleDAO psdao = new ScheduleDAOImpl();
    private AuditTrailDao audao = new AuditTrailDaoImpl();

    @Override
    public UserDao getUserDAO() {
        // TODO Auto-generated method stub
        return userDAO;
    }

    @Override
    public RoleDao getRoleDAO() {
        // TODO Auto-generated method stub
        return roleDAO;
    }

    @Override
    public ProgramDAO getProgramDAO() {
        // TODO Auto-generated method stub
        return rpdao;
    }

    @Override
    public ScheduleDAO getScheduleDAO() {
        // TODO Auto-generated method stub
        return psdao;
    }

    @Override
    public AuditTrailDao getAuditTrailDAO() {
        // TODO Auto-generated method stub
        return audao;
    }

}
