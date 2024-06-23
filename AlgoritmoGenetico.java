import java.util.Random;

/**
 * Esta clase implementa un algoritmo genético simple para evolucionar una cadena binaria.
 * El objetivo es alcanzar una cadena de 20 dígitos binarios con el valor máximo posible.
 */
public class AlgoritmoGenetico {
    // Constantes del algoritmo
    private static final int LONGITUD_CADENA = 20; // Longitud de la cadena binaria
    private static final int OBJETIVO = 40; // Valor máximo posible (todos 1's)
    private static final Random random = new Random(); // Generador de números aleatorios

    public static void main(String[] args) {
        // Generar la cadena inicial aleatoria
        int[] cadenaPrincipal = generarCadenaAleatoria();
        int iteraciones = 0;
        int aptitud = evaluarAptitud(cadenaPrincipal);

        System.out.println("Cadena inicial: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);

        // Bucle principal del algoritmo genético
        while (aptitud < OBJETIVO) {
            iteraciones++;
            
            // Generar una segunda cadena aleatoria para el cruce
            int[] segundaCadena = generarCadenaAleatoria();
            
            // Realizar el cruce entre la cadena principal y la segunda cadena
            int[] cadenaCruzada = cruzarCadenas(cadenaPrincipal, segundaCadena);
            
            // Evaluar la aptitud de la nueva cadena
            int nuevaAptitud = evaluarAptitud(cadenaCruzada);

            // Si la nueva cadena es más apta, reemplaza a la cadena principal
            if (nuevaAptitud > aptitud) {
                cadenaPrincipal = cadenaCruzada;
                aptitud = nuevaAptitud;
                System.out.println("Nueva cadena apta: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);
            }
        }

        // Mostrar resultados finales
        System.out.println("Solución óptima encontrada después de " + iteraciones + " iteraciones.");
        System.out.println("Cadena óptima: " + cadenaAString(cadenaPrincipal) + " Aptitud: " + aptitud);
    }

    /**
     * Genera una cadena binaria aleatoria de longitud LONGITUD_CADENA.
     * @return Un array de enteros representando la cadena binaria.
     */
    private static int[] generarCadenaAleatoria() {
        int[] cadena = new int[LONGITUD_CADENA];
        for (int i = 0; i < LONGITUD_CADENA; i++) {
            cadena[i] = random.nextInt(2); // Genera 0 o 1 aleatoriamente
        }
        return cadena;
    }

    /**
     * Evalúa la aptitud de una cadena binaria.
     * La aptitud se calcula como la suma de los dígitos multiplicados por 2.
     * @param cadena La cadena binaria a evaluar.
     * @return El valor de aptitud de la cadena.
     */
    private static int evaluarAptitud(int[] cadena) {
        int suma = 0;
        for (int gen : cadena) {
            suma += gen * 2;
        }
        return suma;
    }

    /**
     * Realiza el cruce entre dos cadenas binarias.
     * Si los dígitos en la misma posición son iguales, el resultado es 1; de lo contrario, es 0.
     * @param cadena1 Primera cadena para el cruce.
     * @param cadena2 Segunda cadena para el cruce.
     * @return Una nueva cadena resultante del cruce.
     */
    private static int[] cruzarCadenas(int[] cadena1, int[] cadena2) {
        int[] cadenaCruzada = new int[LONGITUD_CADENA];
        for (int i = 0; i < LONGITUD_CADENA; i++) {
            cadenaCruzada[i] = (cadena1[i] == cadena2[i]) ? 1 : 0;
        }
        return cadenaCruzada;
    }

    /**
     * Convierte un array de enteros que representa una cadena binaria a una cadena de texto.
     * @param cadena El array de enteros a convertir.
     * @return Una representación en cadena de texto de la cadena binaria.
     */
    private static String cadenaAString(int[] cadena) {
        StringBuilder sb = new StringBuilder();
        for (int gen : cadena) {
            sb.append(gen).append(" ");
        }
        return sb.toString().trim();
    }
}