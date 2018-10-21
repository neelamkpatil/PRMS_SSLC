<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <fmt:setBundle basename="ApplicationResources" />
        <title> <fmt:message key="title.viewrp"/> </title>
    </head>
    <body>
        <h1><fmt:message key="label.viewrp"/></h1>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudrp.name"/></th>
                <th><fmt:message key="label.crudrp.description"/></th>
                <th><fmt:message key="label.crudrp.duration"/></th>
            </tr>
            <c:forEach var="crudrp" items="${rps}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${crudrp.name}</td>
                    <td class="nowrap">${crudrp.description}</td>
                    <td class="nowrap">${crudrp.typicalDuration}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>