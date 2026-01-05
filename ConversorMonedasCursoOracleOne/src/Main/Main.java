package Main;

import Modelos.ConversionRates;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(){
        Scanner teclado = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        List<String> historial = new ArrayList<>();

        while (true) {
            try {
                System.out.println("\n--- NUEVA CONSULTA ---");
                System.out.println("Ingrese el valor a convertir (0 para salir, -1 para historial): ");
                double valor = teclado.nextDouble();

                if (valor == 0) {
                    System.out.println("Cerrando programa...");
                    break;
                }

                //mostrar el historial
                if (valor == -1) {
                    System.out.println("--- HISTORIAL ---");
                    if(historial.isEmpty()){
                        System.out.println("Historial vacío.");
                    } else {
                        for (String registro : historial) {
                            System.out.println(registro);
                        }
                    }
                    continue; // Vuelve al inicio del while para no pedir divisas
                }

                System.out.println("Ingrese codigo de divisa inicial (ej: USD): ");
                String divisaTypeFrom = teclado.next().toUpperCase();

                System.out.println("Ingrese codigo de divisa a visualizar (ej: ARS): ");
                String divisaTypeGo = teclado.next().toUpperCase();

                String url = "https://v6.exchangerate-api.com/v6/20a9bbf5f7866569b0bc4943/latest/" + divisaTypeFrom;

                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                ConversionRates miRespuesta = gson.fromJson(response.body(), ConversionRates.class);

                if (miRespuesta.conversion_rates() == null) {
                    System.out.println("Error: La divisa de origen '" + divisaTypeFrom + "' no es válida o la API falló.");
                }
                else if (miRespuesta.conversion_rates().get(divisaTypeGo) == null) {
                    System.out.println("Error: La divisa destino '" + divisaTypeGo + "' no es válida.");
                }
                else {
                    Double tasa = miRespuesta.conversion_rates().get(divisaTypeGo);
                    Double resultado = tasa * valor;

                    // printf para que se vea en 2 decimales mas "estetico"
                    System.out.printf("El valor de: %.2f %s es: %.2f %s%n", valor, divisaTypeFrom, resultado, divisaTypeGo);

                    // 3. AGREGADO: Guardamos la operación en la lista
                    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String registro = String.format("[%s] %.2f %s -> %.2f %s", timestamp, valor, divisaTypeFrom, resultado, divisaTypeGo);
                    historial.add(registro);
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Error de conexión: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Monto ingresado inválido. Por favor ingrese un número.");
                teclado.nextLine();

            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado, intente nuevamente -> "+e.getMessage());
                teclado.nextLine();
            }
        }
    }
}