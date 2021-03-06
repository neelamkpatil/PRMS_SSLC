package sg.edu.nus.iss.phoenix.authenticate.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import java.util.logging.*;
import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;

import sg.edu.nus.iss.phoenix.user.dao.RoleDao;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.user.entity.Role;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

public class AuthenticateService {

    private static final Logger logger
            = Logger.getLogger(AuthenticateService.class.getName());

    DAOFactoryImpl factory;
    UserDao udao;
    RoleDao rdao;
    AuditTrailDao audao;

    public AuthenticateService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        udao = factory.getUserDAO();
        rdao = factory.getRoleDAO();
        audao = factory.getAuditTrailDAO();


    }

    //The user that we pass in to authenticate is different from the
    //instance that is returned
    public User validateUserIdPassword(final User toAuth) {
        User found = null;
        try {
//            found = udao.searchMatching(toAuth.getId());
            found = udao.authUser(toAuth);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "user searchMatching", ex);
            return (null);
        } catch (NotFoundException ex) {
            logger.log(Level.SEVERE, "user searchMatching", ex);

        }
        try {
            if (null == found) {
                audao.auditLogin(toAuth.getId(), false);
                return (null);
            } else {
                audao.auditLogin(found.getId(), true);
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "login audit error", ex);
        }

        //Populate the roles
        try {
            for (Role r : found.getRoles()) {
                Role _role = rdao.searchMatching(r.getRole());
                //Should we do something with roles that are without access privilege?
                if (null != _role) {
                    r.setAccessPrivilege(_role.getAccessPrivilege());
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "user searchMatching", ex);
        }

        return (found);
    }

}
