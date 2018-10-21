<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <link href="<c:url value='/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <fmt:setBundle basename="ApplicationResources" />
        <title> <fmt:message key="title.crudps"/> </title>
    </head>
    <body>
        <h1><fmt:message key="label.crudps"/></h1>

        <c:url var="url" scope="page" value="/nocturne/addeditps">
            <c:param name="id" value=""/>
            <c:param name="rpname" value=""/>
            <c:param name="date" value=""/>
            <c:param name="sttime" value=""/>
            <c:param name="duration" value=""/>
            <c:param name="presenter" value=""/>
            <c:param name="producer" value=""/>
            <c:param name="insert" value="true"/>
            <%--<c:param name="error" value="false"/>--%>
        </c:url>
        <a href="${url}"><fmt:message key="label.crudps.add"/></a>
        <br/><br/>
        <table class="borderAll">
            <tr>
                <th><fmt:message key="label.crudps.rpname"/></th>
                <th><fmt:message key="label.crudps.date"/></th>
                <th><fmt:message key="label.crudps.sttime"/></th>
                <th><fmt:message key="label.crudps.duration"/></th>
                <th><fmt:message key="label.crudps.presenter"/></th>
                <th><fmt:message key="label.crudps.producer"/></th>
                <th><fmt:message key="label.crudps.edit"/><fmt:message key="label.crudps.delete"/></th>
            </tr>
            <c:forEach var="crudps" items="${pss}" varStatus="status">
                <tr class="${status.index%2==0?'even':'odd'}">
                    <td class="nowrap">${crudps.rpname}</td>
                    <td class="nowrap">${crudps.date}</td>
                    <td class="nowrap">${crudps.sttime}</td>
                    <td class="nowrap">${crudps.duration}</td>
                    <td class="nowrap">${crudps.presenter}</td>
                    <td class="nowrap">${crudps.producer}</td>
                    <%--<h1>Empty</h1>--%>
                    <td class="nowrap">
                        <c:url var="updurl" scope="page" value="/nocturne/addeditps">
                            <c:param name="id" value="${crudps.id}"/>
                            <c:param name="rpname" value="${crudps.rpname}"/>
                            <c:param name="date" value="${crudps.date}"/>
                            <%--<c:param name="oldDate" value="${crudps.date}"/>--%>
                            <c:param name="sttime" value="${crudps.sttime}"/>
                            <%--<c:param name="oldSttime" value="${crudps.sttime}"/>--%>
                            <c:param name="duration" value="${crudps.duration}"/>
                            <c:param name="presenter" value="${crudps.presenter}"/>
                            <c:param name="producer" value="${crudps.producer}"/>
                            <c:param name="insert" value="false"/>
                            <%--<c:param name="error" value="false"/>--%>
                        </c:url>
                        <a href="${updurl}"><fmt:message key="label.crudps.edit"/></a>
                        &nbsp;&nbsp;&nbsp;
                        <c:url var="delurl" scope="page" value="/nocturne/deleteps">
                            <c:param name="id" value="${crudps.id}"/>                   
                        </c:url>
                        <a href="${delurl}"><fmt:message key="label.crudps.delete"/></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>