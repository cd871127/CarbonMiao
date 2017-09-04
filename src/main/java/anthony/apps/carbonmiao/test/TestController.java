package anthony.apps.carbonmiao.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/a")
    public String test() {
        return "123";
    }
}
