<?php
require "esp_connect.php";

$conn = new mysqli($servername, $username, $password, $dbname);
$Name = $_GET['Name'];
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = $conn->prepare("SELECT Name,Temp, Water_level, reading_time FROM sensordata WHERE Name = '$Name' ORDER BY reading_time DESC");
$sql->execute();

$sql->bind_result($Name,$Temp,$Water_level,$reading_time);

$sensordata = array();
while($sql->fetch()){
		$temp = array();
		$temp['Name'] = $Name; 
		$temp['Temp'] = $Temp;   
		$temp['Water_level'] = $Water_level; 
		$temp['reading_time'] = $reading_time; 
		array_push($sensordata, $temp);
	}
	echo json_encode($sensordata);