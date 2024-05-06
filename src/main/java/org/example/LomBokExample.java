package org.example;
import lombok.*;

@Data
@Builder
class MyStudent {
    private String name;
    private int rollNumber;
}


public class LomBokExample {
    @SneakyThrows
    public static void main(String[] args) {
        MyStudent s1 = MyStudent.builder().name("ABC").rollNumber(22).build();
        System.out.println(s1);

    }
}
