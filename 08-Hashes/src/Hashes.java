import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Hashes
 */
public class Hashes {
    private static final int ITERATIONS = 10000; // Definir iteracions per v
    private static final int KEY_LENGTH_BITS = 128;
    private static final String CHARSET = "abcdefABCDEF1234567890!.";
    public int npass = 0; // numero de passwords provats

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("=============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
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

    // crear l'algoritme sha 
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512"); //get instansa of sha
        md.update(salt.getBytes(StandardCharsets.UTF_8)); // Afegim el salt 
        byte[] messageDigest = md.digest(pw.getBytes(StandardCharsets.UTF_8)); // Converteix a bytes

        return HexFormat.of().formatHex(messageDigest); // a binari hexadecimal
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        byte[] dk = derive(pw, salt, ITERATIONS); // Et retorna el resultat de PBKDF2 en un array de bytes
        return HexFormat.of().formatHex(dk); // A hexadecimal y retorna
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;

        for (int length = 6; length >= 1; length--) {
            char[] candidate = new char[length];
            java.util.Arrays.fill(candidate, CHARSET.charAt(0));

            while (true) {
                String pw = new String(candidate);
                npass++;
                if (comprovaHash(alg, pw, hash, salt)) {
                    return pw;
                }

                int pos = length - 1;
                while (pos >= 0 && candidate[pos] == CHARSET.charAt(CHARSET.length() - 1)) {
                    candidate[pos] = CHARSET.charAt(0);
                    pos--;
                }

                if (pos < 0) {
                    break;
                }
                candidate[pos] = CHARSET.charAt(CHARSET.indexOf(candidate[pos]) + 1);
            }
        }

        return "No trobat";
    }

    private boolean comprovaHash(String alg, String pw, String hash, String salt) throws Exception {
        if (alg.equalsIgnoreCase("SHA-512")) { // si es la contrassenya esperada retorna sino no
            return getSHA512AmbSalt(pw, salt).equals(hash);
        }

        if (alg.equalsIgnoreCase("PBKDF2")) { // lo mateix pero amb PBKDF2
            return getPBKDF2AmbSalt(pw, salt).equals(hash);
        }

        return false; // sino false
    }

    private byte[] derive(String password, String salt, int iterations) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iterations,
                    KEY_LENGTH_BITS); // Genera una instancia con los requisitos que tiene que tener la contrasenya
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded();

        } catch (Exception e) {
            throw new IllegalStateException("No s'ha pogut derivar el hash PBKDF2", e);
        }
    }

    public String getInterval(long t1, long t2) {
        long remaining = Math.max(0, t2 - t1);
        long days = remaining / 86_400_000;
        remaining %= 86_400_000;
        long hours = remaining / 3_600_000;
        remaining %= 3_600_000;
        long minutes = remaining / 60_000;
        remaining %= 60_000;
        long seconds = remaining / 1_000;
        long millis = remaining % 1_000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }
}
