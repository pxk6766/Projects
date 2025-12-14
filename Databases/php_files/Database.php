// All work done by both Pranita Khanal (ID: 1002156766) and Ryan Patterson (ID: 1002190591)

<?php

class Database
{
    public function getDatabaseConnection()
    {
        $dbHost = "localhost:3306";
        $dbName = "best_price_store"; // Please set your database name here
        $dbUser = "root"; // Please set your database username here
        $dbPassword = ""; // Please set your database password here

        try {
            $dbConnection = new PDO(
                "mysql:host=" . $dbHost . ";dbname=" . $dbName,
                $dbUser, $dbPassword
            );
            $dbConnection->setAttribute(
                PDO::ATTR_ERRMODE,
                PDO::ERRMODE_EXCEPTION,
            );
            return $dbConnection;
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }
}
?>