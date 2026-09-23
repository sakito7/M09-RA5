/**
 * RotX
 */
public class RotX {
    public static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static char[] majuscules = alfabet.toUpperCase().toCharArray();
    public static char[] minuscules = alfabet.toCharArray();
    
    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu que és?"};
        String msgXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n---------");
        int index = 0;

        for (int i = 0; i < msgs.length; i++) {
            msgXifrats[i] = xifraRot13(msgs[i], index);
            System.out.printf("%-28s => %s%n", "(" + index + ") -" + msgs[i], msgXifrats[i]);
            index+=2;
        }

        index = 0;
        
        System.out.println("\nDesxifrat\n---------");
        for (String msg : msgXifrats) {
            System.out.printf("%-28s => %s%n", "(" + index + ") -" + msg, desxifraRot13(msg, index));
            index+=2;
        }

    }

    public static String xifraRot13(String cadena, int desplacament) {
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

    public static String desxifraRot13(String cadena, int desplacament) {
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
}
