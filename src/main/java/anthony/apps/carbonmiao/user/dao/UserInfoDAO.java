package anthony.apps.carbonmiao.user.dao;

import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class UserInfoDAO {
    @Resource
    private MongoTemplate mongoTemplate;

    private final static String USER_INFO_COLLECTION_NAME = "userInfo";

    public UserInfoDTO findUserInfoDTOByUserId(String userId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("userId").is(userId)), UserInfoDTO.class, USER_INFO_COLLECTION_NAME);
    }

    public void addUserInfoDTO(UserInfoDTO userInfoDTO) {
        mongoTemplate.save(userInfoDTO, USER_INFO_COLLECTION_NAME);
    }

    public UserInfoDTO updateUserInfoDTOByUserId(String userId, Map<String, String> param) {
        Update update = new Update();
        param.forEach(update::set);
        return mongoTemplate.findAndModify(Query.query(Criteria.where("userId").is(userId)),
                update, UserInfoDTO.class, USER_INFO_COLLECTION_NAME);
    }

    public UserInfoDTO deleteUserInfoDTOByUserIdAndPassWord(String userId, String passWord) {
        Criteria criteria = Criteria.where("userId").is(userId).andOperator(Criteria.where("passWord").is(passWord));
        return mongoTemplate.findAndRemove(Query.query(criteria), UserInfoDTO.class, USER_INFO_COLLECTION_NAME);
    }
}
