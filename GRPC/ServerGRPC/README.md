# GRPC Application
## what is GRPC?
gRPC is a modern open source high performance Remote Procedure Call (RPC) framework that can run in any environment. It can efficiently connect services in and across data 
centers with pluggable support for load balancing, tracing, health checking and authentication. It is also applicable in last mile of distributed computing to connect devices, 
mobile applications and browsers to backend services.
## About The App
This application is about linking differents microapps via GRPC.
## BloomRPC

 BloomRPC aims to provide the simplest and most efficient developer experience for exploring and querying your GRPC service.

 
 ### Unary Model
 
 Unary RPCs where the client sends a single request to the server and gets a single response back.

 
<img width="960" alt="unary" src="https://github.com/aymanehed/GRPC_Application/assets/93987581/6472be80-0739-4df8-a630-cca1eaf92373">


### Server Streaming Model

A server-streaming RPC is similar to a unary RPC, except that the server returns a stream of messages in response to a client's request.


<img width="960" alt="server" src="https://github.com/aymanehed/GRPC_Application/assets/93987581/56fc8fc4-f885-4794-b6fa-6bf3b35fc7b9">


### Client Streaming Model

What is client streaming in gRPC?
Client streaming RPCs where the client writes a sequence of messages and sends them to the server, again using a provided 
stream. Once the client has finished writing the messages, it waits for the server to read them and return its response.


<img width="960" alt="client" src="https://github.com/aymanehed/GRPC_Application/assets/93987581/6fdf4df7-a938-486d-912d-421af1570250">
