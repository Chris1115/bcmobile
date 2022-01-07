<?php
require_once('helper.php');

$username = $_POST['username'];

$query = "SELECT * FROM users WHERE username='$username'";

$sql = mysqli_query($db_connect, $query);

if($sql)
{
    $result = array();
    while($row = mysqli_fetch_row($sql)){
        array_push( $result, array(
            'id' => $row['id'],
            'name' => $row['name'],
            'username' => $row['username'],
            'password' => $row['password']
        ));
    }

    echo json_encode(array('users_data' => $result));
}



?>