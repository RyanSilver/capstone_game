<?php
session_start();
include 'includes/connect.php';
include 'timeout.php';
$url = "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];




//check for form submissions
if (isset($_POST['login']) || isset($_POST['confirm'])) {

    $inactive = 600;
    $username = $db->escapeString($_POST['username']);
    $password = $db->escapeString($_POST['password']);
    $result   = $db->query("SELECT * FROM user WHERE username = '$username'");
    $row      = $result->fetchArray();
    $hash_pwd = $row['password'];
    $hash     = password_verify($password, $hash_pwd);
    if ($hash == 0 && strpos($url, '=confirm') !== false) {
        header('Location: accountsettings.php?error=incorrectcredentials');
        exit;
    } else if ($hash == 0) {
        header('Location: userlogin.php?error=incorrectpassword');
        exit;
    } else {
        $result = $db->query("SELECT * FROM user WHERE username = '$username' AND password= '$hash_pwd'");

        if (!$row = $result->fetchArray()) {
            ;

        } else {
            if (strpos($url, '=confirm') !== false) {


                $email       = $db->escapeString($_POST['email']);
                $first       = $db->escapeString($_POST['first']);
                $last        = $db->escapeString($_POST['last']);
                $password    = $db->escapeString($_POST['confirmpassword']);
                $newpassword = $db->escapeString($_POST['newpassword']);
                // update user information
                if (!empty($_POST['newpassword']) && $password == $newpassword && isset($_POST['confirm'])) {
                    $encrypted_password = password_hash($newpassword, PASSWORD_DEFAULT);
                    $result             = $db->exec("UPDATE user SET password= '$encrypted_password' WHERE username= '$username'");
                }
                if (!empty($_POST['first']) && isset($_POST['confirm'])) {
                    $results = $db->exec("UPDATE user SET first= '$first' WHERE username = '$username'");

                }
                if (!empty($_POST['last']) && isset($_POST['confirm'])) {
                    $results = $db->exec("UPDATE user SET last= '$last' WHERE username = '$username'");
                }
                if (!empty($_POST['email']) && isset($_POST['confirm'])) {
                    $results = $db->exec("UPDATE user SET email= '$email' WHERE username = '$username'");
                }



            }
            //login was successful. Store relevant information.
            $results = $db->query("SELECT * FROM user WHERE username = '$username'");
            $row     = $results->fetchArray();

            $_SESSION['id']       = $row['id'];
            $_SESSION['username'] = $row['username'];
            $_SESSION['password'] = $row['password'];
            $_SESSION['email']    = $row['email'];
            $_SESSION['usertype'] = $row['usertype'];
            $_SESSION['first']    = $row['first'];
            $_SESSION['last']     = $row['last'];
            session_regenerate_id();

        }
        header('Location: index.php');
    }
}

else {
    header('Location: userlogin.php ');
}



?>
