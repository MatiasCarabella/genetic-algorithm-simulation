import java.util.Random;

public class AlgoritmoGenetico2 {
    private static final int LONGITUD_CADENA = 20;
    private static final int OBJETIVO = 40;
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] cadenaPrincipal = generarCadenaAleatoria();
        int iteraciones = 0;
        int aptitud = evaluarAptitud(cadenaPrincipal);

        System.out.println("Cadena inicial: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);

        while (aptitud < OBJETIVO) {
            iteraciones++;
            int[] segundaCadena = generarCadenaAleatoria();
            int[] cadenaCruzada = cruzarCadenas(cadenaPrincipal, segundaCadena);
            int nuevaAptitud = evaluarAptitud(cadenaCruzada);

            if (nuevaAptitud > aptitud) {
                cadenaPrincipal = cadenaCruzada;
                aptitud = nuevaAptitud;
                System.out.println("Nueva cadena apta: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);
            }
        }

        System.out.println("Solución óptima encontrada después de " + iteraciones + " iteraciones.");
        System.out.println("Cadena óptima: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);
    }

    private static int[] generarCadenaAleatoria() {
        int[] cadena = new int[LONGITUD_CADENA];
        for (int i = 0; i < LONGITUD_CADENA; i++) {
            cadena[i] = random.nextInt(2);
        }
        return cadena;
    }

    private static int evaluarAptitud(int[] cadena) {
        int suma = 0;
        for (int gen : cadena) {
            suma += gen * 2;
        }
        return suma;
    }

    private static int[] cruzarCadenas(int[] cadena1, int[] cadena2) {
        int[] cadenaCruzada = new int[LONGITUD_CADENA];
        for (int i = 0; i < LONGITUD_CADENA; i++) {
            cadenaCruzada[i] = (cadena1[i] == cadena2[i]) ? 1 : 0;
        }
        return cadenaCruzada;
    }

    private static String cadenaAString(int[] cadena) {
        StringBuilder sb = new StringBuilder();
        for (int gen : cadena) {
            sb.append(gen).append(" ");
        }
        return sb.toString().trim();
    }
}