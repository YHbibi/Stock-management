<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");

if (!isset($_POST["nomP"])) {

    echo "choisir un produit ! ";
    exit;
}


$nomP = 'PC LENOVO';
$res = array();
$res['data'] = array();


$rq = " SELECT `id`, `nomP`, `qteP` FROM produit WHERE  nomP = '$nomP' ";
$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

//var_dump($tab);

foreach ($tab as $t) {

    $index["id"] = $t["id"];
    $index["nomP"] = $t["nomP"];
    $index["qteP"] = $t["qteP"];

    array_push($res['data'], $index);
}
if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
