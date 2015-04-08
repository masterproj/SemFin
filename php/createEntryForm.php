<?php
	session_start();
	
	// Check if user is already locked in
	if(isset($_SESSION['user']))
	{
				
		if($_GET[req] != "")
		{		
		require_once("db_data.php");

		mysql_select_db($database, $connection) or die ("Database not found!");
		
		// Create request

		$request = "SELECT * FROM individuen WHERE nickname='$_GET[nickname]'";
		$result = mysql_query($request) or die ("Query is not valid!");
		
		if($_GET[password] == '' || $_GET[nickname] == '')
		{
			echo "empty nick or pw";
		} else {
			if($r = mysql_fetch_array($result))
			{
				$request = "UPDATE individuen SET password='". $_GET[password] ."' WHERE nickname='". $_GET[nickname] ."'" ;
			} else {
				$request = "INSERT INTO individuen (nickname,password) VALUES ( '". $_GET[nickname] ."' , '". $_GET[password] ."')" ;
			}
			echo $request . "<br/>";
		
			$reqset = mysql_query($request) or die ("SET Query is not valid!");		
		}

		$request = "$_GET[req]";
		$result = mysql_query($request) or die ("Query is not valid!");
		
		// Close connection
		mysql_close($connection);
		
			echo("<p>Daten</p>\n");
			
			
			$rows = array();
			while($r = mysql_fetch_array($result)) {
			$rows[] = $r;
			}

			$jason = json_encode($rows);
			echo json_encode($rows);

			//while($data = mysql_fetch_row($result))
			//echo $data[0].' , '.$data[1]. ' , ' . $data[2] .'<br />';
			
			echo "\n<br>\n";
			
			$jasohn = json_decode($jason);
			var_dump($jasohn);
			
			}
	}
	else
	{
		echo "<p>Nö!</p>";
	}
?>

<html>
<head><title>Create Entry</title>
</head>

<body>

	<form action='createEntryForm.php' method='GET'>	
		<table>
		<tr><td>req:</td><td><input name='req' value='SELECT * FROM individuen' type='text' size='30'/></td></tr>
		<tr><td>Nick:</td><td><input name='nickname' value='<?php echo $rows[0]["nickname"]; ?>' type='text' size='30'/></td></tr>
		<tr><td>Passwort:</td><td><input name='password' value='<?php echo $rows[0]["password"]; ?>' type='text' size='30'/></td></tr>	
		<tr><td></td><td><input type='submit' value='make it so' /></td></tr>
		</table>
	</form>

</body>
</html>
