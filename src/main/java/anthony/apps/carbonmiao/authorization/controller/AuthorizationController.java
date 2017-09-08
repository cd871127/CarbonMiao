package anthony.apps.carbonmiao.authorization.controller;

import anthony.apps.carbonmiao.authorization.service.AuthorizationService;
import anthony.apps.carbonmiao.system.config.SystemConfigParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = SystemConfigParameters.PAGE_SERV, methods = {GET, POST, PUT})
public class AuthorizationController {

    @Resource
    private AuthorizationService authorizationService;

    @RequestMapping(value = "key/{userName}", method = GET)
    public String getPublicKey(@PathVariable("userName") String userName) {
        return authorizationService.getPublicKey(userName);
    }

}
