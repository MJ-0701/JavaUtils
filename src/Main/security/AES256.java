package Main.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class AES256 {
    private String iv;
    private Key keySpec;

    /**
     * 16자리의 키값을 입력하여 객체를 생성.
     @param key 암/복호화를 위한 키값.
     @param throws UnsupportedEncodingException 키값의 길이다 16이하일때 발생.
    */
    private final static String key = "비밀키 입력하는곳.";

    public AES256() throws UnsupportedEncodingException {
        this.iv = key.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes(StandardCharsets.UTF_8);
        int len = b.length;
        if(len > keyBytes.length){
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;

    }


}
