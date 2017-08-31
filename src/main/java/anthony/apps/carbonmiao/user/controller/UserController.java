package anthony.apps.carbonmiao.user.controller;

import anthony.apps.carbonmiao.user.dao.UserInfoDAO;
import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "{userId}", method = GET)
    public UserInfoDTO login(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Criteria userIdCriteria = Criteria.where("userId").is(userId);
        Criteria passWordCriteria = Criteria.where("passWord").is(request.getHeader("passWord"));
        UserInfoDTO userInfoDTO = userInfoDAO.findUserInfoDTOByUserId(userId);
        if (null != userInfoDTO
                && null != userInfoDTO.getPassWord()
                && userInfoDTO.getPassWord().equals(request.getHeader("passWord"))) {
            response.setStatus(SC_OK);
        } else {
            response.setStatus(SC_FORBIDDEN);
        }
        return userInfoDTO;
    }

    @RequestMapping(value = "{userId}", method = PATCH)
    public UserInfoDTO modify(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> param = new HashMap<>();
        param.put("passWord", request.getHeader("passWord"));
        return userInfoDAO.updateUserInfoDTOByUserId(userId, param);
    }


}
