<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");


$res = array();
$res['data'] = array();


$rq = " SELECT * FROM produit ";
$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

//var_dump($tab);

foreach ($tab as $t) {

    $index["id"] = $t["id"];
    $index["nomP"] = $t["nomP"];
    $index["couleurP"] = $t["couleurP"];
    $index["categorieP"] = $t["categorieP"];
    $index["fournisseurP"] = $t["fournisseurP"];
    $index["qteP"] = $t["qteP"];
    $index["prixUP"] = $t["prixUP"];

    array_push($res['data'], $index);
}
if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
