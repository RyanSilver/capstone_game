<?php

//creates a connection to sn sqlite3 database
   class MyDB extends SQLite3
   {
      function __construct()
      {
         $this->open('includes/games.db');
      }
   }
   $db = new MyDB();
   if(!$db){
      echo $db->lastErrorMsg();
   } else {
    ;
}
?>
