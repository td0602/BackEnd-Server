package com.rs.retailstore.controler;

import com.rs.retailstore.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController //anotation đánh dấu class API --> kiểu trả về là ResponseBody
@RequestMapping("/v1") //version 1
public class CustomerGreetingController {

    private static final String greetingTemplate = "Hello, %s %s";
    //AutomicLong hỗ trợ generic counter tăng dần cho id
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    //RequestParam là khi ta truyền vào url: vd: ...&customerName=Luong&gender=1
    public Greeting greeting(@RequestParam(value = "gender", defaultValue = "0") boolean gender,
                             @RequestParam(value = "customerName", defaultValue = "Customer") String customerName
                             ){
        //buider hỗ trợ gná sữ liệu cho một đối tượng, ở đây là đt Greeting
        return Greeting.builder()
                .id(counter.incrementAndGet()) //mỗi lần request tự động tăng lên 1
                .content(String.format(greetingTemplate, gender ? "Mr." : "Ms.", customerName))
                .build();

    }
}
