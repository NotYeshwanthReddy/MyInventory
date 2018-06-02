<?php
require "init.php";
$user_name = $_POST["User_Name"];
$pass = $_POST["Password"];
$email = $_POST["Email_ID"];
$enroll_no = $_POST["Enrollment_No"];
$batch = $_POST["Batch"];
$section = $_POST["Section"];
$ph_no = $_POST["Phone"];


$sql_query = "INSERT INTO `customers`(`Name`, `Enrollno`, `mail`, `Phno`, `batch`, `Section`, `Password`) VALUES 
('$user_name', '$enroll_no', '$email', '$ph_no', '$batch', '$section', '$pass');";

if(mysqli_query($con,$sql_query))
{
	// echo "<h3> Data insertion success...</h3>";
}
else
{
	// echo "Data insertion error ..".mysqli_error($con);
}
?>
