package fun.juhua.library_springboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.juhua.library_springboot.entity.Book;
import fun.juhua.library_springboot.entity.BookElectronicResource;
import fun.juhua.library_springboot.mapper.BookMapper;
import fun.juhua.library_springboot.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;

    @Override
    public List<Book> getBookList(String bookName, String bookID) {
//        List<Book> bookList = null;
//        QueryWrapper<Book> wrapper = new QueryWrapper<>();
//        if ((bookName == null || bookName == "") && (bookID == null || bookID == "")) {
//            bookList = bookMapper.selectList(null);
//        } else if ((bookID == null || bookID == "")) {
//            bookList = bookMapper.selectList(wrapper.like("bookName", bookName));
//        } else {
//            bookList = bookMapper.selectList(wrapper.eq("bookID", bookID));
//        }
//        return bookList;

        List<Book> bookList = new ArrayList<>();
        QueryWrapper<Book> wrapper = new QueryWrapper<>();

        if (bookID != null && !bookID.isEmpty()) {
            // 根据书籍ID查询书籍及其所有电子资源
            Book bookWithResources = bookMapper.selectBookWithResources(bookID);
            if (bookWithResources != null) {
                bookList.add(bookWithResources);
            }
        } else if (bookName != null && !bookName.isEmpty()) {
            // 根据书名查询书籍，不涉及电子资源
            wrapper.like("bookName", bookName);
            bookList = bookMapper.selectList(wrapper);
        } else {
            // 查询所有书籍及其电子资源
            List<Book> allBooks = bookMapper.selectList(wrapper);
            for (Book book : allBooks) {
                Book fullBook = bookMapper.selectBookWithResources(book.getBookID());
                if (fullBook != null) {
                    bookList.add(fullBook);
                }
            }
        }

        return bookList;

    }

    @Override
    public Book findById(String bookID) {
        return bookMapper.selectById(bookID);
    }

    @Override
    public int updateBook(Book book) {
        if (book.getBookSum() < book.getBookLend()) {
            return 0;
        }
        return bookMapper.updateById(book);
    }

    @Override
    public int deleteBook(String bookID) {
        return bookMapper.deleteById(bookID);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public int returnBook(String bookID) {
        Book book = findById(bookID);
        //如果借出数量大于库存,已借数量小于等于0，返回不成功
//        if (book.getBookSum() < book.getBookLend() || book.getBookLend() < 1) {
//            return 0;
//        }
        book.setBookLend(book.getBookLend() - 1);
        return bookMapper.updateById(book);
    }

    @Override
    public int check(String bookID) {
        Book book = findById(bookID);
        if (book == null || book.getBookSum() <= book.getBookLend()) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }


}
