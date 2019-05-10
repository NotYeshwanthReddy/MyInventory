# MyInventory

<sub>
    Created on 22-04-18.<br/>
    Developer - Yeshwanth Reddy.
</sub>


  This Project is a food ordering app made specifically for our college purpose. <br/>
  It uses MySQL as database for storing user details. The orders from the android app can be viewed frim the desktop application whick is available in support files folder. <br/>
  We have 3 food points in the campus, Nescafe, HOD(hot oven Delivery) and TMP(tera mera point). <br/>
  So the app consists of 3 tabs consisting of items each food points offer. <br/>

Setup : <br/>
  The App needs a local host with my_inventory database whose SQL file is provided in the Support_Files folder. Just open the Web Server, go to import DB and select this file(my_inventory.sql). <br/>
  Please note that, on setting up the local host, Update the IP address of your localhost in the BackgroundTask.java file in line number 79, 80, 81, 82. (check you IP address in cmd using ipconfig) <br/>
  The links are of the .php files which are also available in the Support_Files Folder. <br/>
  I'm using Wamp server and I put my .php files in (C:\wamp64\www\my_inventory) just in case, as the localhost URL depends on the location of the files also. <br/>

Testing : <br/>
  After setting up the database, PHP files and updating the URL in .java file, run the emulator and try to register into the app. <br/>
  Check if the Localhost DB got updated with the new customer or not. If yes, the setup was succesfull. else go through the setup steps once again. <br/>

Windows app: <br/>
  To check the Orders and mark them as completed, I made an windows app in NetBeans. <br/>
  The Windows app is in the Windows_Application Folder. There is no setup required for the windows app. <br/>
  Open the folder, Open any one of the food point folder, open the dist folder then the .jar file(OSProjectBuild_27-04-2018\NESapp\dist\NESapp.jar). <br/>
  You can see your order in the list once it refreshes. <br/>
 
