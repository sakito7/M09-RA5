import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec; 


public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Nigges";
    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                    + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }


    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
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
 
	public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
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
	private static byte[] generaIv() {
		byte[] nouIv = new byte[MIDA_IV];
		SecureRandom random = new SecureRandom();
		random.nextBytes(nouIv);
		return nouIv;
	}

    private static SecretKeySpec generaHash(String clau) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
		byte[] hash = digest.digest(clau.getBytes(StandardCharsets.UTF_8)); // 32 bytes = 256 bits
		return new SecretKeySpec(hash, ALGORISME_XIFRAT);
	}
 
	// Extreu la part de l'IV de dins del missatge xifrat
	private static byte[] extreureIv(byte[] bIvIMsgXifrat) {
		return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
	}
 
	// Extreu la part del missatge xifrat del array de bytes amb l'IV
	private static byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
		return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
	}
}