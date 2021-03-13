# rabbitmq-producer

## Simple java application
````
docker build -t img-java-example -f DockerfileJavaExample .
````
````
docker run -it --rm img-java-example
````

````
mvn jib:dockerBuild
````
````
docker run -it --rm rabbitmq-producer
````




````mermaid
graph LR;
A[write code] --> B{Does it work?}
B -- Yes --> C[Great!]
B -- No --> D[Google]
D --> A
````

