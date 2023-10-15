package ejercicio1_4_1.Interfaces;

import ejercicio1_4_1.modeloDatos.Contacto;
import java.time.LocalDate;
import java.util.function.Function;

@FunctionalInterface
public interface ValidacionRegistroContacto extends Function<Contacto, ValidacionRegistroContacto.ResultadoValidacion> {
    enum ResultadoValidacion {
        VALIDO,
        EMAIL_INVALIDO,
        EDAD_INVALIDA,
        TELEFONO_INVALIDO
    }

    @Override
    ResultadoValidacion apply(Contacto contacto);

    default ResultadoValidacion validarEmail(String email) {
        return email != null && email.contains("@") ? ResultadoValidacion.VALIDO : ResultadoValidacion.EMAIL_INVALIDO;
    }

    default ResultadoValidacion validarEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return ResultadoValidacion.EDAD_INVALIDA;
        }
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaMayorDeEdad = fechaActual.minusYears(18);
        return fechaNacimiento.isBefore(fechaMayorDeEdad) ? ResultadoValidacion.VALIDO : ResultadoValidacion.EDAD_INVALIDA;
    }

    default ResultadoValidacion validarTelefono(String telefono) {
        return telefono != null && telefono.startsWith("+34") ? ResultadoValidacion.VALIDO : ResultadoValidacion.TELEFONO_INVALIDO;
    }
}