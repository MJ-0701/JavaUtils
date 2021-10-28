package Main.security;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.logging.Logger;


public class RSA {

    private KeyPairGenerator generator;
    private KeyFactory keyFactory;
    private KeyPair keyPair;
    private Cipher cipher;

    // 1024비트 키 쌍을 생성
    public RSA(){
        try{
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            keyFactory = KeyFactory.getInstance("RSA");
            cipher = Cipher.getInstance("RSA");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public HashMap<String, Object> createRSA(){
        HashMap<String, Object> rsa = new HashMap<String, Object>();
        try{
            keyPair = generator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey,RSAPublicKeySpec.class);
            String modules = publicKeySpec.getModulus().toString(16);
            String exponent = publicKeySpec.getPublicExponent().toString(16);

            rsa.put("privatekey", privateKey);
            rsa.put("modules", modules);
            rsa.put("exponent", exponent);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rsa;
    }

    //Key 로 RSA 복호화를 수행.
    public String getDecryptText(PrivateKey privateKey, String encryptText) throws Exception{
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(hexToByteArray(encryptText));

        return new String(decryptedBytes, "UTF-8");
    }

    //Key 로 RSA 암호화를 수행.
    public String setEncryptText(PublicKey publicKey, String encryptText) throws  Exception{
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(encryptText.getBytes());
        return new String(encryptedBytes, "UTF-8");
    }

    private byte[] hexToByteArray(String hex){
        if(hex == null || hex.length() %2 != 0){
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < hex.length(); i += 2){
            byte value = (byte)Integer.parseInt(hex.substring(i, i+2), 16);
            bytes[(int) Math.floor(i / 2)] = value;

        }
        return bytes;
    }

    public static void main(String[] args){

    }




}
