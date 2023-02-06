<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");


if (!isset($_POST["id"])) {

    echo "choisi un produit ! ";
    exit;
}

$id = $_POST["id"];
$nomP = $_POST["nomP"];
$qteS = $_POST["qteS"];
//$id = 1;

$rq = " DELETE FROM produit WHERE id = '$id' ";

$res = $connextion->query($rq);


// en cas de suppression du produit
$rqS = " INSERT INTO bonSortie(nomP,qteS) VALUES('$nomP','$qteS') ";
$resS = $connextion->query($rqS);



if ($res) {
    echo "suppression avec succes";
} else {
    echo "failed";
}
