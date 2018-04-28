package cefetiny;
import InterpretadorDoPrograma.*;
import Memoria.*;
import Execucao.*;

public class ComandoReadInt extends Comando{
        
    private String identifier;
    private SimbolosCefetiny sim;
    
    public ComandoReadInt(String id){
        identifier = id;
    }
    
    @Override
    public void executa(){
        System.out.println();
        int in = EntradaDeTexto.readInt();
        sim = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(in));
        sim.setValor(String.valueOf(in));
        sim.setTipo(ContantesDaLinguagem.T_INTEGER);

        Memoria.add(new ManipuladorDaMemoria(identifier,sim));
    }
}
