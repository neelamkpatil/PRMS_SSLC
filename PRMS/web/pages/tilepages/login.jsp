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
            //alert(name);
            //var psw = $('#password').val();
            
            //加密密码
            //encrypted = enc(publickey, psw);
            //提交
            //myForm.haha.value=psw;
            //$("#haha").val(uname);
            
            var publickey=$('#publickey').val();
            //var publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJmwSmifYrfvWc7U7LntEWU9z7Puu8V96/NxkPaG426YpTkg9c3zgBioRFrrl3I7q+g5qmN88CkEM4FQhKatwr7uAeOSF9TGhp+6+AOFHDF3ja043HTse03YG/Mmf9uUnPtsQLiiubeRVpZGYgJsmtE+4Uisb0amFkpZ1YWafOkQIDAQAB"
            var encrypt=new JSEncrypt();
            encrypt.setPublicKey(publickey);
            //alert(pwd);
            var encrypted = encrypt.encrypt(pwd);
            //alert(encrypted);
            //$('#haha').val()="hellohahah";
            $('#encrypted').val(encrypted);
            //alert($('#encrypted').val());
            <%
                KeyPairGenerator kpg =KeyPairGenerator.getInstance("RSA");
                kpg.initialize(1024);
                KeyPair keyPair = kpg.generateKeyPair();
                String  publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                String  privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
              
                //String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMmbBKaJ9it+9ZztTsue0RZT3Ps+67xX3r83GQ9objbpilOSD1zfOAGKhEWuuXcjur6DmqY3zwKQQzgVCEpq3Cvu4B45IX1MaGn7r4A4UcMXeNrTjcdOx7Tdgb8yZ/25Sc+2xAuKK5t5FWlkZiAmya0T7hSKxvRqYWSlnVhZp86RAgMBAAECgYEAjoVSIlGBf3o/M/LnMzdQ5JHgtJTknb8YruDKjIPs9lDfAvlMdwEuXJiJraE5Z6oQiEIM/9iQNWW6v1jPBZzJi9qDgvNqHdSl0kMrgOUxR1o81jLAsb2v/YAcVqzyGRNz4JgIs5LL6XOkeSmJl0e99D/dlsu3LHbVP1bn4ZM+yqkCQQD6/ZJQeKfY598a7zT1UTjczRJkvKsZSHW5NvUR5JG6s3NugUS5DBfXubvtA8Nzhgj7hrEOjsSvpEM08IHmx+OXAkEAzaEdl1EuIenSYm07r9Jl++vpDuNMM7gkeYqpL/3kAF9TUf1gptgfUkmawbXLNyn68oV6LVYlJ0jqbThu53wEFwJAPQbwz442yZ+/eK4XA1sHZ3CZe1MAizo6HZnGRt57rJi7hW/7BL9zAKoRjpSurmU1w+rBHFgMbOSUzsd33mRV6wJAYObgLwLOnqS1xDR9wCmCbemKdr2el3Dbn3s8p6UZ4SMK/MRO29FVUU3mZArrrR9XZxqHrP2/nHe8IaRKf0PuGQJAbm93ptzyTMYfze+3agTCBoLoV+WFYv3YHixJqoLunVGJUCFWmCyv5Yo742T9lJp+kVcxnuStyS7RI9M0RBQsvg==";
                request.setAttribute("privatekey", privateKey);
                request.setAttribute("publickey", publicKey);
//                String he=request.getParameter("haha");
//                System.out.print(he);
            %>
            document.getElementById("myForm").submit();
            alert(10);
            
        }
        //加密方法
        function enc(pubkey, pwd) {
            // Encrypt with the public key...
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey(pubkey);
            encrypted = encrypt.encrypt(pwd);
            return encrypted;
        }
        
    </script>
    </head>
    <body>
<!--        action="${pageContext.request.contextPath}/nocturne/login"
              method=post>-->
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
                    <%--<td><input type="password" name="password"--%>
                    <%--value="${param['name']}" size=15 maxlength=20></td>--%>
                    <td><input type="password" name="password" id="password"
                               value="${fn:escapeXml(param['password'])}" size=15 maxlength=20></td>
                </tr>
                <tr >
                    <td colspan="2" align="center"><input type="submit" name="submit" value="Submit" onclick="Formsubmit()"> &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" name="reset" value="Reset"></td>
                </tr>
            </table>
                <input type="hidden" name="privatekey" id="privatekey" value="${privatekey}">
                <input type="hidden" name="encrypted" id="encrypted">
                <input type="hidden" name="publickey" id="publickey" value="${publickey}" />
            
            
            
        </form>
                ${errorMessage}
    </body>