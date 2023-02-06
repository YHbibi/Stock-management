<?php

$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");


if (!isset($_POST["id"], $_POST["nomP"], $_POST["couleurP"], $_POST["categorieP"], $_POST["fournisseurP"], $_POST["prixUP"], $_POST["qteP"])) {

    echo "Remplir tous les champs ! ";
    exit;
}

$id = $_POST["id"];
$nomP = $_POST["nomP"];
$couleurP = $_POST["couleurP"];
$categorieP = $_POST["categorieP"];
$fournisseurP = $_POST["fournisseurP"];
$prixUP = $_POST["prixUP"];
$qteP = $_POST["qteP"];

$qteOLD = $_POST["qteOLD"];

$rq = " UPDATE produit SET nomP = '$nomP', couleurP = '$couleurP', categorieP ='$categorieP', fournisseurP = '$fournisseurP' ,prixUP = '$prixUP', qteP = '$qteP'
        WHERE id = '$id'
    ";



$res = $connextion->query($rq);

if ($qteOLD < $qteP) {


    $qteE = $qteP - $qteOLD;

    $rqE = "INSERT INTO bonEntree(nomP,qteE) 
       VALUES('$nomP','$qteE')
    ";

    $resE = $connextion->query($rqE);
}

if ($qteOLD > $qteP) {

    $qteS =  $qteOLD - $qteP;


    $rqS = "INSERT INTO bonSortie(nomP,qteS) 
       VALUES('$nomP','$qteS')
    ";

    $resS = $connextion->query($rqS);
}

if ($res) {
    echo "modification avec succes";
} else {
    echo "echec de modification";
}
