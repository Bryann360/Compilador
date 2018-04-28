package cefetiny;
import InterpretadorDoPrograma.*;
import Execucao.*;
import Lista.*;

public class ComandoWhile extends Comando{
        Lista<SimbolosCefetiny> expressao = new Lista<>() ;
    Lista<Comando> comandos ;
    Lista<SimbolosCefetiny> aux  = new Lista<>() ;
    
    public ComandoWhile(Lista<SimbolosCefetiny> expressao, Lista<Comando> comandos){
        this.expressao = expressao;
        this.comandos = comandos;
        aux =  expressao;
    }
    
    
    @Override
    public void executa() {
        
        aux = expressao;
        while(SolverDeExpressoes.solve(aux).getLexema().equals("true")){
            aux = expressao;
            for(int i=0;i<comandos.getTamanho();i++){
                comandos.get(i).executa();
            }
        }
    }
}
