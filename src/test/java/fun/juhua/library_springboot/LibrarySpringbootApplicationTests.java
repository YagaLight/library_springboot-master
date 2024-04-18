package fun.juhua.library_springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.juhua.library_springboot.entity.Book;
import fun.juhua.library_springboot.entity.Reader;
import fun.juhua.library_springboot.mapper.BookMapper;
import fun.juhua.library_springboot.mapper.ReaderMapper;
import fun.juhua.library_springboot.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LibrarySpringbootApplicationTests {
    @Resource
    ReaderMapper readerMapper;

    @Resource
    BookMapper bookMapper;

    @Resource
    BookService bookServiceImpl;


    @Test
    public void login() {
        String id = "r001";
        String password = "r0012";
        QueryWrapper<Reader> wrapper = new QueryWrapper<>();
        Reader reader = readerMapper.selectOne(wrapper.eq("id", id));
        Map<String, String> map = new HashMap<>();
        if (reader == null || !reader.getPassword().equals(password)) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }


    @Test
    public void testGetBookDetails() {
        String bookID = "b001";
        String bookName = "冬与狮";
        List<Book> bookList = bookServiceImpl.getBookList(bookName,bookID);
        if (bookList == null) {
            System.out.println("=================为空=================");
        }else{
            System.out.println("=================不为空，数据如下=================");
            System.out.println(bookList.get(0).getElectronicResources().get(0));
            System.out.println(bookList.get(0).getElectronicResources().get(0).getBookID());
            System.out.println(bookList.get(0).getElectronicResources().get(0).getResourceType());
            System.out.println(bookList.get(0).getElectronicResources().get(0).getResourceUrl());
        }
    }

}
