package blockchain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

    public PrivateKey privateKey;
    public PublicKey publicKey;

    public Wallet(){
        generateKeyPair();
    }
    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // 初始化 KeyGenerator 并且生成一对 KeyPair
            keyGen.initialize(ecSpec, random);   //256 字节大小是可接受的安全等级
            KeyPair keyPair = keyGen.generateKeyPair();
            // 从 KeyPair中获取公钥和私钥
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


}
