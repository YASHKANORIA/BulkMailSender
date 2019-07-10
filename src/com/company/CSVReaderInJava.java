package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVReaderInJava {

    public static void main(String... args) {
        List<Contact> contacts = readBooksFromCSV("yash.csv");


        for (Contact b : contacts) {
            Mailer mailer=new Mailer();
            String subject="hello "+b.getName();
            String body="How r u "+b.getName()+"! I am good <br><br>Best Regards<br><b>Yash Kanoria</b>";
            mailer.send(b.getEmail(),subject,body);
        }
    }

    private static List<Contact> readBooksFromCSV(String fileName) {
        List<Contact> contacts = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);


        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {


            String line = br.readLine();


            while (line != null) {


                String[] attributes = line.split(",");

                Contact contact = createBook(attributes);


                contacts.add(contact);


                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return contacts;
    }

    private static Contact createBook(String[] metadata) {
        String name = metadata[0];
       String email = metadata[1];


        return new Contact(name,email);
    }

}



