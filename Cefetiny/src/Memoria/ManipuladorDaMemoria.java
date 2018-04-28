package Memoria;
import InterpretadorDoPrograma.*;

public class ManipuladorDaMemoria {
    
    private String identificador = "";

    private SimbolosCefetiny simbolo;
    
    public ManipuladorDaMemoria(String idx, SimbolosCefetiny sim){
        this.identificador = idx;
        this.simbolo = sim;
    }
    
    public ManipuladorDaMemoria(String idx){
        this.identificador = idx;
    }
    
    public SimbolosCefetiny getSimbolo() {
        return simbolo;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setSimbolo(SimbolosCefetiny simbolo) {
        this.simbolo = simbolo;
    }
}
