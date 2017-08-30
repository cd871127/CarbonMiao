package anthony.apps.carbonmiao.user;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserInfoRepository extends MongoRepository<UserInfo,String> {
}
