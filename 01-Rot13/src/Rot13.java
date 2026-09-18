public class Rot13 {
    String alfabet = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    char[] majuscules = alfabet.toUpperCase().toCharArray();
    char[] minuscules = alfabet.toLowerCase().toCharArray();
    
    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu que és?"};
        String msgXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n---------");

        for (int i = 0; i < msgs.length; i++) {
            msgXifrats[i] = xifraRot13(msgs[i])
            System.out.printf("%-23s => %s%n", msgs[i], msgXifrats[i]);
        }
        
        System.out.println("\nDesxifrat\n---------");
        for (String msg : msgXifrats) {
            System.out.printf("%-23s => %s%n", msgs, desxifraRot13);
        }


    }

    public static void xifraRot13(String cadena) {
        String resultat = "";
        int posicio;

        for (int i = 0; i < cadena.length; i++) {
            char c = cadena[i];
            if(c == '?') {
                resultat = resultat + c;
                continue;
            } else {
                if(Character.isLowerCase(c)) {
                    for (int i = 0; i < minuscules.length; i++) {
                        char cMin = minuscules[i];
                        if (cMin = c) {
                            posicio = (i+13)%2
                            for ()
                            resultat = resultat + 
                        }
                        
                    }
                } else {
                    for (int i = 0; i < majuscules.length; i++) {
                        char cMay = majuscules[i];
                        if (cMay = c) {
                            posicio = (i+13)%2
                        }   
                    }
                }
            }
        }
    }

    public static void desxifraRot13(String cadena) {
        
    }
}