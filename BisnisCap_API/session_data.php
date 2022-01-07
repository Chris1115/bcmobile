<?php
session_start();
echo json_encode(array('session_data'=>$_SESSION))
?>