/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.user.entity.User;
import sg.edu.nus.iss.phoenix.user.service.ReviewSelectUserService;

/**
 *
 * @author wangzuxiu
 */
public class ReviewSelectUserDelegate {

    private ReviewSelectUserService service;

    public ReviewSelectUserDelegate() {
        service = new ReviewSelectUserService();
    }

//    public List<User> reviewSelectUserByRole() {
//		return service.reviewSelectUserByRole();	
//    }
    public List<User> reviewSelectUser() {
        return service.reviewSelectUser();
    }

    public List<User> searchUser(User ur) {
        return service.searchUser(ur);
    }

}
