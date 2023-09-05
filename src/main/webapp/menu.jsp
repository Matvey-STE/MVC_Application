<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Menu</title>
    <link rel="stylesheet" href="menu.css">
</head>
<body>
<header>
    <h1>Hello, ${user.login}, this is your account!</h1>
    <form action="user" method="post">
    </form>
</header>
<main>
    <p>Personal information <a href="/user?login=${user.login}">Personal information</a></p>
    <p><a href="index.jsp">Exit</a></p>
</main>
<footer>
    <p>&copy; 2023 Java Guru Co.</p>
</footer>
</body>
</html>
