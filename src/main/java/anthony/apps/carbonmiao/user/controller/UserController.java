package anthony.apps.carbonmiao.user.controller;

import anthony.apps.carbonmiao.system.config.SystemConfigParameters;
import anthony.apps.carbonmiao.user.dao.UserInfoDAO;
import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import anthony.apps.carbonmiao.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = SystemConfigParameters.PAGE_SERV, methods = {GET, POST, PATCH, DELETE})
public class UserController {

    @Resource
    private UserInfoDAO userInfoDAO;
    @Resource
    private UserService userService;

    @RequestMapping(method = POST)
    public String register(@RequestBody UserInfoDTO userInfoDTO) {
        return userService.register(userInfoDTO);
    }

    @RequestMapping(value = "{userName}", method = POST)
    public String isUserExist(@PathVariable String userName) {
        String res= userService.isUserExist(userName);
        System.out.println(res);
        return res;
    }

    @RequestMapping(value = "{userName}", method = GET)
    public UserInfoDTO login(@PathVariable("userName") String userName, @RequestParam("passWord") String passWord, HttpServletResponse response) {
        UserInfoDTO userInfoDTO = userInfoDAO.findUserInfoDTOByUserName(userName);
        if (null != userInfoDTO
                && null != userInfoDTO.getPassWord()
                && userInfoDTO.getPassWord().equals(passWord)) {
            response.setStatus(SC_OK);
        } else {
            response.setStatus(SC_FORBIDDEN);
            userInfoDTO = null;
        }
        return userInfoDTO;
    }

    public UserInfoDTO modify(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap<>();
        param.put("passWord", request.getHeader("passWord"));
        return userInfoDAO.updateUserInfoDTOByUserName(userName, param);
    }

    public void delete(@PathVariable("userName") String userName) {

        UserInfoDTO userInfoDTO = userInfoDAO.deleteUserInfoDTOByUserName(userName);


    }


}
