<?php
require "esp_connect.php";
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = $conn->prepare("SELECT id, Set_speed, Set_turn_speed, Movement, Mode, reading_time FROM phone_data WHERE id = 1 ORDER BY id");

$sql->execute();

$sql->bind_result($id,$Set_speed,$Set_turn_speed,$Movement,$Mode,$reading_time);

$phone_data = array();
while($sql->fetch()){
		$temp = array();
		$temp['id'] = $id; 
		$temp['Set_speed'] = $Set_speed;
		$temp['Set_turn_speed'] = $Set_turn_speed;  
		$temp['Movement'] = $Movement; 
		$temp['Mode'] = $Mode;
		$temp['reading_time'] = $reading_time; 
		array_push($phone_data, $temp);
	}
	echo json_encode($phone_data);
    
