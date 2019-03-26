package thealeshka.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import thealeshka.demo.DemoApplication;
import thealeshka.demo.buffer.Buffer;
import thealeshka.demo.thread.ThreadCreator;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MyController {


    @RequestMapping(value = "/", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String get() {
        JSONObject res = new JSONObject();
        List<String> list = DemoApplication.getTc().getBuffer();
        int i=0;
        for (String a:list) {
            res.put((i++)+"",a);
        }
        return res.toString();
    }
}
