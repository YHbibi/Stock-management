<?php


$connextion = new PDO("mysql:host=localhost:3306;dbname=stock", "root", "");
/*
if (!isset($_POST["categorieP"])) {

    echo "choisir une categorie ! ";
    exit;
}

*/
$res = array();
$res['data'] = array();


$rq = " SELECT count(distinct(fournisseurP)) AS nbFour, count(distinct(categorieP)) AS nbCat, SUM(qteP) AS nbProd FROM produit ";

$response = $connextion->query($rq);
$tab = $response->fetchAll(PDO::FETCH_ASSOC);

$rqE = " SELECT count(id) AS nbEntree FROM bonEntree ";
$responseE = $connextion->query($rqE);
$tabE = $responseE->fetchAll(PDO::FETCH_ASSOC);

$rqS = " SELECT count(id) AS nbSortie FROM bonSortie ";
$responseS = $connextion->query($rqS);
$tabS = $responseS->fetchAll(PDO::FETCH_ASSOC);


/*
echo '<pre>';
print_r($tabE[0]['nbEntree']);
echo '</pre>';
exit;
*/
foreach ($tab as $t) {

    $index["nbProd"] = $t["nbProd"];
    $index["nbFour"] = $t["nbFour"];
    $index["nbCat"] = $t["nbCat"];
    $index["nbMvt"] = $tabE[0]['nbEntree'] + $tabS[0]['nbSortie'];

    array_push($res['data'], $index);
}
if (count($tab)) {

    $res["success"] = "1";
} else {
    $res["success"] = "0";
}
echo json_encode($res);
