<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="ApplicationResources" />

<h3 align="center">
    <fmt:message key="caption.menu" />
</h3>

<table class="framed">
    <tr>
        <c:if test="${empty sessionScope.user}">
            <td><a href="<c:url value="/pages/login.jsp"/>"> <fmt:message
                        key="caption.menu.login" />
                </a></td>
            </c:if>
    </tr>

    <c:set var="flag" value="0"/>
    <c:forEach var="roles" items="${sessionScope.user.roles}" varStatus="status">
        <c:if test="${roles.role=='manager'}">
            <tr>
                <td>
                    <a href="<c:url value="/nocturne/managerp"/>"> <fmt:message
                            key="caption.menu.managerp" />
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="<c:url value="/nocturne/manageps"/>"> <fmt:message
                            key="caption.menu.manageps" />
                    </a>
                </td>
            </tr>
        </c:if>
        <c:if test="${roles.role=='admin'}">
            <tr>
                <td>
                    <a href="<c:url value="/nocturne/manageur"/>"> <fmt:message
                            key="caption.menu.manageur" />
                    </a>
                </td>
            </tr>
        </c:if>
        <c:if test="${roles.role=='presenter' || roles.role=='producer'}">
            <c:if test="${flag=='0'}">
                <tr>
                    <td>
                        <a href="<c:url value="/nocturne/viewrp"/>"> <fmt:message
                                key="caption.menu.viewrp" />
                        </a>
                    </td>
                </tr>
                <c:set var="flag" value="1"/>
            </c:if>

        </c:if>
    </c:forEach>

    <tr>
        <c:if test="${not empty sessionScope.user}">
            <td><a href="<c:url value="/nocturne/logout"/>"> <fmt:message
                        key="caption.menu.logout" />
                </a></td>
            </c:if>
    </tr>

</table>


