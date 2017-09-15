package anthony.apps.carbonmiao.user.service;

import anthony.apps.carbonmiao.authorization.service.AuthorizationService;
import anthony.apps.carbonmiao.common.util.ServiceResult;
import anthony.apps.carbonmiao.user.dao.UserInfoDAO;
import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserInfoDAO userInfoDAO;
    @Resource
    private AuthorizationService authorizationService;

    public void re() {

    }

    public String register(UserInfoDTO userInfoDTO) {
        ServiceResult<String> result = new ServiceResult<>();
        String oriPassword = authorizationService.decode(userInfoDTO.getUserName(), userInfoDTO.getPassWord());
        userInfoDTO.setPassWord(oriPassword);
        userInfoDAO.addUserInfoDTO(userInfoDTO);
        result.setSuccess();
        result.setResultData(authorizationService.getToken(userInfoDTO.getUserName(), userInfoDTO.getPassWord()));
        return result.toJSON();
    }

    public String isUserExist(String userName) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        result.setResultData(true);
        if (null == userInfoDAO.findUserInfoDTOByUserName(userName)) {
            result.setResultData(false);
        }
        result.setSuccess();
        return result.toJSON();
    }
}
