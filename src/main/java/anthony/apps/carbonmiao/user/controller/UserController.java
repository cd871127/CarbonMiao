package anthony.apps.carbonmiao.user.controller;

import anthony.apps.carbonmiao.system.config.SystemConfigParameters;
import anthony.apps.carbonmiao.user.dao.UserInfoDAO;
import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserInfoDAO userInfoDAO;

    @RequestMapping(method = POST)
    public UserInfoDTO register(@RequestBody UserInfoDTO userInfoDTO, HttpServletResponse response) {
        if (null == userInfoDAO.findUserInfoDTOByUserId(userInfoDTO.getUserId())) {
            userInfoDAO.addUserInfoDTO(userInfoDTO);
            response.setStatus(SC_CREATED);
        } else {
            response.setStatus(SC_FORBIDDEN);
            userInfoDTO = null;
        }
        return userInfoDTO;
    }



    @CrossOrigin(origins = SystemConfigParameters.PAGE_SERV, methods = GET)
    @RequestMapping(value = "{userId}", method = GET)
    public UserInfoDTO login(@PathVariable("userId") String userId, @RequestParam("passWord")String passWord, HttpServletResponse response) {
        UserInfoDTO userInfoDTO = userInfoDAO.findUserInfoDTOByUserId(userId);
        if (null != userInfoDTO
                && null != userInfoDTO.getPassWord()
                && userInfoDTO.getPassWord().equals(passWord)) {
            response.setStatus(SC_OK);
        } else {
            response.setStatus(SC_FORBIDDEN);
            userInfoDTO=null;
        }
        return userInfoDTO;
    }

    @RequestMapping(value = "{userId}", method = PATCH)
    public UserInfoDTO modify(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap<>();
        param.put("passWord", request.getHeader("passWord"));
        return userInfoDAO.updateUserInfoDTOByUserId(userId, param);
    }

    @RequestMapping(value = "{userId}", method = DELETE)
    public void delete(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        UserInfoDTO userInfoDTO = userInfoDAO.findUserInfoDTOByUserId(userId);
        String passWord = request.getHeader("passWord");
        if (null != userInfoDTO
                && null != userInfoDTO.getPassWord()
                && userInfoDTO.getPassWord().equals(passWord)) {
            userInfoDTO = userInfoDAO.deleteUserInfoDTOByUserIdAndPassWord(userId, passWord);
        }

        if (null != userInfoDTO) {
            //ok
        } else {
            //not ok
        }
    }



}
