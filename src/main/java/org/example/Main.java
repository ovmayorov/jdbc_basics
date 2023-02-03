package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.AuthorDaoImpl;
import org.example.model.Author;
import org.example.model.Book;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Не удалось загрузить драйвер");
//        }

        String url = "jdbc:postgresql://localhost:5432/lesson_2";
        String user = "postgres";
        String password = "123";

        AuthorDao dao = new AuthorDaoImpl(url, user, password);
        Author author = dao.getById(2);
        System.out.println("Author: " + author.getName());
        System.out.println("Books: ");
        author.getBooks().forEach(book -> System.out.println(book.getTitle()));
    }
}
