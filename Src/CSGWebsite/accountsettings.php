<?php
session_start();
include 'timeout.php';
$url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
if(!isset($_SESSION['id']))
{
    header('Location: userlogin.php?=pleaselogin');
}
else if(!isset($_POST['manageaccount']) && strpos($url,'error=incorrectcredentials') == false)
{
  header('Location: index.php');
}
?>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Manage Account</title>

        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <!-- include main css file -->
        <link href="mainstyle.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>

    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
                    <a class="navbar-brand" href="index.php">
                        <img id="brand-image" src="Resources/Risk_Temp_Logo.png" />
                    </a>
                    <!-- Change "Logo" to a real image. Eventually.-->
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="aboutus.php">About <span class="sr-only">(current)</span></a></li>
                        <li><a href="tutorials.php">Tutorials</a></li>
                        <li><a href="useraccount.php">My Account</a></li>
                        <li><a href="mygames.php">My Games</a></li>

                        <?php
                    if(isset($_SESSION['id'])) {
                      echo "<li><a href='logout.php'>LOG OUT</a></li>";
                    }
                    else {
                      echo "<li class='signup'><a href='accountcreation.php'>Sign Up</a></li>
                            <li class='login'><a href='userlogin.php'>Log In</a></li>";
                    }
                  ?>

                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>

        <div id="form-background">
            <form name="form_login" method="post" action="login.php?=confirm" role="form" id="signupform" data-toggle="validator">
                <div class="form-group">
                    <?php
          if(strpos($url,'error=incorrectcredentials') !== false) {
            echo "<label  class = 'serversideError'>ERROR: Username and Password combination incorrect</label>";
            }
      ?>
                        <label for="example-text-input">Change First Name</label>
                        <input class="form-control" class="ib" type="text" name="first" value="" class="" id="firstname" autofocus>
                </div>
                <div class="form-group">
                    <label for="example-text-input">Change Last Name</label>
                    <input class="form-control" class="ib" type="text" name="last" value="" class="" id="lastname">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail1" class="inputboxes">Change Email address</label>
                    <input type="email" class="form-control" class="ib" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" placeholder="Enter email" id="email" data-error="Invalid email address">
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Change Password</label>
                    <input type="password" name="newpassword" class="form-control" class="ib" placeholder="Password" id="password1" data-minlength="6" data-error="Passwords must be at least 6 characters">
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Confirm New Password</label>
                    <input type="password" name="confirmpassword" class="form-control" class="ib" id="exampleInputPassword1" data-match="#password1" placeholder="Password" id="password2" data-error="Passwords don't match">
                    <div class="help-block with-errors"></div>
                </div>

                <div class="form-group">
                    <label for="exampleInputPassword1">Current Username</label>
                    <input type="text" name="username" class="form-control" class="ib" id="exampleInputPassword1" id="password2" data-error="You must input Username" required>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Current Password(Before any changes)</label>
                    <input type="password" name="password" class="form-control" class="ib" id="exampleInputPassword1" placeholder="Password" id="password2" data-error="You must input Password" required>
                    <div class="help-block with-errors"></div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary" id="submit" name="confirm">CONFIRM</button>
                </div>
            </form>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>
    </body>

    </html>
