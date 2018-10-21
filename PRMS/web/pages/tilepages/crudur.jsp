<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <fmt:setBundle basename="ApplicationResources" />
        <title> <fmt:message key="title.crudur"/> </title>
    </head>
    <body>
        <h1><fmt:message key="label.crudur"/></h1>

        <a href=""><fmt:message key="label.crudur.add"/></a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudur.id"/></th>
                <th><fmt:message key="label.crudur.name"/></th>
                <th><fmt:message key="label.crudur.role"/></th>
                <th><fmt:message key="label.crudur.edit"/> <fmt:message key="label.crudur.delete"/></th>
            </tr>
            <c:forEach var="crudur" items="${urs}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${crudur.id}</td>
                    <td class="nowrap">${crudur.name}</td>
                    <td class="nowrap">
                        <c:forEach var="roles" items="${crudur.roles}" varStatus="status">
                            <c:out value="${roles.role}"/>
                        </c:forEach>
                    </td>
                    <td class="nowrap">
                        <a href="${updurl}"><fmt:message key="label.crudur.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        <a href=""><fmt:message key="label.crudur.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>