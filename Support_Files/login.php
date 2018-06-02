<?php
require "init.php";
$pass = $_POST["Password"];
$email = $_POST["Email_ID"];

// $pass = "1234567890";
// $email = "yashwanth.peddamallu@st.niituniversity.in";

$sql_query = "SELECT Name FROM customers WHERE mail like '$email' AND Password like '$pass';";

$result = mysqli_query($con, $sql_query);

if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["Name"];
	echo "$name";
}
else{
	echo "Login Failed...";
}
?>
