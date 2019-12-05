This project uses lombok jar.

If lombok is not yet installed in the IDE,
download jar file at https://projectlombok.org/download.

After downloading jar file,
run java -jar /path/to/jar/lombok.jar and follow instructions on how to install.

Application can be run via IDE or command line.
For IDE, run as java application and supply /path/to/csv/yourcsv.csv as argument.

For command line, create a maven build run configuration with clean install.
Then run java -jar ${project.build.directory}/hoolah-1.0.0.jar /path/to/csv/yourcsv.csv in the CLI.

Notes:
1. I did not use any autowiring mechanism as importing Spring dependencies
   just for the autowire feature will bloat my file size.
2. No logging mechanism was introduced in the project.
3. Application will terminate after one run.
4. Input parameters will be handled via Java Scanner API.
