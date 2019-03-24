package thealeshka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thealeshka.demo.thread.ThreadCreator;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
       // SpringApplication.run(DemoApplication.class, args);
        ThreadCreator tc=new ThreadCreator(2,3);
        tc.create();

    }

}
