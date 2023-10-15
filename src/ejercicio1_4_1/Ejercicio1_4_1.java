package ejercicio1_4_1;

import ejercicio1_4_1.Interfaces.ValidacionRegistroContacto;
import ejercicio1_4_1.modeloDatos.Contacto;
import java.time.LocalDate;

public class Ejercicio1_4_1 {

   public static void main(String[] args) {
        // Crear un array de 3 objetos Contacto con datos incorrectos
        Contacto[] contactos = new Contacto[4];
        contactos[0] = new Contacto("Ejemplo1", "correo_ejemplo1.com", "123456789", LocalDate.of(2005, 5, 10));
        contactos[1] = new Contacto("Ejemplo2", "correo_ejemplo2@gmail.com", "987654321", LocalDate.of(2003, 2, 20));
        contactos[2] = new Contacto("Ejemplo4", "correo_ejemplo4@gmail.com", "+349876543210", LocalDate.of(2010, 2, 20));
        contactos[3] = new Contacto("Ejemplo3", "correo_ejemplo3@hotmail.com", "+349876543210", LocalDate.of(2000, 12, 5));

        // Crear un validador usando la interfaz ValidacionRegistroContacto con lambdas
        ValidacionRegistroContacto validador = contacto -> {
            if (contacto.getEmail() != null && contacto.getEmail().contains("@") &&
                contacto.getNacimiento() != null) {
                LocalDate fechaActual = LocalDate.now();
                LocalDate fechaMayorDeEdad = fechaActual.minusYears(18);

                if (contacto.getNacimiento().isBefore(fechaMayorDeEdad) &&
                    contacto.getTelefono() != null && contacto.getTelefono().startsWith("+34")) {
                    return ValidacionRegistroContacto.ResultadoValidacion.VALIDO;
                }

                if (!contacto.getEmail().contains("@")) {
                    return ValidacionRegistroContacto.ResultadoValidacion.EMAIL_INVALIDO;
                }
                if (!contacto.getNacimiento().isBefore(fechaMayorDeEdad)) {
                    return ValidacionRegistroContacto.ResultadoValidacion.EDAD_INVALIDA;
                }
                if (!contacto.getTelefono().startsWith("+34")) {
                    return ValidacionRegistroContacto.ResultadoValidacion.TELEFONO_INVALIDO;
                }
            }
            return ValidacionRegistroContacto.ResultadoValidacion.EMAIL_INVALIDO;
        };

        // Validar los objetos Contacto usando el validador con lambdas
        for (Contacto contacto : contactos) {
            ValidacionRegistroContacto.ResultadoValidacion resultado = validador.apply(contacto);

            if (resultado == ValidacionRegistroContacto.ResultadoValidacion.VALIDO) {
                System.out.println(contacto.getNombre() + " es válido.");
            } else {
                System.err.println("Error en " + contacto.getNombre() + ": " + resultado);
            }
        }
    }
}
