<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>choose</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

 <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
 h1 {
         text-align: center;
         color: lightblue;
         margin-top: 40px;
       }

        form {
            background-color: lightblue;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            max-width: 300px;
            width: 100%;
        }

        label {
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: white;
            color: black;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }


    </style>
</head>
<body>
<form action="<%=(String)request.getAttribute("action")%>" method="post">
    <label for="course"> Choose Course </label>
    <select name="course" id="course">
        <option value="arabic"> Arabic </option>
        <option value="english"> English </option>
        <option value="spanish"> Spanish </option>
    </select>
    <p>
        <input type="submit" value="Submit" />
    </p>
</form>
</body>
</html>