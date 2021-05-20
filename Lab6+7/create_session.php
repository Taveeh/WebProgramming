<?php
require_once 'Controller.php';
session_start();
$errors = 0;
$fields = array("username", "password");
if (isset($_POST['loginButton'])) {
    foreach ($fields as $key => $field) {
        if (!isset($_POST[$field]) && empty($_POST[$field])) {
            echo "please enter username and password\n";
            $errors++;
        }
    }
}

if ($errors <= 0) {
    $password = $_POST['password'];
    $username = $_POST['username'];
    $controller = new Controller();
    if ($controller->checkValidPassword($username, $password)) {
        $_SESSION['username'] = $username;
//        header("Location: userPage.php");
    }else {
        header("Location: index.html");
        echo '<p>Invalid username and password</p>';
    }
}
?>