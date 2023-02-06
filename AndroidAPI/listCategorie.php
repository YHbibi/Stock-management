<?php
$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");

$res = array();
$res['data'] = array();

$rq = " SELECT distinct`categorieP` FROM produit ";
$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

foreach ($tab as $t) {

    $index["categorieP"] = $t["categorieP"];

    array_push($res['data'], $index);
}
if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
