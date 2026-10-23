package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        byte[] msgBytes = msg.getBytes();
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] encryptedMsgBytes = encryptCipher.doFinal(msgBytes);
        return encryptedMsgBytes;
    }

    public String desxifraRSA(byte[] missatgeXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] decryptedMsgBytes = encryptCipher.doFinal(missatgeXifrat);
        //String decryptedMsgString = Arrays.toString(decryptedMsgBytes);
        String decryptedMsgString = new String(decryptedMsgBytes);
        return decryptedMsgString;
    }
}
