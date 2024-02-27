<!DOCTYPE html>
<html lang = "en">
<head>
    <title> home </title>
    <style>
     body {
         text-align: center;
        }

    h1 {

          color: lightblue;
          margin-top: 40px;
            margin-bottom: 40px;
        }

    h3 {

          color: lightblue;


        }
        #container {
         margin-top: 40px;
          text-align: center;
            display: flex;
            flex-wrap: wrap;
             box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
             justify-content: center;
             align-items: center;
        }


        #container > a {
          text-align: center;
            border-radius: 16px;
            text-decoration: none;
            background-color: lightblue;
            color: black;
            width: auto;
            height: auto;
            margin: 12px;
            padding: 16px;
        }
        h3 {
            margin: 10px;
        }
    </style>
</head>
<body>
<h1> Welcome Student </h1>

<h3 > Choose The service you want : </h3>
<div id = "container">

    <a href="viewMarks"> All marks </a>
    <a href="viewAnalytics">  All Analytics of Grades</a>
    <a href="viewSingle"> Single mark </a>

</div>
</body>
</html>