<?php
require_once('helper.php');

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM users WHERE username='$username'";

$sql = mysqli_query($db_connect, $query);

if($sql)
{
    $result = mysqli_fetch_assoc($sql);
}
else
{
    echo 'sql error';
}

if(password_verify($password, $result['password']))
{
    session_start();
    $_SESSION['id'] = $result['id'];
    $_SESSION['name'] = $result['name'];
    $_SESSION['username'] = $result['username'];
    $_SESSION['password'] = $result['password'];
    echo json_encode(array('message' => 'Login Success'));
}
else
{
    echo json_encode( array('message' => 'Login Failed'));
}

?>