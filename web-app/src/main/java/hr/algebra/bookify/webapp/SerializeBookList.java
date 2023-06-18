package hr.algebra.bookify.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SerializeBookList {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeBookList.class);

    public static String serializeBookList(ArrayList<Book> books) {
        String fileName = "books.ser";

        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(books);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public static List<Book> deserializeBookList(String fileName) {
        List<Book> list = new ArrayList<>();

        Set<Class<?>> whitelist = new HashSet<>();
        whitelist.add(Book.class);

        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            while (fileIn.available() > 0) {
                ArrayList<?> arrayList = (ArrayList<?>) in.readObject();

                for (Object element : arrayList) {
                    Class<?> elementClass = element.getClass();

                    if (whitelist.contains(elementClass)) {
                        list.add((Book) element);
                    } else {
                        throw new SecurityException("Object of non-deserializable class " + elementClass.getSimpleName() + " detected");
                    }
                }
            }

        } catch (SecurityException se) {
            LOGGER.error(se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
}