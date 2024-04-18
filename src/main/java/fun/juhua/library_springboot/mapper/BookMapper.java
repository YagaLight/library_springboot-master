package fun.juhua.library_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.juhua.library_springboot.entity.Book;
import fun.juhua.library_springboot.entity.BookElectronicResource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper extends BaseMapper<Book> {

    // 根据书籍ID查询书籍及其所有电子资源
    @Select("SELECT b.bookID, b.bookName, b.bookAuthor, b.bookPublisher, b.publishTime, b.bookPrice, b.bookSum, b.bookLend, b.tag, b.isbn FROM book b WHERE b.bookID = #{bookId}")
    @Results(id = "BookWithResourcesMap", value = {
            @Result(property = "bookID", column = "bookID"),
            @Result(property = "bookName", column = "bookName"),
            @Result(property = "bookAuthor", column = "bookAuthor"),
            @Result(property = "bookPublisher", column = "bookPublisher"),
            @Result(property = "publishTime", column = "publishTime"),
            @Result(property = "bookPrice", column = "bookPrice"),
            @Result(property = "bookSum", column = "bookSum"),
            @Result(property = "bookLend", column = "bookLend"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "electronicResources", column = "bookID", javaType = List.class,
                    many = @Many(select = "fun.juhua.library_springboot.mapper.BookMapper.selectElectronicResourcesByBookId"))
    })
    Book selectBookWithResources(String bookId);

    // 辅助方法，用于加载特定书籍的电子资源

    @Select("SELECT id, book_id, resource_type, resource_url FROM book_electronic_resources WHERE book_id = #{bookId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "bookID", column = "book_id"),
            @Result(property = "resourceType", column = "resource_type"),
            @Result(property = "resourceUrl", column = "resource_url")
    })

    List<BookElectronicResource> selectElectronicResourcesByBookId(String bookId);

    // 方法来加载所有书籍及其电子资源
    @Select("SELECT * FROM book")
    @ResultMap("BookWithResourcesMap")
    List<Book> selectAllBooksWithResources();

}
