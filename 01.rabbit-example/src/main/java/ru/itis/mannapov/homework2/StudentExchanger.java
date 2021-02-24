package ru.itis.mannapov.homework2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeoutException;

public class StudentExchanger {

    private final static String EXCHANGE_NAME = "students";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
                    ObjectInputStream is = new ObjectInputStream(bis);
                    Student student = (Student) is.readObject();
                    System.out.println("First name length: " + student.getFirstName().length() + "; Last name length " +
                            student.getLastName().length());
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);

                } catch (ClassNotFoundException e) { System.out.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
