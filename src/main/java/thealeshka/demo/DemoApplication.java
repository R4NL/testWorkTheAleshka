package thealeshka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import thealeshka.demo.logger.MyLogger;
import thealeshka.demo.thread.ThreadCreator;


@SpringBootApplication
public class DemoApplication {

    private static ThreadCreator tc;//sorry i know it is bad to do like that but time...

    public static void main(String[] args) {
        MyLogger logger=new MyLogger();
        SpringApplication.run(DemoApplication.class, args);
        tc = new ThreadCreator(2, 3,logger);
        tc.create();
    }

    public static ThreadCreator getTc() {
        return tc;
    }

    public static void setTc(ThreadCreator tc) {
        DemoApplication.tc = tc;
    }
}
