// All work done by both Pranita Khanal (ID: 1002156766) and Ryan Patterson (ID: 1002190591)

<?php

require "./Service.php";

$service = new Service();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $result = $service->updateItem();
}
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Update Item</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f4f4f4;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #e9e9e9;
        }
        .menu {
            margin-bottom: 20px;
        }
        .menu a {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .menu a:hover {
            background-color: #45a049;
        }
    </style>
    </head>

    <body>
        <h1>Best Price Grocery Store</h1>

        <div class = "menu">
            <a href="index.php">Home</a>
            <a href="searchItem.php">Search Item</a>
            <a href="addItem.php">Add Item</a>
            <a href="updateItem.php">Update Item</a>
            <a href="deleteItem.php">Delete Item</a>
        </div>

        <h2>Update Item</h2>

        <form method="post">
        <fieldset>
            <input type="text" name="iId" placeholder="ID"></br>
            <input type="text" name="Iname" placeholder="Name" ></br>
            <input type="text" name="Sprice" placeholder="Price" ></br>
            <input type="text" name="Idescription" placeholder="Description" ></br>
            <input id="button" type="submit" name="submit">
        </fieldset>
        <!-- <?= htmlspecialchars($result) ?> -->
    </body>

</html>