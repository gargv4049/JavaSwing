<!DOCTYPE html>
<html>
<head>
    <title>Login Result</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #eef7f2, #d8f3e5);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            width: 380px;
            background: white;
            padding: 35px 30px;
            border-radius: 16px;
            text-align: center;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        .icon {
            width: 65px;
            height: 65px;
            margin: 0 auto 18px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
        }

        .success-icon {
            background: #d1e7dd;
            color: #198754;
        }

        .error-icon {
            background: #f8d7da;
            color: #dc3545;
        }

        h2 {
            color: #198754;
            margin-bottom: 10px;
        }

        h3 {
            margin: 8px 0;
            color: #333;
        }

        .error {
            color: #dc3545;
            font-weight: bold;
            margin: 15px 0;
        }

        .success {
            color: #198754;
            font-weight: bold;
        }

        .welcome {
            color: #555;
            margin-bottom: 25px;
        }

        .btn {
            display: inline-block;
            text-decoration: none;
            background: #198754;
            color: white;
            padding: 11px 25px;
            border-radius: 7px;
            font-weight: bold;
            transition: 0.3s;
        }

        .btn:hover {
            background: #146c43;
            transform: translateY(-2px);
        }

        .footer {
            margin-top: 20px;
            font-size: 13px;
            color: #777;
        }
    </style>
</head>

<body>

<div class="card">

<%
    String error = (String) request.getAttribute("error");
    String username = (String) request.getAttribute("username");

    if (error != null) {
%>

    <div class="icon error-icon">✕</div>

    <h2 style="color:#dc3545;">Login Failed</h2>

    <h3 class="error"><%= error %></h3>

    <a href="index.jsp" class="btn">Try Again</a>

<%
    } else if (username != null) {
%>

    <div class="icon success-icon">✓</div>

    <h2 class="success">Login Successful!</h2>

    <h3>Welcome, <%= username %>!</h3>

    <p class="welcome">
        You have successfully logged into your account.
    </p>

    <a href="index.jsp" class="btn">Logout</a>

<%
    }
%>

    <div class="footer">
        Student Login System
    </div>

</div>

</body>
</html>