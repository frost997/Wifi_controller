<!DOCTYPE html>
<html><body>
<?php
require "esp_connect.php";
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT id, Speed, L_speed, R_speed, Water_level, Battery, Distance, reading_time FROM sensordata ORDER BY id DESC";

echo '<table cellspacing="5" cellpadding="5">
      <tr> 
        <td>ID</td> 
        <td>Speed</td>  
        <td>L_speed</td> 
        <td>R_speed</td>
        <td>Water_level</td>
        <td>Battery</td>
        <td>Distance</td> 
        <td>Timestamp</td> 
      </tr>';
 
if ($result = $conn->query($sql)) {
    while ($row = $result->fetch_assoc()) {
        $row_id = $row["id"];
        $row_Speed = $row["Speed"];
        $row_L_speed = $row["L_speed"];

        $row_Water_level = $row["Water_level"];  
        $row_Battery = $row["Battery"]; 
        $row_Distance = $row["Distance"]; 
        $row_reading_time = $row["reading_time"];
        // Uncomment to set timezone to - 1 hour (you can change 1 to any number)
        //$row_reading_time = date("Y-m-d H:i:s", strtotime("$row_reading_time - 1 hours"));
      
        // Uncomment to set timezone to + 4 hours (you can change 4 to any number)
        //$row_reading_time = date("Y-m-d H:i:s", strtotime("$row_reading_time + 4 hours"));
      
        echo '<tr> 
                <td>' . $row_id . '</td> 
                <td>' . $row_Speed . '</td> 
                <td>' . $row_L_speed . '</td> 
                <td>' . $row_R_speed . '</td>
                <td>' . $row_Water_level . '</td>
                <td>' . $row_Battery  . '</td>
                <td>' . $row_Distance . '</td> 
                <td>' . $row_reading_time . '</td> 
              </tr>';
    }
    $result->free();
}

$conn->close();
?> 
</table>
</body>
</html>