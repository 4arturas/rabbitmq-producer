# rabbitmq-producer

## Install local docker registry
````
docker run -d -p 5000:5000 --restart=always --name registry registry:2
````

## Simple java application
````
docker build -t img-java-example -f DockerfileJavaExample .
````
````
docker run -it --rm img-java-example
````

````
mvn clean install jib:dockerBuild
````
````
docker run -it --rm rabbitmq-producer
````
````
mvn clean install jib:dockerBuild && docker run -it --rm rabbitmq-producer
````

## Build image and push to the local docker registry
````
mvn clean install -DskipTests && docker build -t rabbitmq-producer . && docker tag rabbitmq-producer localhost:5000/rabbitmq-producer && docker push localhost:5000/rabbitmq-producer
mvn clean install -DskipTests && docker tag rabbitmq-producer localhost:5000/rabbitmq-producer && docker push localhost:5000/rabbitmq-producer

docker run -it --rm localhost:5000/rabbitmq-producer
````




````mermaid
graph TD;
A[write code] --> B{Does it work?}
B -- Yes --> C[Great!]
B -- No --> D[Google]
D --> A
````

