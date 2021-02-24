package ru.itis.mannapov.homework2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class StudentHandler {

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
                    // read user
                    ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
                    ObjectInputStream is = new ObjectInputStream(bis);
                    Student student = (Student) is.readObject();
                    Scanner sc = new Scanner(System.in);
                    int type = sc.nextInt();
                    if (type == 1) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new FileWriter("students.txt", true)), true);
                        out.println(student.getFirstName() + " " + student.getLastName());
                        out.close();
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    } else if (type == 2) {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new FileWriter("documents.txt", true)), true);
                        out.println(student.getFirstName() + " " + student.getLastName());
                        out.close();
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    } else {
                        //
                        channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    }

                } catch (ClassNotFoundException e) {
                    System.out.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
