package com.example.springsecurity;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {
        @Test
        public void method(){
            int i = 5;
            System.out.println(i+1 + i+1 +i+1);
        }

}
