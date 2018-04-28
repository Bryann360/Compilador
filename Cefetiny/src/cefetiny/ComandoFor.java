package cefetiny;
import InterpretadorDoPrograma.*;
import Execucao.*;
import Lista.*;

public class ComandoFor extends Comando{
        
    Lista<SimbolosCefetiny> condicao = new Lista();
    
    Lista<Comando> comandos = new Lista();
    
    Comando inc,atrib;
    
    public ComandoFor(Lista<SimbolosCefetiny> condicao,Lista<Comando> comandos,Comando inc,Comando atrib){
        this.condicao = condicao;
        this.comandos = comandos;
        this.inc = inc;
        this.atrib = atrib;
    }

    @Override
    public void executa() {
        
        atrib.executa();
        
        while(SolverDeExpressoes.solve(this.condicao).getLexema().equals("true")){
            
            for(int i=0;i<comandos.getTamanho();i++){
                comandos.get(i).executa();
            }
            
            inc.executa();
        }
    }
    
}
