<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");

if (!isset($_POST["categorieP"])) {

    echo "choisir une categorie ! ";
    exit;
}

$categorieP = $_POST["categorieP"];

$res = array();
$res['data'] = array();


$rq = " SELECT SUM(qteP) AS stockParCat, `categorieP`  FROM produit WHERE  categorieP = '$categorieP' ";

$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

/*
echo '<pre>';
print_r($tab);
echo '</pre>';
*/



foreach ($tab as $t) {

    $index["categorieP"] = $t["categorieP"];
    $index["stockParCat"] = $t["stockParCat"];

    array_push($res['data'], $index);
}
if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
