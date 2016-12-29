# MDMS-Barcode-Project
Mobile Device Management System is developed for streamlining Mobile devices' checkin and checkout process.It uses Barcode scanning technique to perform the same.

Spring MVC framework<br />
Mysql for database<br />
Api’s used:<br />
1)Apache Common’s Mail API<br />
2)Barbecue Barcode Generator API<br />

# This application has 2 different roles:<br />
1) Admin <br />
2) User <br />

# Admin:

The admin role consists of 2 different modules:<br /><br />
1) generating barcodes for all users<br />
2) generating barcodes for all mobile devices<br />

# User:
The user role consists of only 1 module i.e. <br /><br />
1) Scanning the barcode<br />

-> User has to first scan the his own barcode provided over the email.<br />
->Then the user will be provided with a window of 60 seconds move on<br />
-> user will be asked to put the number of devices he/she needs to scan and as soon as the user inputs the number of devices,timer will be stopped.<br />
->User can not scan more than 5 devices at a time.<br />
->User can checkin or checkout devices as per their requirement and an email will be sent to the user with all the transactions for the record.<br />

# Future Enhancements:
Create a database table for userId and Password<br />
List of all devices which are booked can be displayed in tabular form for everyone<br />
If a user has kept the device for more than 5 days, an automated email should be sent to the person<br />
Right Now the application is running on a IDE in the iMac.I had to configure tomcat and deploy the application over there but due to short of time, I was not able to do it.<br />
