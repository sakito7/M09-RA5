import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

public class Monoalfabetic {
    public String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] alfabetNormal = alfabet.toUpperCase().toCharArray();
    public char[] alfabetPermutat = permutaAlfabet(alfabetNormal);


    public void main(String[] args) {
        List<String> missatges = List.of("Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila");
        List<String> missatgesInvers = new ArrayList<>();

        System.out.println(alfabetNormal);
        System.out.println(alfabetPermutat);
        System.out.println();

        System.out.println("Xifratge:");
        for (int i = 0; i < missatges.size(); i++) {            
            String missatgeXifrat = xifraMonoAlfa(missatges.get(i));
            missatgesInvers.add(missatgeXifrat);
            System.out.printf("%-35s -> %s%n", missatges.get(i), missatgeXifrat);

        }

        System.out.println();

        System.out.println("Desxifratge:");
        for (int i = 0; i < missatgesInvers.size(); i++) {            
            String missatgeXifrat = desxifraMonoAlfa(missatgesInvers.get(i));
            System.out.printf("%-35s -> %s%n", missatgesInvers.get(i), missatgeXifrat);

        }
    }

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
}