package net.karpisek.grpcsandbox.server;

import io.grpc.stub.StreamObserver;
import net.karpisek.grpcsandbox.EchoRequest;
import net.karpisek.grpcsandbox.EchoResponse;
import net.karpisek.grpcsandbox.EchoServiceGrpc.EchoServiceImplBase;

public class EchoServiceImpl extends EchoServiceImplBase {

	@Override
	public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
		EchoResponse response = EchoResponse.newBuilder().setText(request.getText()).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}
