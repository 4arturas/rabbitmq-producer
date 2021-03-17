package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
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
        System.out.println("####################################");

        Connection connection = null;
        Channel channel = null;
        try
        {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for ( int i = 0; i < 1000_000_000; i++ )
            {
                String message = "" + i;

                boolean bMessageWasSent = send_Message( channel, message );
                if ( bMessageWasSent == false )
                {
                    connection = factory.newConnection();
                    channel = connection.createChannel();
                    System.err.println("#########################");
                    System.err.println("# Connection was lost, reconnecting");
                    System.err.println("#########################");
                }


                System.out.println(" [x] Sent '" + message + "'");

                Thread.currentThread().sleep(10);
            } // end for
            System.exit(0);
        }
        finally
        {
            if ( channel != null )
                channel.close();

            if ( connection != null )
                connection.close();
        }
    }

    static boolean send_Message( Channel channel, String message )
    {
        try
        {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch ( Exception e )
        {
            return false;
        }
    }
}
