<?php
session_start();
include 'includes/connect.php';


//include 'timeout.php';
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
    <title>My Games</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="mainstyle.css" rel = "stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
    //initiliaze circle colors
          var counter = 0;
          var ALAcircleColor= "black";
          var NWTcircleColor = "black";
          var ALBcircleColor = "black";
          var WUScircleColor ="black";
          var CAMcircleColor = "black";
          var ONTcircleColor = "black";
          var QUEcircleColor = "black";
          var EUScircleColor = "black";
          var GREcircleColor ="black";
          var ICEcircleColor = "black";
          var SCAcircleColor ="black";
          var GBRcircleColor = "black";
          var WEUcircleColor = "black";
          var NEUcircleColor ="black";
          var SEUcircleColor = "black";
          var UKRcircleColor = "black";
          var MEAcircleColor = "black";
          var URAcircleColor = "black";
          var SIBcircleColor = "black";
          var IRKcircleColor = "black";
          var AFGcircleColor = "black";
          var YAKcircleColor = "black";
          var KAMcircleColor = "black";
          var MONcircleColor = "black";
          var CHIcircleColor = "black";
          var SIAcircleColor = "black";
          var JAPcircleColor = "black";
          var INDcircleColor = "black";
          var VENcircleColor = "black";
          var PERcircleColor = "black";
          var ARGcircleColor = "black";
          var BRAcircleColor= "black";
          var NAFcircleColor = "black";
          var EGYcircleColor = "black";
          var CAFcircleColor = "black";
          var EAFcircleColor = "black";
          var SAFcircleColor = "black";
          var MADcircleColor = "black";
          var INEcircleColor = "black";
          var NGUcircleColor = "black";
          var WAUcircleColor = "black";
          var EAUcircleColor = "black";
          var NZEcircleColor = "black";
          //initiliaze army scores
          var army = ["0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0" ];





 // function for rendering the canvas
    function initcanvas()
    {


    var c = document.getElementById('replay');
    var ctx = c.getContext('2d');
    var cw = ctx.canvas.width;
    var ch = ctx.canvas.height;
//creates circle object that will represent each country
    function circleObj()
    {
        this.render = function (ctx,centerX,centerY,circleColor,countryN,army) {
        ctx.beginPath();
        ctx.arc(centerX, centerY, 20, 0, 2 * Math.PI, false);
        ctx.fillStyle = circleColor;
        ctx.fill();
        ctx.stroke();
        ctx.fillStyle = "white";
        ctx.fillText(countryN,centerX-10,centerY+0);
        ctx.fillText(army,centerX-3,centerY+10);
      }
    }



      //init all countries
      var wasteInterval;
      var ALA= new circleObj();
      var NWT = new circleObj();
      var ALB = new circleObj();
      var WUS = new circleObj();
      var CAM = new circleObj();
      var ONT = new circleObj();
      var QUE = new circleObj();
      var EUS = new circleObj();
      var GRE = new circleObj();
      var ICE = new circleObj();
      var SCA = new circleObj();
      var GBR = new circleObj();
      var WEU = new circleObj();
      var NEU = new circleObj();
      var SEU = new circleObj();
      var UKR = new circleObj();
      var MEA = new circleObj();
      var URA = new circleObj();
      var SIB = new circleObj();
      var IRK = new circleObj();
      var AFG = new circleObj();
      var YAK = new circleObj();
      var KAM = new circleObj();
      var MON = new circleObj();
      var CHI = new circleObj();
      var SIA = new circleObj();
      var JAP = new circleObj();
      var IND = new circleObj();
      var VEN = new circleObj();
      var PER = new circleObj();
      var ARG = new circleObj();
      var BRA= new circleObj();
      var NAF = new circleObj();
      var EGY = new circleObj();
      var CAF = new circleObj();
      var EAF = new circleObj();
      var SAF = new circleObj();
      var MAD = new circleObj();
      var INE = new circleObj();
      var NGU = new circleObj();
      var WAU = new circleObj();
      var EAU = new circleObj();
      var NZE = new circleObj();
      var arr;
      var i = 0;
      var stop = false;
// function to start a new game. reinit starting values.
           function initGame()
           {
             stop = true;
             counter = 0;
             ALAcircleColor= "black";
             NWTcircleColor = "black";
             ALBcircleColor = "black";
             WUScircleColor ="black";
             CAMcircleColor = "black";
             ONTcircleColor = "black";
             QUEcircleColor = "black";
             EUScircleColor = "black";
             GREcircleColor ="black";
             ICEcircleColor = "black";
             SCAcircleColor ="black";
             GBRcircleColor = "black";
             WEUcircleColor = "black";
             NEUcircleColor ="black";
             SEUcircleColor = "black";
             UKRcircleColor = "black";
             MEAcircleColor = "black";
             URAcircleColor = "black";
             SIBcircleColor = "black";
             IRKcircleColor = "black";
             AFGcircleColor = "black";
             YAKcircleColor = "black";
             KAMcircleColor = "black";
             MONcircleColor = "black";
             CHIcircleColor = "black";
             SIAcircleColor = "black";
             JAPcircleColor = "black";
             INDcircleColor = "black";
             VENcircleColor = "black";
             PERcircleColor = "black";
             ARGcircleColor = "black";
             BRAcircleColor= "black";
             NAFcircleColor = "black";
             EGYcircleColor = "black";
             CAFcircleColor = "black";
             EAFcircleColor = "black";
             SAFcircleColor = "black";
             MADcircleColor = "black";
             INEcircleColor = "black";
             NGUcircleColor = "black";
             WAUcircleColor = "black";
             EAUcircleColor = "black";
             NZEcircleColor = "black";
             army = ["0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0" ];

            <?php
                   //checks to see if user decided to click one of their games.
           $url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
        if(strpos($url,'game=') !== false) {
           $tempgameNumb=10;
           $tempgameNumb = strrev($url);
           $looping = true;
           $gameNumb = "";

           $index = 2;
           $container;
           while($looping)
           {
             $container = substr($tempgameNumb,$index-2,1);


             if($container=="0" || $container =="1" || $container=="2" || $container=="3" || $container =="4" || $container =="5" || $container =="6" || $container =="7" || $container =="8" || $container =="9")
             {
               $gameNumb = (string)$gameNumb . (string)$container;
               $index++;
              }
             else
               {
               $looping = false;
                }

             }
           $gameNumb = strrev($gameNumb);
           if($gameNumb!="")
           {

                   //makes sure the game number is legitimate
           $testResults = $db->query("SELECT update_contents FROM games_history WHERE game_number=$gameNumb");
           if($testResults->fetchArray(SQLITE3_ASSOC))
           {
           $results = $db->query("SELECT update_contents FROM games_history WHERE game_number=$gameNumb");
           $row = $results->fetchArray();
           $update = array();
           $index = 0;
           $row = true;
           while($row!=false)
            {
            $row = $results->fetchArray();
            $update[$index] = $row['update_contents'];
            $index++;
          }
//updates storage array with moves from database

          echo "arr =" . json_encode($update) . ";";
          echo "i=0;";
          echo "startGame();";
        }
   }
      }
             ?>


}

//officially begins the game
function startGame()
{
  clearInterval(animateInterval);
  clearInterval(wasteInterval);
  stop = false;
  wasteInterval = setInterval(updateCanvas,250);
}
//updates things on a turn by turn basis
function updateCanvas()
{

  if(stop==false)
  {
  var qCountry = getCountry(arr[i]);
  var qColor = getColor(arr[i]);
  var qArmy = getArmyValue(arr[i]);
  updateColor(qCountry,qColor,qArmy);
  i++;
  animate();
  if (counter < 150)
  {
  counter ++;
   }
  if(counter==150)
  {
    clearInterval(wasteInterval);
      wasteInterval = setInterval(updateCanvas,75);
      counter++;
  }
  if(arr[i]==null)
  {
    clearInterval(wasteInterval);
    counter = 0;
  }
}
  }
             //stores relevant information for animate to make use of when updating the map
      function updateColor(country1,color1,armyValue1)
      {

        if(country1 == "ALA")
        {
          ALAcircleColor = color1;
          army[0] = armyValue1;
        }
        else if(country1 == "NWT")
        {
           NWTcircleColor = color1;
           army[1] = armyValue1;
        }
        else if(country1== "ALB")
        {
          ALBcircleColor =color1;
          army[2] = armyValue1;
        }
        else if(country1 == "WUS")
        {
           WUScircleColor = color1;
           army[3] = armyValue1;
        }
        else if(country1 == "CAM")
        {
           CAMcircleColor = color1;
           army[4] = armyValue1;
        }
        else if(country1 == "ONT")
        {
           ONTcircleColor = color1;
           army[5] = armyValue1;
        }
        else if(country1=="QUE")
        {
          QUEcircleColor = color1;
          army[6] = armyValue1;
        }
        else if(country1 == "EUS")
        {
           EUScircleColor = color1;
           army[7] = armyValue1;
        }
        else if(country1 == "GRE")
        {
           GREcircleColor = color1;
           army[8] = armyValue1;
        }
        else if(country1 == "ICE")
        {
           ICEcircleColor = color1;
           army[9] = armyValue1;
        }
        else if(country1 == "SCA")
        {
           SCAcircleColor = color1;
           army[10] = armyValue1;
        }
        else if(country1 == "GBR")
        {
           GBRcircleColor = color1;
           army[11] = armyValue1;
        }
        else if(country1 == "WEU")
        {
           WEUcircleColor = color1;
           army[12] = armyValue1;
        }
        else if(country1 == "NEU")
        {
           NEUcircleColor = color1;
           army[13] = armyValue1;
        }
        else if(country1 == "SEU")
        {
           SEUcircleColor = color1;
           army[14] = armyValue1;
        }
        else if(country1 == "UKR")
        {
           UKRcircleColor = color1;
           army[15] = armyValue1;
        }
        else if(country1 == "MEA")
        {
           MEAcircleColor = color1;
           army[16] = armyValue1;
        }
        else if(country1 == "URA")
        {
          URAcircleColor = color1;
          army[17] = armyValue1;
        }
        else if(country1 == "SIB")
        {
          SIBcircleColor = color1;
          army[18] = armyValue1;
        }
        else if(country1 == "IRK")
        {
           IRKcircleColor = color1;
           army[19] = armyValue1;
        }

        else if(country1 == "AFG")
        {
           AFGcircleColor = color1;
           army[20] = armyValue1;
        }
        else if(country1 == "YAK")
        {
           YAKcircleColor = color1;
           army[21] = armyValue1;
        }
        else if(country1 == "KAM")
        {
           KAMcircleColor = color1;
           army[22] = armyValue1;
        }
        else if(country1 == "MON")
        {
           MONcircleColor = color1;
           army[23] = armyValue1;
        }
        else if(country1 == "CHI")
        {
           CHIcircleColor = color1;
           army[24] = armyValue1;
        }
        else if(country1 == "SIA")
        {
           SIAcircleColor = color1;
           army[25] = armyValue1;
        }
        else if(country1 == "JAP")
        {
           JAPcircleColor = color1;
           army[26] = armyValue1;
        }
        else if(country1 == "IND")
        {
           INDcircleColor = color1;
           army[27] = armyValue1;
        }
        else if(country1 == "VEN")
        {
          VENcircleColor = color1;
          army[28] = armyValue1;
        }
        else if(country1 == "PER")
        {
           PERcircleColor = color1;
           army[29] = armyValue1;
        }
        else if(country1 == "ARG")
        {
           ARGcircleColor = color1;
           army[30] = armyValue1;
        }
        else if(country1 == "BRA")
        {
           BRAcircleColor = color1;
           army[31] = armyValue1;
        }
        else if(country1 == "NAF")
        {
           NAFcircleColor = color1;
           army[32] = armyValue1;
        }
        else if(country1 == "EGY")
        {
           EGYcircleColor = color1;
           army[33] = armyValue1;
        }
        else if(country1 == "CAF")
        {
           CAFcircleColor = color1;
           army[34] = armyValue1;
        }
        else if(country1 == "EAF")
        {
           EAFcircleColor = color1;
           army[35] = armyValue1;
        }
        else if(country1 == "SAF")
        {
           SAFcircleColor = color1;
           army[36] = armyValue1;
        }
        else if(country1 == "MAD")
        {
           MADcircleColor = color1;
           army[37] = armyValue1;
        }
        else if(country1 == "INE")
        {
           INEcircleColor = color1;
           army[38] = armyValue1;
        }
        else if(country1 == "NGU")
        {
           NGUcircleColor = color1;
           army[39] = armyValue1;
        }
        else if(country1 == "WAU")
        {
           WAUcircleColor = color1;
           army[40] = armyValue1;
        }
        else if(country1 == "EAU")
        {
           EAUcircleColor = color1;
           army[41] = armyValue1;
        }
        else if(country1 == "NZE")
        {
         NZEcircleColor = color1;
         army[42] = armyValue1;
        }
              return;
      }

      //Returns the Country Value from string input

          function getCountry(queryInfo)
          {
              var temp = queryInfo;
              var findCountry = temp.substring(16,13);
              return findCountry;
          }

          //Returns the color Value from string input
          function getColor(queryInfo)
          {
            var temp = queryInfo;
            var findColor = temp.substring(21,17);
            return findColor;
          }

                 //Returns the Army Value from string input
         function getArmyValue(queryInfo)
         {
           var temp = queryInfo;
           var bool = false;
           var index = 22;
           var findArmy = "";
           var container;
           while(bool==false || index>temp.length)
           {
             container = temp.substring(index,index-1);
             if(container==0 || container ==1 || container==2 || container==3 || container ==4 || container ==5 || container ==6 || container ==7 || container ==8 || container ==9)
             {
               findArmy = findArmy.toString() + container.toString();
               index++;
             }
             else
             {
               bool = true;
             }
           }
           return findArmy;
         }
// updates the map
      function animate()
      {

        ALA.render(ctx,30,50,ALAcircleColor,"ALA",army[0]);
        NWT.render(ctx,80,50,NWTcircleColor,"NWT",army[1]);
        ALB.render(ctx,80,100,ALBcircleColor,"ALB",army[2]);
        WUS.render(ctx,80,150,WUScircleColor,"WUS",army[3]);
        CAM.render(ctx,80,200,CAMcircleColor,"CAM",army[4]);
        ONT.render(ctx,130,100,ONTcircleColor,"ONT",army[5]);
        QUE.render(ctx,180,100,QUEcircleColor,"QUE",army[6]);
        EUS.render(ctx,130,150,EUScircleColor,"EUS",army[7]);
        GRE.render(ctx,230,50,GREcircleColor,"GRE",army[8]);
        ICE.render(ctx,330,50,ICEcircleColor,"ICE",army[9]);
        SCA.render(ctx,430,50,SCAcircleColor,"SCA",army[10]);
        GBR.render(ctx,380,100,GBRcircleColor,"GBR",army[11]);
        WEU.render(ctx,380,200,WEUcircleColor,"WEU",army[12]);
        NEU.render(ctx,430,150,NEUcircleColor,"NEU",army[13]);
        SEU.render(ctx,430,200,SEUcircleColor,"SEU",army[14]);
        UKR.render(ctx,480,150,UKRcircleColor,"UKR",army[15]);
        MEA.render(ctx,480,250,MEAcircleColor,"MEA",army[16]);
        URA.render(ctx,580,125,URAcircleColor,"URA",army[17]);
        SIB.render(ctx,630,125,SIBcircleColor,"SIB",army[18]);
        IRK.render(ctx,680,125,IRKcircleColor,"IRK",army[19]);
        AFG.render(ctx,580,175,AFGcircleColor,"AFG",army[20]);
        YAK.render(ctx,680,75,YAKcircleColor,"YAK",army[21]);
        KAM.render(ctx,730,75,KAMcircleColor,"KAM",army[22]);
        MON.render(ctx,680,175,MONcircleColor,"MON",army[23]);
        CHI.render(ctx,680,225,CHIcircleColor,"CHI",army[24]);
        SIA.render(ctx,680,275,SIAcircleColor,"SIA",army[25]);
        JAP.render(ctx,730,225,JAPcircleColor,"JAP",army[26]);
        IND.render(ctx,580,275,INDcircleColor,"IND",army[27]);
        VEN.render(ctx,130,275,VENcircleColor,"VEN",army[28]);
        PER.render(ctx,130,325,PERcircleColor,"PER",army[29]);
        ARG.render(ctx,130,375,ARGcircleColor,"ARG",army[30]);
        BRA.render(ctx,180,325,BRAcircleColor,"BRA",army[31]);
        NAF.render(ctx,355,350,NAFcircleColor,"NAF",army[32]);
        EGY.render(ctx,405,350,EGYcircleColor,"EGY",army[33]);
        CAF.render(ctx,355,400,CAFcircleColor,"CAF",army[34]);
        EAF.render(ctx,405,400,EAFcircleColor,"EAF",army[35]);
        SAF.render(ctx,405,450,SAFcircleColor,"SAF",army[36]);
        MAD.render(ctx,455,450,MADcircleColor,"MAD",army[37]);
        INE.render(ctx,755,325,INEcircleColor,"INE",army[38]);
        NGU.render(ctx,805,325,NGUcircleColor,"NGU",army[39]);
        WAU.render(ctx,755,375,WAUcircleColor,"WAU",army[40]);
        EAU.render(ctx,805,375,EAUcircleColor,"EAU",army[41]);
        NZE.render(ctx,855,425,NZEcircleColor,"NZE",army[42]);


      }
  var animateInterval = setInterval(animate,300);

//checks to see if user selected to replay a specific game. if so reinit things
  <?php
  $url = "http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
  if(strpos($url,'game=') !== false) {
  echo 'initGame();';
    }
?>
    }
window.addEventListener('load', function(event){
  initcanvas();

});



    </script>
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


<canvas id="replay" width = '900' height = '500'></canvas>

    <script type="text/javascript">
    var userGameNumber;
    var userGameslist;

// finds all the users games that they have stored away in the database
    function findUserGames(){

     <?php
    //  if($db->query("SELECT game_number FROM games_tracker WHERE user_id = $_SESSION[id]"))
       //{
      $testResults = $db->query("SELECT game_number FROM games_tracker WHERE user_id = $_SESSION[id]");
      if($testResults->fetchArray(SQLITE3_ASSOC))
      {
        $results = $db->query("SELECT game_number FROM games_tracker WHERE user_id = $_SESSION[id]");

      $userGameList = array();
      $userGameNumber = 0;
      $row = true;
      $row = $results->fetchArray();
      while($row!=false)
        {
          $userGameList[$userGameNumber] = $row['game_number'];
          $userGameNumber++;
          $row = $results->fetchArray();
       }
       echo "userGamesList =" . json_encode($userGameList) . ";";
       echo "userGameNumber=" . $userGameNumber . ";";
       echo "return true;";
    }
    else {
     echo "return false;";
    }
       ?>

    }

      </script>


<form method="GET" action="" type="submit">
<div class="dropdown" id="menu">
  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    Replay Game
    <span class="caret"></span>
  </button>


<script type="text/javascript">

if(findUserGames())
{
  //display games in a drop down with buttons on it. UP to 10.
   document.write('<ul id="dropdown" class="dropdown-menu" aria-labelledby="dropdownMenu1">');
   while(userGameNumber>0) {
    document.write("<li><button type ='submit' class='btn btn-danger gamebutton' name='game' value='"+userGamesList[userGameNumber-1] +"'>Game "+userGamesList[userGameNumber-1] +"</button></li>");
     userGameNumber--;
    }
  }

    </script>
  </ul>

</div>
</form>

<h3 class = "movingleft">You can use your replays here if you have any stored up from the game! Just check the drop-down and select the button of the games you wish to replay. Some games have thousands of turns, so be patient. After 150 turns the pace of the game is doubled! </h3>









    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>
