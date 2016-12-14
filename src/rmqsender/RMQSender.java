
package rmqsender;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class RMQSender {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare("MyQeue", false, false, false, null);
        int loop = Integer.parseInt(args[0]);
        for(; loop >= 0; loop--) {
            String message = "Hello #" + loop;
            channel.basicPublish("", "MyQeue", null, message.getBytes());
            System.out.println("Sent: '" + message + "'");
        }
        channel.close();
        connection.close();
    }    
}
