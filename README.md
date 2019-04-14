# plugin-data-service-v2

Simple component that allows to handle requests of NPAW plugins.

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:
```
git@github.com:ajdafonte/plugin-data-service-v2.git
```

### Build and run component

Execute the following gradle task:
```
gradlew.bat build
```

### Running the tests

Execute the following gradle task:
```
gradlew.bat test
```

### Running the component
```
gradlew.bat bootRun
```

### Import into IDE

This is a Gradle project (build.gralde.kts). Here are some instructions on how to import a Gradle project in some of the most popular IDEs:
- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import
- Eclipse - https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

## Technical Comments

In this section will be described some technical details of this project.

### Stack
  
Here's a short summary of the tech stack used in the development of this component.
- Java 8, Jersey, Jetty, Gradle
- Other dependencies: 
    - slf4j, lombok  

### Component Configuration

- In file `clients-config.json` is possible to define all clients plugin configurations.
- Application is listening at port 80 (hardcoded)

### Component Functionality

#### Available endpoints

- `GET /pluginData` - Retrieve a plugin data information considering some query parameters.
    - Input data: accountCode, targetDevice and pluginVersion
        - Example of request URL: ``` http://localhost:80/api/pluginData?accountCode=clientA&targetDevice=XBox&pluginVersion=3.3.1 ```
        - Example of Curl: ``` curl -X GET "http://localhost/api/pluginData?accountCode=clientA&targetDevice=XBox&pluginVersion=3.3.1" -H "accept: application/xml" ```
    - Output data: hostName, pingTime, viewId
        - Example of response body: ``` <?xml version="1.0" encoding="UTF-8" standalone="yes"?> <q><h>clusterA.com</h><pt>10</pt><c>46ea39d3-f401-4961-aa01-a3046a810312</c></q> ``` 

### Additional Remarks

- In folder `misc`, there's a file named `plugin-data-service-v2-performance-test.jmx` that contain a [JMeter](https://jmeter.apache.org/) test plan in order 
to perform some performance validations of the available endpoints;
- Due to time constraints:
    - It's not possible to change clients plugin configurations at runtime
    - Jetty server configurations are hardcoded (port, threadpool settings, etc) 
    - Unit and integration tests are missing