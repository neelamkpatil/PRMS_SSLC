<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <fmt:setBundle basename="ApplicationResources" />

        <title><fmt:message key="title.setuprp" /></title>
    </head>
    <body>
        <h1><fmt:message key="label.setuprp"/></h1>

        <form action="${pageContext.request.contextPath}/nocturne/enterrp" method=post>
            <%--<center>--%>
            <table cellpadding=4 cellspacing=2 border=0>				
                <tr>
                    <td><fmt:message key="label.crudrp.name" /></td>
                    <td><c:if test="${param['insert'] == 'true'}">
                            <input type="text" name="name" value="${param['name']}" size=15
                                   maxlength=20>
                            <span class="error">${messageName.enterrp}</span>
                            <input type="hidden" name="insert" value="true" />
                        </c:if> 
                        <c:if test="${param['insert']=='false'}">
                            <input type="text" name="name" value="${param['name']}" size=15
                                   maxlength=20 readonly="readonly">
                            <input type="hidden" name="insert" value="false" />
                        </c:if>
                        <c:if test="${programExist == 'false'}">
                            <font color="red">* This radio program already exists!</font>
                            </c:if>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudrp.description" /></td>
                    <td><input type="text" name="description"
                               value="${param['description']}" size=45 maxlength=45>
                        <span class="error">${messageDescription.enterrp}</span>
                        <c:if test="${isDescVaild == 'false'}">
                            <font color="red">* Please input a valid description!</font>
                            </c:if>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="label.crudrp.duration" />(hh:mm:ss)</td>
                    <td><input type="text" name="typicalDuration"
                               value="${param['typicalDuration']}" size=15 maxlength=8>
                        <c:if test="${isDurationVaild == 'false'}">
                            <font color="red">* Please input a valid duration!</font>
                            </c:if>

                    </td>
                </tr>
                <!--<tr><td><font color="blue">${param['insert']}</font></td></tr>-->
                <tr>
                    <td><input type="submit" name="submit" value="Submit"></td>
                    <td><input type="reset" name="reset" value="Reset"></td>
                </tr>
            </table>
            <%--</center>--%>
        </form>

    </body>
</html>