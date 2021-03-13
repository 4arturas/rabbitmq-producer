package demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Producer
{
    private final static String QUEUE_NAME = "hello";

    private static String RABBITQM_RELAY_HOST;
    private static int RABBITQM_RELAY_PORT;

    public static void main(String[] argv) throws Exception
    {
        RABBITQM_RELAY_HOST = System.getenv("RABBITQM_RELAY_HOST");
        RABBITQM_RELAY_PORT = Integer.parseInt(System.getenv("RABBITQM_RELAY_PORT"));

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( RABBITQM_RELAY_HOST );
        factory.setPort( RABBITQM_RELAY_PORT );
//        factory.setPort(8181);
        try
                (
                        Connection connection = factory.newConnection();
                        Channel channel = connection.createChannel()
                )
        {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for ( int i = 0; i < 10; i++ )
            {
                String message = "" + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(10);
            } // end for
        }
    }
}