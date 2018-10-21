/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.radioprogram.dao.ProgramDAO;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ReviewSelectProgramService;
import sg.edu.nus.iss.phoenix.user.dao.UserDao;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author wangzuxiu
 */
public class ReviewSelectUserService {

    DAOFactoryImpl factory;
    UserDao urdao;

    public ReviewSelectUserService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        urdao = (UserDao) factory.getUserDAO();
    }

    public List<User> reviewSelectUser() {
        List<User> data = null;
        try {
            data = urdao.loadAll();
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ArrayList<User> findURByRole(String role) {
        ArrayList<User> currentList = new ArrayList<User>();
        try {
            currentList = (ArrayList<User>) urdao.loadURByRole(role);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentList;

    }

    public List<User> searchUser(User ur) {
        List<User> data = null;
        try {
            data = urdao.searchMatching(ur);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectProgramService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
