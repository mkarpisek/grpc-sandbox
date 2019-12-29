package net.karpisek.grpcsandbox;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import net.karpisek.grpcsandbox.EchoServiceGrpc.EchoServiceBlockingStub;
import net.karpisek.grpcsandbox.server.EchoServiceImpl;

@RunWith(JUnit4.class)
public class EchoServerTest {

	@Rule
	public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	@Test
	  public void eho_replyMessage() throws Exception {
	    String serverName = InProcessServerBuilder.generateName();

	    grpcCleanup.register(InProcessServerBuilder
	        .forName(serverName).directExecutor().addService(new EchoServiceImpl()).build().start());

	    EchoServiceBlockingStub blockingStub = EchoServiceGrpc.newBlockingStub(
	        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));


	    EchoResponse reply = blockingStub.echo(EchoRequest.newBuilder().setText("hi").build());
	    assertEquals("hi", reply.getText());
	  }

}
