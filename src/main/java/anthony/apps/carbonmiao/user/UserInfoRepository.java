package anthony.apps.carbonmiao.user;

import anthony.apps.carbonmiao.user.dto.UserInfoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfoDTO,String> {
}
