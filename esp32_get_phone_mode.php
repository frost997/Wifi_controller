<?php
$servername = "localhost:3306";
$dbname = "vidituvi_addon";
$username = "vidituvi_addon";
$password = "QR*bV%szw~[d";
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = $conn->prepare("SELECT id, Mode, reading_time FROM mode_data ORDER BY id DESC LIMIT 1");

$sql->execute();

$sql->bind_result($id,$Mode,$reading_time);

$sensordata = array();
while($sql->fetch()){
		$temp = array();
		$temp['id'] = $id;
		$temp['Mode'] = $Mode; 
		$temp['reading_time'] = $reading_time; 
		array_push($sensordata, $temp);
	}
	echo json_encode($sensordata);
    
