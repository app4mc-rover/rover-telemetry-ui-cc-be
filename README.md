# cloud-application-telemetry-ui-backend with Hono C&C

## Implemented REST-API

### GET METHOD - `/telemetry`
This interface allows applications to get the telemetry data out of the used database for a in the application specified rover as JSON.

### POST METHOD - `/command-control`
This interface allows an application to move a rover by putting command and speed as JSON in a POST-Request.

## What to know about this application?
- Controller are the REST-API implementations which use services to implement business logic
- the package payload contains the needed classes to use as JSON packet for communication to frontend and other software
- application.properties is used to configure hono and influxdb

## What needs to be implemented ?
- modular implementation for the REST-API so that the frontend can decide which of the rover's data it wants or which rover it wants to control
- other use cases for the third-party backend for example integrating the appstore backend to this backend to make access consistent

## Run the application
To run the application enter the following command:
`mvn spring-boot:run`

# Please note ...

... to edit the `application.properties` at `/src/main/resources/` and add data for your Eclipse Hono, InfluxDB, and qpid.