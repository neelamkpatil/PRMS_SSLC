/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author wangzuxiu
 */
public class UserDelegate {

    public ArrayList<User> findAllUR() {
        UserService service = new UserService();
        return service.findAllUR();

    }

    public void processCreate(User ur) {
        UserService service = new UserService();
        service.processCreate(ur);

    }

    public void processModify(User ur) {
        UserService service = new UserService();
        service.processModify(ur);

    }

    public void processDelete(String name) {
        UserService service = new UserService();
        service.processDelete(name);
    }
}
