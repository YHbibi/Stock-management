<?php

$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");


$res = array();

// historiques des entrees
$res['dataE'] = array();


$rq = " SELECT * FROM bonEntree GROUP BY(id) ORDER BY nomP,dateE ";
$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

/*
echo '<pre>';
print_r($tab);
echo '</pre>';
*/

foreach ($tab as $t) {

    $index["id"] = $t["id"];
    $index["nomP"] = $t["nomP"];
    $index["qteE"] = $t["qteE"];
    $index["dateE"] = $t["dateE"];
    array_push($res['dataE'], $index);
}


// historiques des sorties

$res['dataS'] = array();

$rq = " SELECT * FROM bonSortie GROUP BY(id) ORDER BY nomP,dateS ";
$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

foreach ($tab as $t) {

    $index["id"] = $t["id"];
    $index["nomP"] = $t["nomP"];
    $index["qteS"] = $t["qteS"];
    $index["dateS"] = $t["dateS"];
    array_push($res['dataS'], $index);
}

/*
echo '<pre>';
print_r($tab);
echo '</pre>';


echo '<pre>';
print_r($res);
echo '</pre>';

exit;
*/

if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
