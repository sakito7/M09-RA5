public class Rot13 {
    public static String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static char[] majuscules = alfabet.toUpperCase().toCharArray();
    public static char[] minuscules = alfabet.toCharArray();
    
    public static void main(String[] args) {
        /*String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu que és?"};
        String msgXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n---------");

        for (int i = 0; i < msgs.length; i++) {
            msgXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgXifrats[i]);
        }
        
        System.out.println("\nDesxifrat\n---------");
        for (String msg : msgXifrats) {
            System.out.printf("%-23s => %s%n", msgs, desxifraRot13(msg));
        }
            
        */

        System.out.println(desxifraRot13("Hola, Mr. calçot"));


    }

    public static String xifraRot13(String cadena) {
        String resultat = "";
        int posicio;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    char cMin = minuscules[j];
                    if (cMin == c) {
                        posicio = (j+13)%(minuscules.length);
                        resultat = resultat + minuscules[posicio];
                        break;
                    }
                }
            } else if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    char cMay = majuscules[j];
                    if (cMay == c) {
                        posicio = (j+13)%(majuscules.length);
                        resultat = resultat + majuscules[posicio];
                        break;
                    }   
                }
            } else resultat = resultat + c;
        }

        return resultat;
    }

    public static String desxifraRot13(String cadena) {
        String resultat = "";
        int posicio;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if(Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    char cMin = minuscules[j];
                    if (cMin == c) {
                        posicio = (j-13)%(minuscules.length);
                        resultat = resultat + minuscules[posicio];
                        break;
                    }
                }
            } else if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    char cMay = majuscules[j];
                    if (cMay == c) {
                        posicio = (j-13)%(majuscules.length);
                        resultat = resultat + majuscules[posicio];
                        break;
                    }   
                }
            } else resultat = resultat + c;
        }

        return resultat;
    }
}