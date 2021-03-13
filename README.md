# rabbitmq-producer

## Install local docker registry
````
docker run -d -p 5000:5000 --restart=always --name registry registry:2
````

## Build image and push to the local docker registry
````
mvn clean install jib:dockerBuild && docker tag rabbitmq-producer localhost:5000/rabbitmq-producer && docker push localhost:5000/rabbitmq-producer
````
## Run image
````
docker run -it --rm localhost:5000/rabbitmq-producer
````



````mermaid
graph TD;
A[write code] --> B{Does it work?}
B -- Yes --> C[Great!]
B -- No --> D[Google]
D --> A
````

