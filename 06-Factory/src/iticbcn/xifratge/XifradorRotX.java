package iticbcn.xifratge;

/**
 * RotX
 */
public class XifradorRotX implements Xifrador {
    public static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static char[] majuscules = alfabet.toUpperCase().toCharArray();
    public static char[] minuscules = alfabet.toCharArray();
    
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
        StringBuffer resultat = new StringBuffer();
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
}
