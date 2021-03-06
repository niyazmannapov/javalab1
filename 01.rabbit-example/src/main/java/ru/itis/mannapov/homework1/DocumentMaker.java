package ru.itis.mannapov.homework1;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class DocumentMaker {

    private final static String EXCHANGE_NAME = "users";
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
                    render(channel, message);

                } catch (ClassNotFoundException | DocumentException e) {
                    System.out.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void render(Channel channel, com.rabbitmq.client.Delivery message) throws IOException, ClassNotFoundException, DocumentException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
        ObjectInputStream is = new ObjectInputStream(bis);
        User user = (User) is.readObject();
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("pdf/doc_" +
                UUID.randomUUID() + ".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
        Chunk chunk = new Chunk("User " + user.getFirstName() + " " + user.getLastName(), font);
        document.add(chunk);
        document.close();
        System.out.println("new pdf generated");
        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
    }
}
