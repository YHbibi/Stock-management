<?php

$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");


if (!isset($_POST["nomP"], $_POST["couleurP"], $_POST["categorieP"], $_POST["fournisseurP"], $_POST["prixUP"], $_POST["qteP"])) {

    echo "Remplir tous les champs ! ";
    exit;
}

$nomP = $_POST["nomP"];
$couleurP = $_POST["couleurP"];
$categorieP = $_POST["categorieP"];
$fournisseurP = $_POST["fournisseurP"];
$prixUP = $_POST["prixUP"];
$qteP = $_POST["qteP"];

$rq = " INSERT INTO produit(nomP,couleurP,categorieP,fournisseurP,prixUP,qteP) 
        VALUES('$nomP','$couleurP','$categorieP','$fournisseurP','$prixUP','$qteP')
    ";

$res = $connextion->query($rq);


// en cas de nv produit
$qteE = $qteP;
$rqE = " INSERT INTO bonEntree(nomP,qteE) VALUES('$nomP','$qteE') ";
$resE = $connextion->query($rqE);

if ($res) {
    echo "ajout avec succes";
} else {
    echo "failed";
}
