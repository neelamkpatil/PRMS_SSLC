package sg.edu.nus.iss.phoenix.audittrail.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.audittrail.dao.AuditTrailDao;

/**
 *
 * @author Peiyan
 */
public class AuditTrailDaoImpl implements AuditTrailDao {

    Connection connection;

    @Override
    public void auditLogin(String userId, boolean isSuccess) throws SQLException {
        String sql;
        if (isSuccess) {
            sql = "INSERT INTO `login-audit` ( `dateTime`, `user`, `appUserId`,  `operation`, `isSuccess`, `role` ) \n"
                    + "SELECT now(), user(), ?, 'login', 'yes', role FROM user WHERE id = ?; ";
        } else {
            sql = "INSERT INTO `login-audit` ( `dateTime`, `user`, `appUserId`,  `operation`, `isSuccess`) \n"
                    + "VALUES (now(), user(), ?, 'login', 'no'); ";
        }

        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userId);
            if (isSuccess) {
                stmt.setString(2, userId);
            }

            System.out.println(stmt.toString());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("Add Login Aduit Trail Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }

    @Override
    public void auditLogout(String userId, boolean isSuccess) throws SQLException {
        String sql;
        sql = "INSERT INTO `login-audit` ( `dateTime`, `user`, `appUserId`,  `operation`, `isSuccess`) \n"
                + "VALUES (now(), user(), ?, 'logout', 'yes'); ";

        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userId);

            System.out.println(stmt.toString());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("Add Logout Aduit Trail Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
    
    @Override
    public void auditMaintain(String operation, String userId, boolean isSuccess, String key) throws SQLException {
        String sql;
        sql = "INSERT INTO `maintain-audit` ( `dateTime`, `user`, `appUserId`,  `operation`, `isSuccess`, `key`) \n"
                + "VALUES (now(), user(), ?, ?, ?, ?); ";

        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, operation);
            if (isSuccess) {
                stmt.setString(3, "yes");
                stmt.setString(4, key);
                
            } else {
                stmt.setString(3, "no");
            }

            System.out.println(stmt.toString());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("Add" + operation + "Aduit Trail Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }

    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
