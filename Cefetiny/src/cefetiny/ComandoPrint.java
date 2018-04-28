package cefetiny;
import InterpretadorDoPrograma.*;
import Execucao.*;
import Lista.*;

public class ComandoPrint extends Comando{
    
    Lista<SimbolosCefetiny> expressao;
    
    public ComandoPrint(Lista<SimbolosCefetiny> exp){
        expressao = exp;
    }
    
    @Override
    public void executa(){
        
        System.out.print(SolverDeExpressoes.solve(expressao).getLexema());
        
    }
}