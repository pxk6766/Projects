// All work done by both Pranita Khanal (ID: 1002156766) and Ryan Patterson (ID: 1002190591)

<?php
require "./Database.php";
require "./Item.php";

class Service
{
    function fetchAllItems()
    {
        $dbObject = new Database();
        $dbConnection = $dbObject->getDatabaseConnection();

        $sql = "SELECT * FROM ITEM";

        $stmt = $dbConnection->prepare($sql);
        $stmt->setFetchMode(PDO::FETCH_CLASS, "Item");

        if ($stmt->execute()) {
            return $stmt->fetchALL();
        } else {
            return "Failed";
        }
    }

    function addItem()
    {
        $Iname = $_POST['Iname'];
        $Sprice = $_POST['Sprice'];
        $Idescription = $_POST['Idescription'];

        $dbObject = new Database();
        $dbConnection = $dbObject->getDatabaseConnection();

        $sql =
            "INSERT INTO ITEM (Iname, Sprice, Idescription) VALUES (?, ?, ?)";

        $stmt = $dbConnection->prepare($sql);
        if ($stmt->execute([$Iname, $Sprice, $Idescription])) {
            echo "Item added successfully";
        } else {
            echo "Failed";
        }
    }

    function updateItem()
    {
        $iId = $_POST["iId"];
        $Iname = $_POST["Iname"];
        $Sprice = $_POST["Sprice"];
        $Idescription = $_POST["Idescription"];

        $dbObject = new Database();
        $dbConnection = $dbObject->getDatabaseConnection();

        $sql =
            "UPDATE ITEM SET Iname = ?, Sprice = ?, Idescription = ? WHERE iId = ?";
        
        $stmt = $dbConnection->prepare($sql);
        if($stmt->execute([$Iname, $Sprice, $Idescription, $iId])) {
            echo "Item $iId updated successfully";
        } else {
            echo "FAILED TO UPDATE ITEM";
        }
    }

    function deleteItem()
    {
        $iId = $_POST["iId"];

        $dbObject = new Database();
        $dbConnection = $dbObject->getDatabaseConnection();

        $sql = "DELETE FROM ITEM WHERE iId = ?";
        $stmt = $dbConnection->prepare($sql);
        
        if($stmt->execute([$iId])) {
            echo "Item $iId deleted successfully";
        } else {
            echo "FAILED TO DELETE ITEM";
        }
    }

    function searchItem()
    {
        $searchType = $_POST["searchType"];
        $searchValue = trim($_POST["searchValue"]);
            
        $dbObject = new Database();
        $dbConnection = $dbObject->getDatabaseConnection();
            
        if ($searchType == 'Iname') {
            $sql = "SELECT * FROM ITEM WHERE Iname = ?";
            $searchValue = '' . $searchValue . '';
        } else {
            $sql = "SELECT * FROM ITEM WHERE iId = ?";
        }
        
        $stmt = $dbConnection->prepare($sql);
        $stmt->setFetchmode(PDO::FETCH_CLASS, "Item");

        if($stmt->execute([$searchValue])) {
            return $stmt->fetchAll();
        } else {
            return "Failed";
        }
    }
}
?>