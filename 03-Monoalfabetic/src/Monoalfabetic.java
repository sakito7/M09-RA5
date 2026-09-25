import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

public class Monoalfabetic {
    public String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] alfabetNormal = alfabet.toUpperCase().toCharArray();
    public char[] alfabetPermutat = permutaAlfabet(alfabetNormal);


    public void main(String[] args) {
        List<String> missatges = List.of("àrbitre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila");
        List<String> missatgesInvers = new ArrayList<>();

        System.out.println(alfabetNormal);
        System.out.println(alfabetPermutat);
        System.out.println();

        System.out.println("Xifratge:");
        for (int i = 0; i < missatges.size(); i++) {
            System.out.println(missatges.get(i));
            
            String missatgeXifrat = xifraMonoAlfa(missatges.get(i));
            missatgesInvers.add(missatgeXifrat);
            
            System.out.println(missatgeXifrat);

        }

        System.out.println();

        System.out.println("Desxifratge:");
        for (int i = 0; i < missatgesInvers.size(); i++) {
            System.out.print(missatgesInvers.get(i));
            
            String missatgeXifrat = desxifraMonoAlfa(missatges.get(i));
            
            System.out.println(missatgeXifrat);

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
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(!Character.isLetter(c)) res.append(c);
            for (int j = 0; j < alfabetNormal.length; j++) {
                if (Character.isLowerCase(c)) {
                    if (Character.toUpperCase(c) == alfabetNormal[j]) {
                        res.append(Character.toLowerCase(alfabetPermutat[j]));
                    }
                }
                
                if (c == alfabetNormal[j]) {
                    res.append(alfabetPermutat[j]);
                }
            }
        }

        return res.toString();
    }



    public String desxifraMonoAlfa(String cadena) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if(!Character.isLetter(c)) res.append(c);

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (Character.isLowerCase(c)) {
                    if (Character.toUpperCase(c) == alfabetPermutat[j]) {
                        res.append(Character.toLowerCase(alfabetNormal[j]));
                    }
                }

                if (c == alfabetNormal[j]) {
                    res.append(alfabetNormal[j]);                    
                }
            }
        }

        return res.toString();
    }
}