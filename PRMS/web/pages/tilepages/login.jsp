<%@page import="java.util.Base64"%>
<%@page import="java.security.KeyPairGenerator"%>
<%@page import="java.security.KeyPair"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.security.interfaces.RSAPrivateKey"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
    <head>
        <fmt:setBundle basename="ApplicationResources" />
        <c:set var="t" value="true" />
        <title><fmt:message key="title.login" /></title>
        <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="jsencrypt.min.js"></script>
        <script type="text/javascript" src="jsencrypt.js"></script>
        <script type="text/javascript">
            var encrypted;

            function Formsubmit() {

                var pwd = document.getElementById("password").value;

                var publickey = $('#publickey').val();
                var encrypt = new JSEncrypt();
                encrypt.setPublicKey(publickey);
                var encrypted = encrypt.encrypt(pwd);
                $('#encrypted').val(encrypted);
            <%
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(1024);
                KeyPair keyPair = kpg.generateKeyPair();
                String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

                request.setAttribute("privatekey", privateKey);
                request.setAttribute("publickey", publicKey);
            %>
                document.getElementById("myForm").submit();

            }

            function enc(pubkey, pwd) {
                // Encrypt with the public key
                var encrypt = new JSEncrypt();
                encrypt.setPublicKey(pubkey);
                encrypted = encrypt.encrypt(pwd);
                return encrypted;
            }

        </script>
    </head>
    <body>

        <form id="myForm" action="${pageContext.request.contextPath}/nocturne/login" method="post">
            <h2>
                <fmt:message key="title.login" />
            </h2>
            <table >
                <tr>
                    <td><fmt:message key="fieldLabel.username" /></td>
                    <td><input type="text" name="id" id="id" value="${fn:escapeXml(param['name'])}"
                               size=15 maxlength=20></td>
                </tr>
                <tr>
                    <td><fmt:message key="fieldLabel.password" /></td>         
                    <td><input type="password" id="password"
                               value="${fn:escapeXml(param['password'])}" size=15 maxlength=20></td>
                </tr>
                <tr >
                    <td colspan="2" align="center">
                        <input type="submit" name="submit" value="Submit" onclick="Formsubmit()"> &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" name="reset" value="Reset"></td>
                </tr>
            </table>
            <input type="hidden" name="privatekey" id="privatekey" value="${privatekey}">
            <input type="hidden" name="encrypted" id="encrypted">
            <input type="hidden" id="publickey" value="${publickey}" />



        </form>
    </body>