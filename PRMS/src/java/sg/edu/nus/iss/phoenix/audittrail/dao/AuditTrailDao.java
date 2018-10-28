package sg.edu.nus.iss.phoenix.audittrail.dao;

import java.sql.SQLException;

/**
 *
 * @author Peiyan
 */
public interface AuditTrailDao {

    /**
     * auditLogin-method. This will audit and log the login operation performed 
     * by the application user. 
     *
     * @param userId
     * @param isSuccess
     * @return
     * @throws java.sql.SQLException
     */
    public abstract void auditLogin(String userId, boolean isSuccess)
            throws SQLException;
    
    /**
     * auditLogout-method. This will audit and log the logout operation performed 
     * by the application user. 
     *
     * @param userId
     * @param isSuccess
     * @return
     * @throws java.sql.SQLException
     */
    public abstract void auditLogout(String userId, boolean isSuccess)
            throws SQLException;
    
    /**
     * auditMaintain-method. This will audit and log the maintain resources operation performed 
     * by the application user. 
     *
     * @param userId
     * @param isSuccess
     * @return
     * @throws java.sql.SQLException
     */
    public abstract void auditMaintain(String operation, String userId, boolean isSuccess, String key)
            throws SQLException;

}
