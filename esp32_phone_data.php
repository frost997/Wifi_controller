<?php
require "esp_connect.php";
$api_key_value = "tPmAT5Ab3j7F9";

$api_key = $id =  $Set_speed = $Set_turn_speed = $Movement = $Mode = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $api_key = test_input($_POST["api_key"]);
    if($api_key == $api_key_value) {
        $id = test_input($_POST["id"]);
        $Set_speed = test_input($_POST["Set_speed"]);
        $Set_turn_speed = test_input($_POST["Set_turn_speed"]);
        $Movement = test_input($_POST["Movement"]);
        $Mode = test_input($_POST["Mode"]);
        // Create connection
        $result = mysqli_query($conn, $mysql_qry);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        } 
        $sql = "INSERT INTO phone_data (id, Set_speed, Set_turn_speed, Movement, Mode)
        VALUES ('" . $id . "','" . $Set_speed . "','" . $Set_turn_speed . "', '" . $Movement . "', '" . $Mode . "')";
        
        if ($conn->query($sql) === TRUE) {
            echo "New record created successfully";
        } 
        else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
        $conn->close();
    }
    else {
        echo "Wrong API Key provided.";
    }

}
else {
    echo "No data posted with HTTP POST.";
}

function test_input($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}