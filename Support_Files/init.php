<?php

$server_name = "localhost";
$mysql_user = "root";
$mysql_pass = "";
$db_name = "my_inventory";

$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

if(!$con)
{
	// echo "Connection failed".mysqli_connect_error();
}
else
{
	// echo "<h3>Connection success";
}