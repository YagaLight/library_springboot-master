package fun.juhua.library_springboot.service;

import fun.juhua.library_springboot.entity.Book;
import fun.juhua.library_springboot.entity.BookElectronicResource;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {


    List<Book> getBookList(String bookName, String bookID);

    Book findById(String bookID);

    int updateBook(Book book);

    int deleteBook(String bookID);

    int addBook(Book book);

    int returnBook(String bookID);

    int check(String bookID);

    List<Book> getAllBooks();  // 新增方法获取所有书籍及资源
}
