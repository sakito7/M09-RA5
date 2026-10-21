package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Polialfabetic
 */
public class XifradorPolialfabetic implements Xifrador {
    public long  clauSecreta = 1234L;
    public String min = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] alfabet = min.toUpperCase().toCharArray();
    public Random rnd = null;
    public char[] alfabetPermutat = null;

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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long madremia = validaClau(clau, "La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        initRandom(madremia);
        String xifrat = xifraPolialfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long madremia = validaClau(clau, "La clau de Polialfabètic ha de ser un String convertible a long");
        initRandom(madremia);
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraPolialfa(text);
    }

    private long validaClau(String clau, String missatge) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(missatge);
        }
    }
}