package iticbcn.xifratge;

import java.nio.file.Files;
import java.nio.file.Path;
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
        Path tempFile = Files.createTempFile("temp", "txt");
        Files.writeString(tempFile, "some secret message");
        byte[] fileBytes = Files.readAllBytes(tempFile);
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] encryptedFileBytes = encryptCipher.doFinal(fileBytes);
        return encryptedFileBytes;
    }

    public String desxifraRSA(byte[] missatgeXifrat, PrivateKey clauPrivada) {

    }
}
