import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Hashes
 */
public class Hashes {
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
                System.out.printf("=============================\n");
                System.out.printf("Algorisme: %s\n", algorismes[i]);
                System.out.printf("Hash: %s\n",aHashes[i]);
                System.out.printf("--------------------------\n");
                System.out.printf("-- Inici de força bruta ---\n");

                long t1 = System.currentTimeMillis();
                pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
                long t2 = System.currentTimeMillis();

                System.out.printf("Pass : %s\n", pwTrobat);
                System.out.printf("Provats: %d\n", h.npass);
                System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
                System.out.printf("--------------------------\n\n");
        }
    }


    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] messageDigest = md.digest(pw.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 128) {
                hashtext = "0" + hashtext;
        }

        return hashtext;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        byte[] saltBytes = salt.getBytes();
        byte[] dk = derive(pw, salt, ITERATIONS);
        Base64.Encoder b64 = Base64.getEncoder().withoutPadding();
        return ITERATIONS + ":" + b64.encodeToString(salt) + ":" + b64.encodeToString(dk);
    }

    public String forcaBruta(String alg, String hash, String salt) {

    }

    public String getInterval(long t1 pw, long t2) {

    }
}
