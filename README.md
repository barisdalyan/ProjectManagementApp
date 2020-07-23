# Java Swing Project Management 

A simple Swing application that is equipped with Database infrastructure.
>**_Scenario:_**  There are projects in your database table and you sell these projects to customers who are registered in another table object.

## What can ı do with this project?
- You can perform adding, deleting, updating operations on customer registration table and sell a new project to your customers or cancel the sale.
- If you have a lot of customer, you can filter them using search text field.

<img src="https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/list-customers-frame.png" alt="Error" width="550" >

## Annotations
- _This project is developed with Java-11 OpenJDK, Swing Api and Ms SQL Server._
- _Therefore, you should set MS SQL Server and paste your DB link to corresponding line (79) in [LoginDataBase.java.](https://github.com/barisdalyan/ProjectManagementApp/blob/master/src/login/LoginDataBase.java)_ 
- _You can reach Database UML Diagram [here.](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/UML.png)_
- _It is significant to add content of initComponents.txt to each .java file that in same directory, otherwise unexpected error may be occurred._
- _You need to change line (77) as well to your Ms SQL Server **username** and **password** otherwise you can't initialize this app._
- _Ms Server JDBC Driver and other necessary files are [here.](https://github.com/barisdalyan/ProjectManagementApp/tree/master/dist/lib)_

Except these annotations, you can modify this infrastructure as you wish.
I would prefer to [**Netbeans IDE**](https://netbeans.apache.org/download/index.html).

## Screenshot
- [Login](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/login-frame.png)
- [List Customers-Refresh](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/list-customers-refresh-frame.png)
- [Add Customer](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/adding-frame.png)
- [Update Customer](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/updating-frame.png)
- [Delete Customer](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/deleting-frame.png)
- [Filter Search](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/filter.png)
- [Sell Project](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/project-sale-frame.png)
- [Detailed Project Sale](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/detailed-projectsale-frame.png)
- [Jcalendar Date](https://github.com/barisdalyan/ProjectManagementApp/blob/master/screenshot/date.png)



