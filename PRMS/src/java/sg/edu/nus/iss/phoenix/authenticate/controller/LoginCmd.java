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
    private String userPwd;

//    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate ad = new AuthenticateDelegate();
        int timeout = 600;
        User user = new User();
        String userId = req.getParameter("id");
        String userPwd_encrypted = req.getParameter("encrypted");
        //pwd decrypt
        String privatekey = req.getParameter("privatekey");

        try {
            byte[] decodedPwd = RSACode.decryptByPrivateKey(Base64.getDecoder().decode(userPwd_encrypted), privatekey);
            userPwd = new String(decodedPwd);
        } catch (Exception ex) {
            Logger.getLogger(LoginCmd.class.getName()).log(Level.SEVERE, null, ex);
        }

        session = req.getSession();

        PasswordService pws = new PasswordService();
        // Compute hash
        String hashedPwd = pws.encrypt(userPwd); 
        System.out.print("hashed pwd: " + hashedPwd);
        user.setId(userId);
        user.setPassword(hashedPwd);

        user = ad.validateUserIdPassword(user);
        if (null != user) {
            session.invalidate();
            session = req.getSession(true);
            req.getSession().setAttribute("user", user);
            session.setMaxInactiveInterval(timeout);

            return "/pages/home.jsp";
        } else {
            return "/pages/error.jsp";
        }
    }
}
