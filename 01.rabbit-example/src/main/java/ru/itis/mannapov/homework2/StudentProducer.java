package ru.itis.mannapov.homework2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class StudentProducer {
    private final static String EXCHANGE_NAME = "students";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void produce() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите имя и фамилию");
            Student student = Student.builder().firstName(sc.next()).lastName(sc.next()).build();
            // serialize user
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeObject(student);
            os.flush();
            bos.close();
            // publish
            channel.basicPublish(EXCHANGE_NAME, "",null, bos.toByteArray());
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
