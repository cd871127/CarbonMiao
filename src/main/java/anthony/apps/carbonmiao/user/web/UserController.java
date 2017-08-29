package anthony.apps.carbonmiao.user.web;

import anthony.apps.carbonmiao.user.UserInfo;
import anthony.apps.carbonmiao.user.UserInfoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private UserInfoRepository userInfoRepository;

    @RequestMapping("save/{id}/{name}")
    public String save(@PathVariable("id") String id, @PathVariable("name") String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName(name);
        userInfoRepository.save(userInfo);
        return userInfo.toString();
    }



    @RequestMapping(value = "insert",method = GET)
    public String insert() {
        List<UserInfo> us = mongoTemplate.findAll(UserInfo.class);
        us.forEach(System.out::println);
        return "ok";
    }


    //    cmd /k "%ConEmuDir%\..\init.bat"  -new_console:p:n:d:%USERPROFILE% &bash
    @RequestMapping(value = "{userId}", method = GET)
    public String userInfo(@PathVariable("userId") String userId) {
        System.out.println(userId + "11234112");
        System.out.println(userId + "asdasdasd");
        System.out.println(userId + "asdasdasd");
        return null;
    }
}
