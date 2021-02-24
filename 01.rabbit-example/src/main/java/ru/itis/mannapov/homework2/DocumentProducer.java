package ru.itis.mannapov.homework2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.*;
import java.util.concurrent.TimeoutException;

public class DocumentProducer {

    private final static String DOCUMENT2_QUEUE = "document2_queue";
    private final static String DOCUMENT_QUEUE = "document_queue";

    private final static String DOCUMENT2_ROUTING_KEY = "document2";
    private final static String DOCUMENT_ROUTING_KEY = "document";
    private final static String DOCUMENTS_EXCHANGE = "documents";
    private final static String EXCHANGE_TYPE = "direct";

    public static void produce() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Channel channel = declareConnection(connectionFactory);
            File file = new File("students.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentFile;
            while ((currentFile = reader.readLine()) != null) {
                readFile(channel, currentFile, "transfer");
            }
            file = new File("documents.txt");

            reader = new BufferedReader(new FileReader(file));
            while ((currentFile = reader.readLine()) != null) {
                readFile(channel, currentFile, "document");
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Channel declareConnection(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DOCUMENTS_EXCHANGE, EXCHANGE_TYPE);
        channel.queueBind(DOCUMENT2_QUEUE, DOCUMENTS_EXCHANGE, DOCUMENT2_ROUTING_KEY);
        channel.queueBind(DOCUMENT_QUEUE, DOCUMENTS_EXCHANGE, DOCUMENT_ROUTING_KEY);
        return channel;
    }

    private static void readFile(Channel channel, String currentFile, String transfer) throws IOException {
        String[] data = currentFile.split(" ");
        Student student = Student.builder()
                .firstName(data[0]).lastName(data[1]).build();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(student);
        os.flush();
        bos.close();
        channel.basicPublish(DOCUMENTS_EXCHANGE, transfer, null, bos.toByteArray());
    }
}
