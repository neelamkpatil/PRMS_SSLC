/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.service.PasswordService;
import sg.edu.nus.iss.phoenix.authenticate.util.RSACode;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 * @author boonkui
 */
@Action("login")
public class LoginCmd implements Perform {
    private HttpSession session;
    private int loginAttempts;
    private String userPwd;
    //  @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate ad = new AuthenticateDelegate();
        int timeout=20;
        User user = new User();
        String userId=req.getParameter("id");
        String userPwd_encrypted=req.getParameter("encrypted");
//        String hello=(String) req.getParameter("encrypt");
//        System.out.print(hello);
        System.out.print(userPwd_encrypted);
        //pwd decrypt
        String privatekey=req.getParameter("privatekey");
        System.out.print(privatekey);
        
        try {
            byte [] decodedPwd=RSACode.decryptByPrivateKey(Base64.getDecoder().decode(userPwd_encrypted),privatekey);
            userPwd=new String(decodedPwd);
            System.out.print(userPwd);
        } catch (Exception ex) {
            Logger.getLogger(LoginCmd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session = req.getSession();
        
        if(session.getAttribute("loginAttempts") == null){
            loginAttempts = 0;
            session.setAttribute("loginAttempts", loginAttempts);
            //System.out.print(loginAttempts);
	}
	if(loginAttempts > 2){
            String errorMessage = "Error: Number of Login Attempts Exceeded";
            req.setAttribute("errorMessage", errorMessage);
            //System.out.print(loginAttempts);
            return "/pages/login.jsp";
       
	}
        else{
            PasswordService pws=new PasswordService();
            String hashedPwd=pws.encrypt(userPwd); //compute hash
            System.out.print(hashedPwd);
            user.setId(userId);
            user.setPassword(hashedPwd);
            //user.setPassword(userPwd_encrypted);
            user = ad.validateUserIdPassword(user);
            if (null != user) {
                session.invalidate();
                session=req.getSession(true);
                req.getSession().setAttribute("user", user);
                session.setMaxInactiveInterval(timeout);
                //resp.setHeader("Refresh", timeout + "; URL=/pages/login.jsp");
                
                return "/pages/home.jsp";
            } else {
                String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginAttempts));
		req.setAttribute("errorMessage", errorMessage);
                loginAttempts=loginAttempts+1;
                session.setAttribute("loginAttempts", loginAttempts);
                //System.out.print(loginAttempts);
                return "/pages/error.jsp";
        }
        }
    }
}
