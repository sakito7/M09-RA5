import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Polialfabetic
 */
public class Polialfabetic {
    public long  clauSecreta = 1234L;
    public String min = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] alfabet = min.toUpperCase().toCharArray();
    public Random rnd = null;
    public char[] alfabetPermutat = null;


    public void main(String[] args) {
        String[] msgs = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
        String[] msgsXifrats = new String[msgs.length];

        System.out.println("Xifratge\n---------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPolialfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }


        System.out.println("Desxifratge\n---------");
        for (int i = 0; i < msgsXifrats.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPolialfa(msgsXifrats[i]);
            System.out.printf("%-34s-> %s%n", msgsXifrats[i], msg);
        }

        System.out.println();
    }

    public void permutaAlfabet() {
        List<Character> alfabetPermutatFun = new ArrayList<>();

        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutatFun.add(alfabet[i]);
        }

        Collections.shuffle(alfabetPermutatFun, rnd);

        alfabetPermutat = new char[alfabet.length];

        for (int i = 0; i < alfabetPermutatFun.size(); i++) {
            alfabetPermutat[i] = alfabetPermutatFun.get(i);
        }        
    }
    
    public String desxiXiPolialfabet(String msg, boolean xifra) {
        StringBuilder res = new StringBuilder();


        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            permutaAlfabet();
            char[] principal = new char[alfabet.length];
            char[] resultat = new char[alfabet.length];

            if(xifra) {
                principal = alfabet;
                resultat = alfabetPermutat;
            } else {
                principal = alfabetPermutat;
                resultat = alfabet;
            }

            if(!Character.isLetter(c)) {
                res.append(c);
                continue;
            }
            
            for (int j = 0; j < principal.length; j++) {
                if(c == principal[j]) {
                    res.append(resultat[j]);
                } else if (Character.toUpperCase(c) == principal[j]) {
                    res.append(Character.toLowerCase(resultat[j]));
                }

            }
        }
        return res.toString();
    }
    
    public String xifraPolialfa(String msg) {
        return desxiXiPolialfabet(msg, true);
    }

    public String desxifraPolialfa(String msgXifrat) {
        return desxiXiPolialfabet(msgXifrat, false);
    }

    public void initRandom(long clauSecreta) {
        rnd = new Random(clauSecreta);
    }
}