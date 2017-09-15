package anthony.apps.carbonmiao.authorization.controller;

import anthony.apps.carbonmiao.authorization.service.AuthorizationService;
import anthony.apps.carbonmiao.system.config.SystemConfigParameters;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "token/{userName}", method = GET)
    public String getToken(@PathVariable("userName") String userName, @RequestParam("passWord") String passWord) {
        passWord = authorizationService.decode(userName, passWord);
        return authorizationService.getToken(userName, passWord);
    }

}
