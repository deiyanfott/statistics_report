This project uses lombok jar.

If lombok is not yet installed in the IDE,
download jar file at https://projectlombok.org/download.

After downloading jar file,
run java -jar /path/to/jar/lombok.jar and follow instructions on how to install.

Application can be run via IDE or command line.
1. For IDE, run 'ReportGenerator.java' as a java application and supply /path/to/csv/yourcsv.csv as argument.
2. For command line, create a maven build run configuration with clean install or run 'mvn clean install' in the 
   terminal.<br>Then run java -jar ${project.build.directory}/statistics-1.0.0.jar /path/to/csv/yourcsv.csv in the CLI.

About the application:
1. This standalone application takes three inputs from the terminal, start date, end date, and merchant.
2. If no time is specified by the user, start date will default to 00:00:00 and end date will default to 23:59:59.
3. Based on the values provided, the program will print out the number of relevant records and the average amount.
4. Records with corresponding reversal entries will be removed before getting final results.
5. You may modify the csv files in the test package and values of the test classes to play around with the program.

Assumptions:
1. CSV file contains valid and correct values.
2. Records found in CSV are already ordered by date.

Notes:
1. I did not use any autowiring mechanism as importing Spring dependencies
   just for the autowire feature will bloat my file size.
2. No logging mechanism was introduced in the project.
3. Application will terminate after one run.
4. Input parameters will be handled via Java Scanner API.
