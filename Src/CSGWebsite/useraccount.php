<?php
session_start();
include 'timeout.php';
if(!isset($_SESSION['id']))
{
    header('Location: userlogin.php?=pleaselogin');
}
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>My Account</title>

    <!-- Bootstrap -->
    <link  href="bootstrap/css/bootstrap.css"  rel="stylesheet">
    <!-- include main css file -->
    <link href="mainstyle.css" rel = "stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body id="accountbg">
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
             <img id = "brand-image" src = "Resources/Risk_Temp_Logo.png"  />
       </a><!-- Change "Logo" to a real image. Eventually.-->
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
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<image src="Resources/playerRAN.png" id="RANcode"></image>
      <hr>
      <label class="accountLabel">Name</label>
      <label class="equalslabel">=</label>
      <label class ="actualValues"><?php echo $_SESSION['first']; echo " "; echo $_SESSION['last'];?></label><span class = "semiColon">;</span>
      <hr>
      <label class="accountLabel">Email</label>
      <label class="equalslabel">=</label>
      <label class ="actualValues"><?php echo $_SESSION['email']; ?></label><span class = "semiColon">;</span>
      <hr>
      <label class="accountLabel">Active Risk AI</label>
      <label class="equalslabel">=</label>
      <label class ="actualValues">playerRAN.java</label><span class = "semiColon">;</span>
      <hr>
      <form method="post" action="accountsettings.php" role="form" >
      <button class="btn btn-primary" id="accountbutton" name="manageaccount">Change Account Settings</button>
     </form>
<p></p>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
