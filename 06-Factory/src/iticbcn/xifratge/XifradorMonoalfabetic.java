package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    public String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] alfabetNormal = alfabet.toUpperCase().toCharArray();
    public char[] alfabetPermutat = permutaAlfabet(alfabetNormal);


    public char[] permutaAlfabet(char[] alfabetNormal) {
        List<Character> alfabetPermutatFun = new ArrayList<>();

        for (int i = 0; i < alfabetNormal.length; i++) {
            alfabetPermutatFun.add(alfabetNormal[i]);
        }

        Collections.shuffle(alfabetPermutatFun);

        char[] alfabetPermutatReturn = new char[alfabetNormal.length];

        for (int i = 0; i < alfabetPermutatFun.size(); i++) {
            alfabetPermutatReturn[i] = alfabetPermutatFun.get(i);
        }
        
        return alfabetPermutatReturn;
    }


    public String xifraMonoAlfa(String cadena) {
        return xiDesxiMonoAlfa(cadena, alfabetNormal, alfabetPermutat);
    }    
    
    public String desxifraMonoAlfa(String cadena) {
        return xiDesxiMonoAlfa(cadena, alfabetPermutat, alfabetNormal);
    }



    public String xiDesxiMonoAlfa(String cadena, char[] principal, char[] resultat) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i); //ñ

            if(!Character.isLetter(c)) res.append(c);
            for (int j = 0; j < principal.length; j++) {
                if (Character.isLowerCase(c)) {
                    if (Character.toUpperCase(c) == principal[j]) {
                        res.append(Character.toLowerCase(resultat[j]));
                    }
                }
                
                if (c == principal[j]) {
                    res.append(resultat[j]);
                }
            }
        }

        return res.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }
        String xifrat = xifraMonoAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraMonoAlfa(text);
    }
}