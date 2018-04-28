package InterpretadorDoPrograma;

public class SimbolosCefetiny {

    private int token;
    private String lexema;
    private int tipo;
    private String valor;

    public SimbolosCefetiny() {
        this.token = -1;
        this.lexema = "";
        this.tipo = -1;
        this.valor = "";
    }

    public SimbolosCefetiny(int token, String lexema) {
        this.token = token;
        this.lexema = lexema;
        this.tipo = -1;
        this.valor = "";
    }

    public int getToken() {
        return this.token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getLexema() {
        return this.lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
