package net.karpisek.grpcsandbox.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.karpisek.grpcsandbox.EchoRequest;
import net.karpisek.grpcsandbox.EchoResponse;
import net.karpisek.grpcsandbox.EchoServiceGrpc;

public class GrpcClient {
	public static void main(String[] args) throws InterruptedException {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

		EchoServiceGrpc.EchoServiceBlockingStub stub = EchoServiceGrpc.newBlockingStub(channel);
		EchoResponse response = stub.echo(EchoRequest.newBuilder().setText("test").build());
		System.out.printf("Response from server:%s\n", response);
		
		channel.shutdown();
	}
}
