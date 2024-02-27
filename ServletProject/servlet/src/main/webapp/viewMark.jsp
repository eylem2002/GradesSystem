<%@ page import="database.CourseINFO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }


        h1 {
            text-align: center;
            font-size: 24px;
            background-color: lightblue;
            padding: 10px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<h1>
    <%= ((CourseINFO)request.getAttribute("mark")).toString() %>
</h1>
</body>
</html>
