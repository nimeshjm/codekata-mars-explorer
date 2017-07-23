Mars Explorer
---
1. Visit: https://mars-explorer-counselable-enterclose.cfapps.io/rovers

2. The grid size is configurable in the application.yml file.  
The grid coordinates are 0 based.

3. The obstacles are configurable in the application.yml file.

4. Run 'mvn clean package' to generate the deployment artefact.  
5. Run 'mvn test' to run the tests.  

6. Run 'cf push' to deploy to a Cloud Foundry instance. You need to have the CF CLI installed as well as a Cloud Foundry account.

7. Run 'java -jar target/mars-explorer-0.1.0-SNAPSHOT.jar' to run in standalone mode.  

Sample:
---
1. Create a rover  
POST: https://mars-explorer-counselable-enterclose.cfapps.io/rovers  
Content-Type: application/json  
Body: {"position":{"x":0,"y":0},"direction":"N"}  

2. Get a rover
GET: https://mars-explorer-counselable-enterclose.cfapps.io/rovers/1  

3. Move a rover  
POST: https://mars-explorer-counselable-enterclose.cfapps.io/rovers/1  
Content-Type: application/json  
Body:{"commands": ["f", "b", "r", "f", "l", "b"]}

Todo:
---
The following items would need to be implemented to have a production strength API
* hateoas
* error handling
* logging
* caching
* security
* API catalogue e.g. swagger
* API gateway for microservices
* monitoring
* alerting
