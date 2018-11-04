<%@page import="sg.edu.nus.iss.phoenix.user.entity.Role"%>
<%@page import="sg.edu.nus.iss.phoenix.user.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="crudpsPage"/>
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
            if (!flag) {
                response.sendRedirect("inaccessible");
            }
        }
    %>   


</body>