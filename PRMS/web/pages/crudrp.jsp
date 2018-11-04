<%@page import="sg.edu.nus.iss.phoenix.user.entity.Role"%>
<%@ page import="sg.edu.nus.iss.phoenix.user.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="crudrpPage"/>
<body>
    <%
        User user;
        boolean flag = false;
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        if (session.getAttribute("user") == null) {
            response.sendRedirect("inaccessible");          
        } else {
            user = (User) session.getAttribute("user");
            for (Role role : user.getRoles()) {
                if (role.getRole().equals("manager")) {                    
                    flag = true;                                        
                    break;
                }
            }
            if (!flag){
                response.sendRedirect("inaccessible");
            }
        }
    %>     

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        var lastActiveTime = new Date().getTime();
        $(document).ready(function () {
            $('body').bind('click mousemove keypress scroll resize', function () {
                lastActiveTime = new Date().getTime();
            });
            setInterval(checkIdleTime, 30000); // 30 sec
        });

        function checkIdleTime() {
            var diff = new Date().getTime() - lastActiveTime;
            if (diff > 1200000) {//20 min of inactivity
                window.location.href = "/logout?msg=onTimeOut"
            } else {
                $.ajax({url: '/refreshSession', error: function (data, status, xhr) {
                        alert("cannot refresh session on server: " + xhr);
                        window.location.reload();
                    }
                });
            }
        }
    </script>
</body>