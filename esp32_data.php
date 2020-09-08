<?php
require "esp_connect.php";
$api_key_value = "tPmAT5Ab3j7F9";
$api_key = $Name = $Water_level =  $Temp  = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $api_key = test_input($_POST["api_key"]);
    if($api_key == $api_key_value) {
        $Name = test_input($_POST["Name"]);
        $Temp  = test_input($_POST["Temp"]);
        $Water_level = test_input($_POST["Water_level"]);
        // Create connection
        $result = mysqli_query($conn, $mysql_qry);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        } 
        $sql = "INSERT INTO sensordata (Name ,Temp , Water_level)
        VALUES ('" . $Name . "','" . $Temp . "','" . $Water_level . "')";
        
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