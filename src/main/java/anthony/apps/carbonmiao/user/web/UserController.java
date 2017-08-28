package anthony.apps.carbonmiao.user.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    cmd /k "%ConEmuDir%\..\init.bat"  -new_console:p:n:d:%USERPROFILE% &bash
    @RequestMapping("{userId}")
    public String userInfo(@PathVariable("userId") String userId) {
        System.out.println(userId+"1123e41112");
        System.out.println(userId+"1123e41112");
        return null;
    }
}
