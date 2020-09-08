<?php

$servername = "localhost:3306";
$dbname = "vidituvi_addon";
$username = "vidituvi_addon";
$password = "QR*bV%szw~[d";
$conn = mysqli_connect($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
?>