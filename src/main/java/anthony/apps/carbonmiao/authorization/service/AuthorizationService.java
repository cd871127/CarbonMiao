package anthony.apps.carbonmiao.authorization.service;

import anthony.apps.carbonmiao.common.util.ServiceResult;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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

    private Map<String, RSAPrivateKey> privateKeyMap = new HashMap<>();
    private final int KEY_SIZE = 1024;
    private final String KEY_ALGORITHM = "RSA";
    private final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";


    public String getPublicKey() {
        ServiceResult<String> result = new ServiceResult<>();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            String publicKeyStr = new BASE64Encoder().encode(publicKey.getEncoded());
            privateKeyMap.put(publicKeyStr, privateKey);
            result.setSuccess();
            result.setResultData(publicKeyStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            result.setFailed(e.getMessage());
        }
        return result.toJSON();
    }

    public String decode(String publicKeyStr, String encryptedStr) {
        RSAPrivateKey privateKey = privateKeyMap.get(publicKeyStr);
        if (null == privateKey)
            return null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptedStr)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
