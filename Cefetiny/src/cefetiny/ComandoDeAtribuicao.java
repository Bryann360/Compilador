package cefetiny;

import InterpretadorDoPrograma.*;
import Memoria.*;
import Execucao.*;
import Lista.*;

public class ComandoDeAtribuicao extends Comando{

    String variavel;
    
    private Lista<SimbolosCefetiny> expressao = new Lista();
    
    public ComandoDeAtribuicao(Lista<SimbolosCefetiny> exp, String var){
        this.expressao = exp;
        this.variavel = var;
    }

    @Override
    public void executa() {
        
        Memoria.add(new ManipuladorDaMemoria(variavel,SolverDeExpressoes.solve(expressao)));
    }
}