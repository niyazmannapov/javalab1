package ru.itis.mannapov.homework2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            int type = sc.nextInt();
            if (type == 1) {
                StudentProducer.produce();

            } else if(type == 2) {
                DocumentProducer.produce();
            }
        }
    }
}
