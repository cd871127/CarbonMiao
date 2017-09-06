package anthony.apps.carbonmiao.authorization.controller;

import anthony.apps.carbonmiao.authorization.service.AuthorizationService;
import anthony.apps.carbonmiao.common.util.ServiceResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {GET, POST, PUT})
public class AuthorizationController {

    @Resource
    private AuthorizationService authorizationService;

    @RequestMapping(value = "publickey", method = GET)
    public String getPublicKey() {
        return authorizationService.getPublicKey();
    }

}
