package com.rs.retailstore.model;

import lombok.Builder;
import lombok.Data;

@Data //Khởi tạo các hàm như: getter, setter, toString ...
@Builder //Hỗ trợ gá dữ liệu vào cho một đối tượng Geeting
public class Greeting {
    private long id;
    private String content;
}
