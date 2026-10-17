package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

/**
 * RotX
 */
public class XifradorRotX implements Xifrador {
    public String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public char[] majuscules = alfabet.toUpperCase().toCharArray();
    public char[] minuscules = alfabet.toCharArray();
    
    public String xifraRotX(String cadena, int desplacament) {
        StringBuffer resultat = new StringBuffer();
        int posicio;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    char cMin = minuscules[j];
                    if (cMin == c) {
                        posicio = (j+desplacament)%(minuscules.length);
                        resultat.append(minuscules[posicio]);
                        break;
                    }
                }
            } else if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    char cMay = majuscules[j];
                    if (cMay == c) {
                        posicio = (j+desplacament)%(majuscules.length);
                        resultat.append(majuscules[posicio]);
                        break;
                    }   
                }
            } else resultat.append(c);
        }

        return resultat.toString();
    }

    public String desxifraRotX(String cadena, int desplacament) {
        StringBuilder resultat = new StringBuilder();
        int posicio;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    char cMin = minuscules[j];
                    if (cMin == c) {
                        posicio = (j - desplacament + minuscules.length) % minuscules.length;
                        resultat.append(minuscules[posicio]);
                        break;
                    }
                }
            } else if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    char cMay = majuscules[j];
                    if (cMay == c) {
                        posicio = (j - desplacament + majuscules.length) % majuscules.length;
                        resultat.append(majuscules[posicio]);
                        break;
                    }   
                }
            } else resultat.append(c);
        }

        return resultat.toString();
    }

    public void forcaBrutaRotX(String misatge) {
        System.out.println("Missatge xifrat: " + misatge);
        System.out.println("----------------");
        for (int i = 0; i < majuscules.length; i++) {
            System.out.println("(" + i + ")->" + desxifraRotX(misatge, i));
        }

    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament = validaClau(clau);
        String xifrat = xifraRotX(msg, desplacament);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament = validaClau(clau);
        String text = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraRotX(text, desplacament);
    }

    private int validaClau(String clau) throws ClauNoSuportada {
    try {
        int n = Integer.parseInt(clau);
        if (n < 0 || n > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        return n;
    } catch (NumberFormatException e) {
        throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
    }
}
}