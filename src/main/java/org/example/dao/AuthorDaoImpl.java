package org.example.dao;

import org.example.model.Author;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private String url;
    private String user;
    private String password;

    private GenreDao genreDao;

    public AuthorDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.genreDao = new GenreDaoImpl(url, user, password);
    }

    @Override
    public Author getById(int id) throws SQLException {
        try(Connection cnn = DriverManager.getConnection(url, user, password)) {
            String query =
                    "SELECT * FROM author a\n" +
                            "INNER JOIN book b on a.author_id = b.author_id\n" +
                            "WHERE a.author_id = ?";
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            ResultSet data = statement.getResultSet();
            Author author = new Author();
            data.next();

            author.setName(data.getString("name_author"));
            author.setId(data.getInt("author_id"));

            List<Book> books = new ArrayList<>();

            do {
                Book book = new Book();
                book.setAuthor(author);
                book.setId(data.getInt("book_id"));
                book.setAmount(data.getInt("amount"));
                book.setPrice(data.getDouble("price"));
                book.setTitle(data.getString("title"));
                book.setGenre(genreDao.getById(data.getInt("genre_id")));
                books.add(book);
            } while(data.next());

            author.setBooks(books);
            return author;
        }
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public void save(Author author) {

    }

    @Override
    public void delete(int id) {

    }
}
