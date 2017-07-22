Visit: https://mars-explorer-counselable-enterclose.cfapps.io/

The grid size is configurable in the application.yml file.
The grid coordinates are 0 based.

The obstacles are configurable in the application.yml file, as a set of x,y coordinates.

Run 'mvn clean package' to generate the deployment artefact.  
Run 'mvn test' to run the tests.  

Run 'cf push' to deploy to a Cloud Foundry instance. You need to have the CF CLI installed as well as a Cloud Foundry account.

Run 'java -jar target/mars-explorer-0.1.0-SNAPSHOT.jar' to run in standalone mode.
