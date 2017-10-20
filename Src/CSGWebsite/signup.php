<?php
session_start();
include 'includes/connect.php';
//include 'timeout.php';
/*
  $url = 'https://www.google.com/recaptcha/api/siteverify';
  $key = "6LdViRQUAAAAANw54l0O2MqheFlibXQaz_32otxh";
  $response = file_get_contents($url."?secret=".$key."&response=".$POST['g-recaptcha-response']."&remoteip=".$_SERVER['REMOTE-ADDR']);
  $data = json.decode($response);

  if(isset($data->success) AND $data->succes==true)
 {
 */
/* Signup form validation - make sure they're not blank*/


function SqliteNumRows($query){
       $numRows = 0;
       while($rows = $query->fetchArray()){
           ++$numRows;
       }
       return $numRows;
   }

//makes sure you fill out the form
if(isset($_POST['first']) && !empty($_POST['first']) AND isset($_POST['last']) && !empty($_POST['last']) AND  isset($_POST['username']) && !empty($_POST['username'])  AND isset($_POST['password']) && !empty($_POST['password'])
AND  isset($_POST['email']) && !empty($_POST['email']))  {
        // Form Submited

        $email= $db->escapeString($_POST['email']);
        $first =$db->escapeString($_POST['first']);
        $last = $db->escapeString($_POST['last']);
        $confirmpassword = $db->escapeString($_POST['confirmpassword']);
        $password = $db->escapeString($_POST['password']);
        $username = $db->escapeString($_POST['username']);

                //basic email validation
        if(!filter_var($email, FILTER_VALIDATE_EMAIL))
        {
           header('Location: accountcreation.php?error=invalidemail');
           exit;
         }
         $hash = md5( rand(0,1000) ); // Generate random 32 character hash and assign it to a local variable.
                                      // Example output: f4552671f8909587cf485ea990207f3b
    if ( !preg_match('/^[A-Za-z][A-Za-z0-9]{5,31}$/', $username) ) {
                    // for english chars + numbers only
                      // valid username, alphanumeric & longer than or equals 5 chars
                      header('Location: accountcreation.php?error=formatusername');
                      exit;
  }
     $result = $db->query("SELECT username FROM user WHERE username = '$username'");
     $row = $result->fetchArray();
     $usernamecheck = SqliteNumRows($result);









         if($row){
            header('Location: accountcreation.php?error=username');
            exit;
          }


          $result = $db->query("SELECT email FROM user WHERE email = '$email'");
          $row = $result->fetchArray();
          $emailcheck = SqliteNumRows($result);




          if($row){
             header('Location: accountcreation.php?error=duplicateemail');
             exit;
           }
           if($password!=$confirmpassword)
           {
             header('Location: accountcreation.php?error=password');
             exit;
           }


           $encrpyted_password = password_hash($password, PASSWORD_DEFAULT);

        $result = $db->exec("INSERT INTO user (first,last,username,password,email,usertype) VALUES ('$first','$last','$username','$encrpyted_password','$email',0)");

         header('Location: index.php');
          exit;
      }
      else {
        header('Location: accountcreation.php?error=empty');
        exit;
      }

      ?>
