package ru.itis.mannapov.homework2;

import com.itextpdf.html2pdf.HtmlConverter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class DocConsumer {
    private final static String QUEUE = "document_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.basicConsume(QUEUE, false, (consumerTag, message) -> {
                try {
                    generatePdf(channel, message);

                } catch (ClassNotFoundException e) {
                    System.out.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void generatePdf(Channel channel, com.rabbitmq.client.Delivery message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(message.getBody());
        ObjectInputStream is = new ObjectInputStream(bis);
        Student student = (Student) is.readObject();
        String html = Render.render(student, "templates/document.ftl");
        OutputStream out = new FileOutputStream("pdf/document" + UUID.randomUUID() + ".pdf");
        HtmlConverter.convertToPdf(html, out);
        System.out.println("new pdf generated");
        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
    }
}
