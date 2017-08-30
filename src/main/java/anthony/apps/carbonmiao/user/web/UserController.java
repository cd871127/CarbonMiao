package anthony.apps.carbonmiao.user.web;

import anthony.apps.carbonmiao.user.UserInfo;
import anthony.apps.carbonmiao.user.UserInfoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private UserInfoRepository userInfoRepository;

    private final static String USER_INFO_COLLECTION_NAME = "userInfo";

    @RequestMapping(method = POST)
    public UserInfo register(@RequestBody UserInfo userInfo, HttpServletResponse response) {
        if (mongoTemplate.find(Query.query(Criteria.where("userId").is(userInfo.getUserId())),
                UserInfo.class, USER_INFO_COLLECTION_NAME).isEmpty()) {
            mongoTemplate.save(userInfo, USER_INFO_COLLECTION_NAME);
            response.setStatus(SC_CREATED);
        } else {
            response.setStatus(SC_FORBIDDEN);
            userInfo = null;
        }
        return userInfo;
    }

    @RequestMapping(value = "{userId}", method = GET)
    public UserInfo login(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Criteria userIdCriteria = Criteria.where("userId").is(userId);
        Criteria passWordCriteria = Criteria.where("passWord").is(request.getHeader("passWord"));
        UserInfo userInfo = mongoTemplate.findOne(Query.query(userIdCriteria.andOperator(passWordCriteria)), UserInfo.class, USER_INFO_COLLECTION_NAME);
        if (null != userInfo) {
            response.setStatus(SC_OK);
        } else {
            response.setStatus(SC_FORBIDDEN);
        }
        return userInfo;
    }

    @RequestMapping(value = "{userId}", method = PATCH)
    public UserInfo modify(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Criteria userIdCriteria = Criteria.where("userId").is(userId);
        String passWord=request.getHeader("passWord");
        UserInfo userInfo=mongoTemplate.findAndModify(userIdCriteria, new Update(),UserInfo.class,USER_INFO_COLLECTION_NAME);

        return userInfo;
    }

//    @RequestMapping("save/{id}/{name}")
//    public String save(@PathVariable("id") String id, @PathVariable("name") String name) {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(id);
//        userInfo.setName(name);
//        userInfoRepository.save(userInfo);
//        return userInfo.toString();
//    }


//    @RequestMapping(value = "insert", method = GET)
//    public UserInfo insert() {
////        List<UserInfo> us = mongoTemplate.findAll(UserInfo.class);
////        us.forEach(System.out::println);
//        return userInfoRepository.findByName("chendong222").get(0);
////        return us.get(0);
//    }

    @RequestMapping(value = "insert2", method = GET)
    public Map<String, String> insert2() {
        List<UserInfo> us = mongoTemplate.findAll(UserInfo.class);
        us.forEach(System.out::println);
        Map<String, String> m = new HashMap<>();
        m.put("aaaa", "bbbb");
        return m;
    }


    //    cmd /k "%ConEmuDir%\..\init.bat"  -new_console:p:n:d:%USERPROFILE% &bash
//    @RequestMapping(value = "{userId}", method = GET)
//    public String userInfo(@PathVariable("userId") String userId) {
//        System.out.println(userId + "11234112");
//        System.out.println(userId + "asdasdasd");
//        System.out.println(userId + "asdasdasd");
//        return null;
//    }

}
