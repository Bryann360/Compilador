package cefetiny;
import InterpretadorDoPrograma.*;
import Execucao.*;
import Lista.*;

public class ComandoIf extends Comando{
        Lista<SimbolosCefetiny> expressao = new Lista();
    Lista<Comando> comandos = new Lista();
    
    public ComandoIf(Lista<SimbolosCefetiny> expressao, Lista<Comando> comandos){
        this.expressao = expressao;
        this.comandos = comandos;
    }

    @Override
    public void executa() {
        if(SolverDeExpressoes.solve(expressao).getLexema().equals("true")){
            int aux=0;
            for(int i=0;i<comandos.getTamanho();i++){
                if(comandos.get(i) instanceof ComandoElse || comandos.get(i) instanceof ComandoEndIf) aux = 1;
                else if(aux==0)comandos.get(i).executa();
            }
        }
        else{
            int aux=1;
            for(int i=0;i<comandos.getTamanho();i++){
                if(comandos.get(i) instanceof ComandoElse) aux = 0;
                else if(aux==0)comandos.get(i).executa();
            }
        }
    }

}