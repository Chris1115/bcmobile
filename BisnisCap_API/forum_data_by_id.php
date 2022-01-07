<?php
require_once('helper.php');

$id = $_POST['id'];

$query = "SELECT * FROM forums WHERE id=$id";

$sql = mysqli_query($db_connect, $query);

if($sql)
{
    $result = mysqli_fetch_assoc($sql);
    echo json_encode(array('forum_data' => $result));
}



?>