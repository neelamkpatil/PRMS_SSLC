/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author wangzuxiu
 */
public class UserService {

    DAOFactoryImpl factory;
    UserDao urdao;

    public UserService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        urdao = factory.getUserDAO();
    }

    public ArrayList<User> searchPrograms(User urso) {
        ArrayList<User> list = new ArrayList<User>();
        try {
            list = (ArrayList<User>) urdao.searchMatching(urso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<User> findURByCriteria(User ur) {
        ArrayList<User> currentList = new ArrayList<User>();

        try {
            currentList = (ArrayList<User>) urdao.searchMatching(ur);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return currentList;

    }

    public User findUR(String urId) {
        User currentur = new User();

        try {
            currentur = (User) urdao.searchMatching(urId);
            return currentur;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentur;

    }

    public ArrayList<User> findAllUR() {
        ArrayList<User> currentList = new ArrayList<User>();
        try {
            currentList = (ArrayList<User>) urdao.loadAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;

    }

    public void processCreate(User ur) {
        try {
            urdao.create(ur);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void processModify(User ur) {

        try {
            urdao.save(ur);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void processDelete(String name) {

        try {
            User ur = new User(name);
            urdao.delete(ur);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean checkIsAssigned(User user) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.

        boolean flag = urdao.checkIsAssigned(user);

        return flag;
    }

    public boolean checkIsExist(User user) throws SQLException {
        //To change body of generated methods, choose Tools | Templates.

        boolean flag = urdao.checkIsExist(user);

        return flag;
    }

}
