<%@ page import="database.CourseINFO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
   <style>
           h1 {
               text-align: center;
               color: #333;
               margin-top: 50px;
           }

           table {
               width: 80%;
               border-collapse: collapse;
               margin: 30px auto;
           }

           th, td {
               border: 1px solid #333;
               padding: 10px;
               text-align: center;
           }

           th {
               background-color: lightblue;
               color: black;
           }

           tr:nth-child(even) {
               background-color: #ddd;
           }


       </style>

</head>
<body>
<h1>Student Marks</h1>
<table>
  <thead>
  <tr>
    <th> Course </th>
    <th> Marks </th>
  </tr>
  </thead>
  <tbody>
  <%! ArrayList<CourseINFO> courses = new ArrayList<>(); %>
  <% courses = (ArrayList<CourseINFO>)request.getAttribute("marks"); %>
  <% for (CourseINFO mark : courses) { %>
    <tr>
      <td><%= mark.getName()%></td>
      <td><%= mark.getMark()%></td>
    </tr>
  <% } %>
  </tbody>
</table>
</body>
</html>