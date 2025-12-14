// All work done by both Pranita Khanal (ID: 1002156766) and Ryan Patterson (ID: 1002190591)

<?php
require "./Service.php";
$service = new Service();
$items = null;
$searchPerformed = false;

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['submit'])) {
    $items = $service->searchItem();
    $searchPerformed = true;
}
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Search Item</title>
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

        <div class="menu">
            <a href="index.php">Home</a>
            <a href="searchItem.php">Search Item</a>
            <a href="addItem.php">Add Item</a>
            <a href="updateItem.php">Update Item</a>
            <a href="deleteItem.php">Delete Item</a>
        </div>
    
    <h1>Search Item</h1>
    
    <form method="POST">
        <fieldset>
            
            <label for="searchType">Search By:</label>
            <select name="searchType" id="searchType" required>
                <option value="Iname" <?= (isset($_POST['searchType']) && $_POST['searchType'] == 'Iname') ? 'selected' : ''; ?>>
                    Item Name
                </option>
                <option value="iId" <?= (isset($_POST['searchType']) && $_POST['searchType'] == 'iId') ? 'selected' : ''; ?>>
                    Item ID
                </option>
            </select>
            
            <label for="searchValue">Search Value:</label>
            <input
                type="text" 
                name="searchValue" 
                id="searchValue" 
                placeholder="Enter item name or ID"
                value="<?= isset($_POST['searchValue']) ? htmlspecialchars($_POST['searchValue']) : ''; ?>"
                required
            >
            
            <input type="submit" name="submit" value="Search">
        </fieldset>
    </form>
    
    <?php if ($searchPerformed): ?>
        <h2>Search Results</h2>
        
        <?php if (is_array($items) && count($items) > 0): ?>
            <table>
                <thead>
                    <tr>
                        <th>Item ID</th>
                        <th>Item Name</th>
                        <th>Store Price</th>
                        <th>Item Description</th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($items as $item): ?>
                        <tr>
                            <td><?= htmlspecialchars($item->iId) ?></td>
                            <td><?= htmlspecialchars($item->Iname) ?></td>
                            <td>$<?= htmlspecialchars($item->Sprice) ?></td>
                            <td><?= htmlspecialchars($item->Idescription) ?></td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
            <div class="message success">
                Found <?= count($items); ?> item(s) matching your search.
            </div>
        <?php else: ?>
            <div class="message info">
                No items found matching your search criteria.
            </div>
        <?php endif; ?>
    <?php endif; ?>
</body>
</html>