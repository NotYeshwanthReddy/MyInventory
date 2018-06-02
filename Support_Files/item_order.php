<?php
require "init.php";
$enroll_no = $_POST["Enrollment_No"];
$item_ID = $_POST["Item_ID"];
$item_quantity = $_POST["Item_Quantity"];

// $enroll_no = "U101116fcs155";
// $item_ID = "NES001";
// $item_quantity = "1";

$sql_query = "INSERT INTO `orders` (`Enrollno`, `productID`, `Quantity`) VALUES ( '$enroll_no', '$item_ID', '$item_quantity');";

if(mysqli_query($con,$sql_query))
{
	echo "<h3> Data insertion success...</h3>";
}
else
{
	echo "Data insertion error ..".mysqli_error($con);
}
?>
