<%--
  Created by IntelliJ IDEA.
  User: Sim.G
  Date: 2018/10/9
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="/springboot/upload">
    请选择文件： <input type="file" name="file"/> <input type="submit"
                                                   value="上传"/>
</form>

<hr>
<br/> 多个文件上传：
<form action="/springboot/uploads" method="post" enctype="multipart/form-data">
    文件1：<input type="file" name="file"/><br/> 文件2：<input type="file"
                                                         name="file"/><br/> 文件3：<input type="file" name="file"/><br/>
    <input type="submit" value="上传多个文件"/>
</form>
</body>
</html>
