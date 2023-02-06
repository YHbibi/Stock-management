<?php



$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");



if (!isset($_POST["nomP"])) {

    echo "Remplir tous les champs ! ";
    exit;
}


$res = array();

$res['data'] = array();

$nomP = $_POST["nomP"];

$rq = " SELECT * FROM bonSortie WHERE nomP ='$nomP' ";


$response = $connextion->query($rq);

$tab = $response->fetchAll(PDO::FETCH_ASSOC);

foreach ($tab as $t) {

    $index["id"] = $t["id"];
    $index["nomP"] = $t["nomP"];
    $index["qteS"] = $t["qteS"];
    $index["dateS"] = $t["dateS"];
    array_push($res['data'], $index);
}

if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
