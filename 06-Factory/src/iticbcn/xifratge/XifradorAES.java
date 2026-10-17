package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec; 


public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Nigges";
    

    public byte[] xifraAES(String msg, String clau) throws Exception {
		// Obtenir els bytes de l'String
		byte[] bMsg = msg.getBytes(StandardCharsets.UTF_8);
 
		// Genera IvParameterSpec
		byte[] iv = generaIv();
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
 
		// Genera hash
		SecretKeySpec key = generaHash(clau);
 
		// Encrypt.
		Cipher cipher = Cipher.getInstance(FORMAT_AES);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] msgXifrat = cipher.doFinal(bMsg);
 
		// Combinar IV i part xifrada.
		byte[] ivMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
		System.arraycopy(iv, 0, ivMsgXifrat, 0, MIDA_IV);
		System.arraycopy(msgXifrat, 0, ivMsgXifrat, MIDA_IV, msgXifrat.length);
 
		// return iv+msgxifrat
		return ivMsgXifrat;
	}
 
	public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
		// Extreure l'IV.
		byte[] ivExtret = extreureIv(bIvIMsgXifrat);
		IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
 
		// Extreure la part xifrada.
		byte[] msgXifrat = getBytesXifrats(bIvIMsgXifrat);
 
		// Fer hash de la clau
		SecretKeySpec key = generaHash(clau);
 
		// Desxifrar.
		Cipher cipher = Cipher.getInstance(FORMAT_AES);
		cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
		byte[] bDesxifrat = cipher.doFinal(msgXifrat);
 
		// return String desxifrat
		return new String(bDesxifrat, StandardCharsets.UTF_8);
	}
 
	// Genera amb un SecureRandom el contingut en bytes de l'IV i el retorna
	private byte[] generaIv() {
		byte[] nouIv = new byte[MIDA_IV];
		SecureRandom random = new SecureRandom();
		random.nextBytes(nouIv);
		return nouIv;
	}

    private SecretKeySpec generaHash(String clau) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
		byte[] hash = digest.digest(clau.getBytes(StandardCharsets.UTF_8)); // 32 bytes = 256 bits
		return new SecretKeySpec(hash, ALGORISME_XIFRAT);
	}
 
	// Extreu la part de l'IV de dins del missatge xifrat
	private byte[] extreureIv(byte[] bIvIMsgXifrat) {
		return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
	}
 
	// Extreu la part del missatge xifrat del array de bytes amb l'IV
	private byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
		return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
	}

	@Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (msg == null || clau == null) {
            throw new ClauNoSuportada("Missatge o clau nuls");
        }
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en xifrar amb AES: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (xifrat == null || clau == null) {
            throw new ClauNoSuportada("Text xifrat o clau nuls");
        }
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en desxifrar amb AES: " + e.getMessage());
        }
    }
}