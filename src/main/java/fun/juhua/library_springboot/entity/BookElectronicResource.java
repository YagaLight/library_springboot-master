package fun.juhua.library_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class BookElectronicResource {
    @TableId(type = IdType.INPUT)
    private Integer id;
    private String bookID;  // 外键关联到Book表
    private String resourceType;
    private String resourceUrl;

    // 构造函数、getter和setter方法
    public BookElectronicResource() {
    }

    public BookElectronicResource(Integer id, String bookID, String resourceType, String resourceUrl) {
        this.id = id;
        this.bookID = bookID;
        this.resourceType = resourceType;
        this.resourceUrl = resourceUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }


    @Override
    public String toString() {
        return "BookElectronicResource{" +
                "id=" + id +
                ", bookID='" + bookID + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                '}';
    }
}
