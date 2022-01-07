<?php
session_start();
if(session_status() > 0)
{
    session_unset();
    session_destroy();
    echo json_encode(array('message'=>'Logout Success'));
}
else    
{
    echo json_encode(array('message'=>'Logout Failed'));
}
?>