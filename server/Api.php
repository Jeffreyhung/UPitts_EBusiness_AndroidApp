<?php
	//initial connection
	include_once dirname(__FILE__) . '/Constants.php';
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

	//Create Prepared Statement
	//$statement = "SELECT name, price, location, category, company, link, image FROM wearable_devices";
	$statement = "SELECT name, price, location, category, company, link FROM wearable_devices";
	if (count($_GET) != 0){
		$statement .= " WHERE ";
	}
	foreach($_GET as $key => $value)
	{
		//https://www.upitt-dbms.tk/AndroidApp/Api.php?PriceMax=100&PriceMin=0&Search=Garmin&Company=company&Category=fitness,entertainment&Location=hands,wrist
	   switch ($key) {
	   	case 'Search':
	   		$tem = $value;
	   		$statement .= "( name LIKE '%". $tem . "%' ) && ";
	   		break;
	   	case 'Company':
	   		$tem = $value;
	   		$statement .= "( company LIKE '%". $tem . "%' ) && ";
	   		break;
	   	case 'PriceMin':
	   		$tem = $value;
	   		$statement .= "( price  > ". (int)$tem . " ) && ";
	   		break;
	   	case 'PriceMax':
	   		$tem = $value;
	   		$statement .= "( price  < ". (int)$tem . " ) && ";
	   		break;
	   	case 'Location':
	   		$tem = explode(",", $value);
	   		$statement .= " ( ";
	   		for ($i = 0; $i < count($tem); $i++){
	   			$statement .= "( location  = '". $tem[$i] . "' ) || ";
	   		}
	   		$statement = rtrim($statement,"|| ");
	   		$statement .= " ) && ";
	   		break;
	   	case 'Category':
	   		$tem = explode(",", $value);
	   		$statement .= " ( ";
	   		for ($i = 0; $i < count($tem); $i++){
	   			$statement .= "( category  = '". $tem[$i] . "' ) || ";
	   		}
	   		$statement = rtrim($statement,"|| ");
	   		$statement .= " ) && ";
	   		break;
	   	default:
	   		break;
	   }
	}
	// remove redundant characters
	$statement = rtrim($statement,"&& ");
	//get data from database
	$stmt = $conn->prepare($statement);
	$stmt->execute();
	$results = array();
	$result = $stmt->get_result();
	$outp = $result->fetch_all(MYSQLI_ASSOC);

	// output data in json format
	echo json_encode($outp);
	$conn->close();
?>