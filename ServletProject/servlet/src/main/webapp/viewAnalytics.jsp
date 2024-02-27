<%@ page import="database.CourseINFO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Analytics</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        .analytics-container {
            text-align: center;
            background-color: lightblue;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="analytics-container">

    <h1>
        Min Mark: <%= ((Double)request.getAttribute("minMark")).toString() %>
    </h1>

    <h1>
        Max Mark: <%= ((Double)request.getAttribute("maxMark")).toString() %>
    </h1>

    <h1>
             Median: <%= String.format("%.2f", (Double)request.getAttribute("median")) %>
    </h1>

    <h1>
         Average: <%= String.format("%.2f", (Double)request.getAttribute("average")) %>
    </h1>

</div>
</body>
</html>
