package main;

import blockchain.Block;
import blockchain.StringUtil;
import blockchain.Transaction;
import blockchain.Wallet;

import java.security.Security;
import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        //设置 Bouncey castle 作为 Security Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //创建新的钱包
        walletA = new Wallet();//创建一对公钥和私钥
        walletB = new Wallet();
        //测试公钥和私钥
        System.out.println("Private and public keys:");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
        //生成从 WalletA 到 walletB 的测试交易
        Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
        transaction.generateSignature(walletA.privateKey);
        //验证签名是否起作用并结合公钥验证
        System.out.println("Is signature verified");
        System.out.println(transaction.verifiySignature());

    }


}
