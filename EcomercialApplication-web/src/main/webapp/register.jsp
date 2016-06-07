<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>

    <h1>Create an Account record</h1>
    <form id="registerForm" action="Register" method="post">
    <table>
        <tr><td>First Name:</td><td><input type="text" id = "fn" name="firstName" /></td></tr>
        <tr><td>Last Name:</td><td><input type="text" id = "ln" name="lastName" /></td></tr>
         <tr><td>Gender:</td><td><input type="text" id = "gender" name="Gender" /></td></tr>
          <tr><td>Age:</td><td><input type="text" id = "age" name="age" /></td></tr>
          <tr><td>Email:</td><td><input type="text" id = "age" name="email" /></td></tr>
          <tr><td>Password:</td><td><input type="text" id = "age" name="password" /></td></tr>
    </table>
    <input type="submit" id="Register" value="Register" />
    </form>
   
</body>
</html>
