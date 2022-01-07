<?php
require_once('helper.php');

$query = "SELECT * FROM news ORDER BY created_at DESC";

$sql = mysqli_query($db_connect, $query);

if($sql)
{
    $result = array();
    while ($row = mysqli_fetch_array($sql))
    {
        array_push( $result, array(
            'id' => $row['id'],
            'headline' => $row['headline'],
            'content' => $row['content']
        ));
    }

    echo json_encode(array('news_data' => $result));
}



?>