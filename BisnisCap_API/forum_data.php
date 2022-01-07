<?php
require_once('helper.php');

$query = "SELECT * FROM forums ORDER BY created_at DESC";

$sql = mysqli_query($db_connect, $query);

if($sql)
{
    $result = array();
    while ($row = mysqli_fetch_array($sql))
    {
        array_push( $result, array(
            'id' => $row['id'],
            'title' => $row['title'],
            'question' => $row['question']
        ));
    }

    echo json_encode(array('forums_data' => $result));
}



?>