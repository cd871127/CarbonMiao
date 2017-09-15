package anthony.apps.carbonmiao.authorization.service;

import anthony.apps.carbonmiao.common.util.ServiceResult;
import anthony.apps.carbonmiao.user.dao.UserInfoDAO;
import anthony.apps.carbonmiao.util.Pair;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizationService {


    /**
     * pair中第一个string是String.valueOf(System.currentTimeMillis()
     */
    private Map<String, Pair<Long, RSAPrivateKey>> privateKeyMap = new HashMap<>();
    private Map<String, Pair<Long, String>> tokenMap = new HashMap<>();
    private final int KEY_SIZE = 1024;
    private final String KEY_ALGORITHM = "RSA";
    private final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private final long TOKEN_EXP = 10000L;
    private final long KEY_EXP = 10000L;

    @Resource
    private UserInfoDAO userInfoDAO;


    public String getPublicKey(String userName) {
        ServiceResult<String> result = new ServiceResult<>();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            String publicKeyStr = new BASE64Encoder().encode(publicKey.getEncoded());
            privateKeyMap.put(userName, new Pair<>(System.currentTimeMillis(), privateKey));
            result.setSuccess();
            result.setResultData(publicKeyStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            result.setFailed(e.getMessage());
        }
        return result.toJSON();
    }

    public String decode(String userName, String encryptedStr) {
        Pair<Long, RSAPrivateKey> pair = privateKeyMap.get(userName);
        if (null == pair || System.currentTimeMillis() - pair.getKey() >= KEY_EXP)
            return null;
        try {
            RSAPrivateKey privateKey = pair.getValue();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptedStr)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e1) {
            e1.printStackTrace();
        } finally {
            privateKeyMap.remove(userName);
        }
        return null;
    }

    public String getToken(String userName, String passWord) {
        ServiceResult<String> result = new ServiceResult<>();


        //验证用户密码
        boolean isOK = true;

        if (isOK) {
            Pair<Long, String> userTokenPair = tokenMap.get(userName);
            String userToken;
            //判断token是否存在和过期
            if (userTokenPair == null || System.currentTimeMillis() - userTokenPair.getKey() >= TOKEN_EXP) {
                //重新生成token
                userToken = userName;
            } else {
                userToken = userTokenPair.getValue();
            }

            tokenMap.put(userName, new Pair<>(System.currentTimeMillis(), userToken));

            result.setSuccess();
            result.setResultData(userToken);
        } else {
            result.setFailed("没有该用户或者密码错误");
        }
        return result.toJSON();
    }
}
