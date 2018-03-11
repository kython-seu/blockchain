package blockchain;

import java.security.*;
import java.util.ArrayList;

public class Transaction {

    public String transactionId; // 这个也是交易的哈希值
    public PublicKey sender; // 发送方地址/公钥
    public PublicKey reciepient; // 接受方地址/公钥
    public float value;
    public byte[] signature; // 用来防止他人盗用我们钱包里的资金

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0; // 对已生成交易个数的粗略计算

    // 构造方法：
    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.value = value;
        this.inputs = inputs;
    }

    // 用来计算交易的哈希值（可作为交易的 id）
    private String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                        StringUtil.getStringFromKey(reciepient) +
                        Float.toString(value) + sequence
        );
    }



    //对所有我们不想被篡改的数据进行签名
    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }
    //验证我们已签名的数据
    public boolean verifiySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }


}

