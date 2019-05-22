Android app development Group Project - INSFCI 2730 E-Business

Group Member:
Xinran Chen xic96@pitt.edu
Zhifeng Wang zhw81@pitt.edu
Chi-Heng Hung CHH162@pitt.edu
Chia-Jung Chang CHC276@pitt.edu

Instruction:
We developed an android app that can search for information about wearable devices[1] that are stored in our database.
To use the app you need to install the "Search_app.apk" in your device and launch it. 
Once you launch the app, you will see the search page that you can input information like the name, company, price range, location and the category. 
After you finish entering the conditions, click the "SEARCH" button, the app will lead you to a new page with the information of all the devices that match your condition. 
If no matched product found, the page will show "No Result Found".


Explaination:
We create a database with an opensource database[1], and uploaded it to a free online database system[2]. 
In order to access the database, we create a Api file with PHP (file located in Source Code/Server/Api.php) which can be called with HTTP Get method and will return result from the database with dynamic SQL request. 
In the android app, we will call the api with user's input and display the result we get from the api into new page. 



Reference: 
[1] Wearable Technology Database: https://data.world/crowdflower/wearable-technology-database
[2] 000WebHost: https://www.000webhost.com/
