<?php
session_start();
include 'timeout.php';

?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Tutorial</title>

    <!-- Bootstrap -->
    <link  href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- include main css file -->
    <link href="mainstyle.css" rel = "stylesheet">

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

<div class="container" class=".text-center">
<h1>Getting Started</h1>
</br>
<h3>The first thing you need to do is replicate the four classes here. The file accepts four arguments: Our site's IP address, one of 5 ports (5551-5555),
your username in relation our site, and finally UI mode - which if left blank there will be no UI on your end.
Feel free to experiment with the different parameters.
</h3>
<h5>
<a href="http://www.mediafire.com/file/j6wmgo7grl944cj/playerClient.java">playerClient.java</a>
</br>
<a href="http://www.mediafire.com/file/y1sw8pf381bp9rk/clientMap.java">clientMap.java</a></br>
<a href="http://www.mediafire.com/file/28s5ycbrcjx6tv1/RiskClient.java">RiskClient.java</a></br>
<a href="http://www.mediafire.com/file/edv9mjrrr2jb7m2/transactions.java">transactions.java</a>
</br></br>
</h5>
<h3>When it comes to actually implementing your own artificial intelligence algorithm - you will need to make changes to the playerClient.java file, and take care to see
that the format of the strings it produces does not change, more specifics below.

</br></br>
Inside of the playerClient.java file you will find a run method, and this is part of the playerClient that you will need to edit to produce your Risk AI. Inside of the run
method you will find a switch statement. In the version that you download from our site all transactions are responded to randomly, copy the format that the strings are produced in to your solution and
compile the project and run the RiskClient main with the aforementioned arguments to start a game of risk. Every transaction is logged, so you will be able to see
game play out on the site later on. You can watch a game from the games page.
</h3>
</br></br>
</div>
<div class="container" class=".text-center">
<h1>          TIPS</h1>
<h3>
Here are a few recommendations from the original author of the aritifical intelligence risk game, Dr. Finkbine:
</br></br>
<ul>
  <li> Save all teritorynodes in a data structure for lookup. Could do an arrayList and then convert to an array. Write methods to lookup by shortname or longname, might want to use binary search on an array for speed. Or a hash into the array using a hash function on the three-char code. The game will use shortnames most of the time.
</li></br>
  <li> Save all connectionNodes for your map. Need method to determine if any two territories are connected</li>
</ul>

</br></br>
Furthermore, here are a few resources to help get you started developing your own algorithm.
</br>
</h3>
</div>
<div class="container" class=".text-center">
  <h5>
<a href="http://homeai.info/directory/algorithms/">AI Algorithms</a></br>
<a href="http://cs229.stanford.edu/proj2012/LozanoBratz-ARiskyProposalDesigningARiskGamePlayingAgent.pdf">Designing a Risk Game Playing Agent</a>

</h5>
</div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
