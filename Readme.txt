Paynment Tracker
----------------
Program keeps a record of payments. Program is controlled by commands.
Each payment includes a currency and amount. Program prints every 60s lists sums of all payments. If the net amount is 0, currency is not displayed. Inputs are typen into command line. When program starts you can load amounts and currencyes from file by typing "load"(everytime means without quotation marks). After it type name of file with extension. For example "file.txt" which is included. If you wont other file, must put it into PaymentTracker directory. The format of the file is one or more lines with Currency Code Amount like in the Sample:
USD 1000
HKD 100
USD -100
RMB 2000
HKD 200

You can load and add any time you wont any file. You can also add currency with amount. By typing "add". And than type for example:USD 100. Cyrencyes which can be add: USB, HKD, RMB, NZD, GBP. I counted conversion raport from your example for HKD and RMB. I wonted the same output as you had. For other Currencyes I searched on the internet. You can add as much currencyes with amouts as you wont. Each currency have the exchange rate compared to USD configured. When output is displayed, program writes the USD equivalent amount next to it. 
For example: RMB 2000 (USD 314.60)
If you do typing error, program tells you and program asks you if you wont to add next currency with amount. If you dont wont you can type any of commands. You can also show help, lists currencyes when you wont(no need to wait) and program ends by typing quit.

Commands used in the program:
-----------------------------
-they can be typed anytime

-quit- end the program
-add- add currency with amounth. Example:USB 100
-list- prints all sums of currencyes with amounts from database
-help- show classic help
-load- load value and add into database

Instructions how to run the application from the command line
-------------------------------------------------------------
Download & unzip or clone https://github.com/MatyRose/PaymentTracker . Open PaymentTracker-master directory. You will see classes directory, src directory, file.txt and Readme.txt. Open command line at this directory.(just copy and paste the path from directory. Type:javac -d classes -cp classes src/paymenttracker/*.java
Pres enter. After it type:java -cp classes paymentracker.PTmain
Program starts in the command line and you can test it.
 