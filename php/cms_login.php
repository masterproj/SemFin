<?php
	session_start();
	
	// Check if user is already locked in
	if(isset($_SESSION['user']))
	{
		// User is well known
		echo "<p align=\"center\">Welcome back $_SESSION[user]</p>";
		echo "<meta http-equiv=\"refresh\" content=\"3; URL=cms_main.php\">";
	}
	else
	{
		// Check user input
		// Connect to database
		require_once("db_data.php");
		// Select table
		mysql_select_db($database, $connection) or die ("Database not found!");
		
		// Create request
		$request = "SELECT * FROM individuen WHERE nickname=\"$_POST[nick]\"";
		$result = mysql_query($request) or die ("Query is not valid!");
		
		// Check if user is known
		if(mysql_num_rows($result) > 0)
		{
			$data = mysql_fetch_object($result);
			// Check if password is correct
			//
			if($_POST['password'] == $data->password)
			{
				// Set session id
				$_SESSION['user'] = $_POST['nick'];
				
				// Create request
				$request = "SELECT * FROM individuen WHERE nickname=\"$_POST[nick]\"";
				$result = mysql_query($request) or die ("Query is not valid!");
								
				while($r = mysql_fetch_array($result))
				{
					// Print data
					echo $r[0].",".$r[1].",".$r[2]."\n";
				}
			}
			else
			{
				echo "<p align=\"center\">Password is incorrect!</p>";
				echo "<meta http-equiv=\"refresh\" content=\"3; URL=index.php\">";
			}
		}
		else
		{
			echo "<p align=\"center\">Username is incorrect!</p>";
			echo "<meta http-equiv=\"refresh\" content=\"3; URL=index.php\">";
		}
		// Close connection
		mysql_close($connection);
	}
?>