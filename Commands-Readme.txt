* To build your project use command:
	mvn clean package -Dmaven.test.skip

* To launch your application, move into the target folder (cd target). Run the following command to run the application:
	java -jar springboot-recipe-service-0.0.1-SNAPSHOT.war

* Default credentials for MySQL:
	Username: root
	Password:

* To login to mysql instance: Open new terminal and use following command:
      a.	sudo systemctl enable mysql
      b.	sudo systemctl start mysql
      c.	mysql -u root -p
The last command will ask for password which is ‘pass@word1’

* Mandatory: Before final submission run the following command: 
	mvn test

* To ensure your code is saved and available for later use, remember to use the CTRL+Shift+B command on your code IDE.
   This will push or save the updated contents in the internal git/repository.
   It is also important to use CTRL+Shift+B before the final submission to evaluate the code quality.
