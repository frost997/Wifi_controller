<?php
require "esp_connect2.php";
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = $conn->prepare("SELECT Name, reading_time FROM sensordata WHERE Water_level = 1 AND Name = '$Name' ORDER BY MAX(reading_time) LIMIT 2");
$sql->execute();

$sql->bind_result($Name,$reading_time);

$sensordata = array();
while($sql->fetch()){
		$temp = array();
		$temp['Name'] = $Name; 
		$temp['reading_time'] = $reading_time; 
		array_push($sensordata, $temp);
	}
	echo json_encode($sensordata);