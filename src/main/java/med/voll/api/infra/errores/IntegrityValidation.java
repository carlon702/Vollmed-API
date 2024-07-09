package med.voll.api.infra.errores;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s) {
    super(s);
    }
}
