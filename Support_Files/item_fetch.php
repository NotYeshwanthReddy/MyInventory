<?php
require "init.php";

if(mysqli_connect_errno()){
	die('Unable to connect to database'. mysqli_connect_error());
}
$statement = $con->prepare("SELECT productID, name, vendor, price, stock FROM product;");
$statement->execute();
$statement->bind_result($item_id, $name, $vendor, $price, $stock);

$product = array();
while($statement->fetch()){
	$temp = array();
	$temp['productID'] = $item_id;
	$temp['name'] = $name;
	$temp['vendor'] = $vendor;
	$temp['price'] = $price;
	$temp['stock'] = $stock;
	array_push($product, $temp);
}
echo json_encode($product);
