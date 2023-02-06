<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");

if (!isset($_POST["mail"], $_POST["passwd"])) {

    echo "log in d'abord! ";
    exit;
}

$mail = $_POST["mail"];
$passwd = $_POST["passwd"];


// en cas de suppression du produit
$rq = " SELECT * FROM `admin` WHERE `mail` = '$mail' AND `passwd` = '$passwd'  ";
$res = $connextion->query($rq);
$tab = $res->fetchAll(PDO::FETCH_ASSOC);



if (count($tab)) {
    echo "cnx avec succes";
} else {
    echo "failed";
}
