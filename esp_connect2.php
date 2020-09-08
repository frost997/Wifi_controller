<?php

$servername = "localhost";
// REPLACE with your Database name
$dbname = "my_database";
// REPLACE with Database user
$username = "root";
// REPLACE with Database user password
$password = "";
$conn = mysqli_connect($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
?>