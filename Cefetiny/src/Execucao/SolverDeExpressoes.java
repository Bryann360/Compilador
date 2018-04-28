package Execucao;
import InterpretadorDoPrograma.*;
import Lista.*;
import Memoria.*;
import Excecoes.*;

public class SolverDeExpressoes  {
    public static ManipuladorDaLista pilha = new ManipuladorDaLista();
    
    private static Lista<SimbolosCefetiny> exp =  new Lista<SimbolosCefetiny>();
    
    public static SimbolosCefetiny solve(Lista<SimbolosCefetiny> expressao){

        exp = expressao;
        exp =toPosfix(exp);
        
        ManipuladorDaLista<SimbolosCefetiny> aux = new ManipuladorDaLista<>();
        
        for(int i=0;i<exp.getTamanho();i++){
            if(exp.get(i).getToken()==ContantesDaLinguagem.T_IDENTIFIER){
                String a = exp.get(i).getLexema();
                exp.set(i, Memoria.get(a));
            }
        }
        
        for(int i=0;i<exp.getTamanho();i++){
            
            if(exp.get(i).getToken()== ContantesDaLinguagem.T_CONSTANT) aux.push(exp.get(i));
            
            else{
                SimbolosCefetiny op1,op2, res;
                switch(exp.get(i).getLexema()){
                    
                    case("+"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_STRING)){
                            if(op1.getTipo()==ContantesDaLinguagem.T_INTEGER){
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,(String.valueOf(Integer.parseInt(op1.getLexema()) + Integer.parseInt(op2.getLexema()))));
                                res.setTipo(ContantesDaLinguagem.T_INTEGER);
                            }
                            else if(op1.getTipo()==ContantesDaLinguagem.T_DOUBLE){
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,(String.valueOf(Double.parseDouble(op1.getLexema()) + Double.parseDouble(op2.getLexema()))));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);      
                            }
                            else{
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,(op2.getLexema()+op1.getLexema()));
                                res.setTipo(ContantesDaLinguagem.T_STRING);
                            }
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER && op2.getTipo() == ContantesDaLinguagem.T_DOUBLE) || (op1.getTipo() == ContantesDaLinguagem.T_DOUBLE && op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) + Double.parseDouble(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else if((op1.getTipo() != op2.getTipo()) && (op1.getTipo() == ContantesDaLinguagem.T_STRING || op2.getTipo() == ContantesDaLinguagem.T_STRING)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((op2.getLexema()) + (op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_STRING);
                            aux.push(res);
                        }
                        
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"+\" ");
                        break;
                        
                    case("-"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE)){
                            if(op1.getTipo()==ContantesDaLinguagem.T_DOUBLE){
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,(String.valueOf(Double.parseDouble(op2.getLexema()) - Double.parseDouble(op1.getLexema()))));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            }
                            else{
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,(String.valueOf(Integer.parseInt(op2.getLexema()) - Integer.parseInt(op1.getLexema()))));
                                res.setTipo(ContantesDaLinguagem.T_INTEGER);
                            }
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) - Double.parseDouble(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"-\" ");
                        break;
                    
                    case("*"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE)){
                            if(op1.getTipo()==ContantesDaLinguagem.T_DOUBLE){
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) * Double.parseDouble(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            }
                            else{
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Integer.parseInt(op2.getLexema()) * Integer.parseInt(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            }
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) * Double.parseDouble(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"*\" ");
                        break;
                        
                    case("div"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Integer.parseInt(op2.getLexema()) / Integer.parseInt(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_INTEGER);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"div\" ");
                        break;
                        
                    case("^"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE) && (op2.getTipo() == ContantesDaLinguagem.T_INTEGER || op2.getTipo() == ContantesDaLinguagem.T_DOUBLE)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Math.pow(Double.parseDouble(op2.getLexema()),Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"^\" ");
                        break;
                        
                    case("/"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE)){
                            
                            if(op1.getTipo()==ContantesDaLinguagem.T_DOUBLE){
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) / Double.parseDouble(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            }
                            else{
                                res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Integer.parseInt(op2.getLexema()) / Integer.parseInt(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_INTEGER);
                            }
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) / Double.parseDouble(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"/\" ");
                        break;
                        
                    case("sqrt"):
                        op1=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE){
                            res= new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Math.sqrt(Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"sqrt\" ");
                        break;
                        
                    case("mod"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo()== op2.getTipo() && (op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE)){
                            
                            if(op1.getTipo()==ContantesDaLinguagem.T_DOUBLE){
                                res= new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) % Double.parseDouble(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            }
                            else{
                                res= new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Integer.parseInt(op2.getLexema()) % Integer.parseInt(op1.getLexema())));
                                res.setTipo(ContantesDaLinguagem.T_INTEGER);
                            }
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_INTEGER || op1.getTipo() == ContantesDaLinguagem.T_DOUBLE) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res= new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(Double.parseDouble(op2.getLexema()) % Double.parseDouble(op1.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_DOUBLE);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operandos invalidos para o operador \"mod\" ");
                        break;
                        
                    case("not"):
                        op1=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_BOOLEAN){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(!(Boolean.parseBoolean(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"not\" ");
                        break;
                        
                    case("or"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_BOOLEAN && op2.getTipo() == ContantesDaLinguagem.T_BOOLEAN){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Boolean.parseBoolean(op1.getLexema())) || (Boolean.parseBoolean(op2.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"or\" ");
                        break;
                        
                    case("and"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_BOOLEAN && op2.getTipo() == ContantesDaLinguagem.T_BOOLEAN){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Boolean.parseBoolean(op1.getLexema())) && (Boolean.parseBoolean(op2.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"and\" ");
                        break;
                        
                    case("="):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_BOOLEAN && op2.getTipo() == ContantesDaLinguagem.T_BOOLEAN){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Boolean.parseBoolean(op1.getLexema())) == (Boolean.parseBoolean(op2.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) == (Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else if(op1.getTipo()==ContantesDaLinguagem.T_STRING && op2.getTipo()==ContantesDaLinguagem.T_STRING){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf(op1.getLexema().equals(op2.getLexema())));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        } 
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"=\" ");
                        break;
                        
                    case("<>"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if(op1.getTipo() == ContantesDaLinguagem.T_BOOLEAN && op2.getTipo() == ContantesDaLinguagem.T_BOOLEAN){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Boolean.parseBoolean(op1.getLexema())) != (Boolean.parseBoolean(op2.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) != (Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"<>\" ");
                        break;
                        
                    case(">"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) > (Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \">\" ");
                        break;
                    case(">="):
                        op1=aux.pop();
                        op2=aux.pop();
                        if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) >= (Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \">=\" ");
                        break;
                    case("<"):
                        op1=aux.pop();
                        op2=aux.pop();
                        if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) < (Double.parseDouble(op1.getLexema()))));
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"<\" ");
                        break;
                    case("<="):
                        op1=aux.pop();
                        op2=aux.pop();
                        if((op1.getTipo() == ContantesDaLinguagem.T_DOUBLE || op1.getTipo() == ContantesDaLinguagem.T_INTEGER) && (op2.getTipo() == ContantesDaLinguagem.T_DOUBLE || op2.getTipo() == ContantesDaLinguagem.T_INTEGER)){
                            res = new SimbolosCefetiny(ContantesDaLinguagem.T_CONSTANT,String.valueOf((Double.parseDouble(op2.getLexema())) <= (Double.parseDouble(op1.getLexema()))));                            
                            res.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                            aux.push(res);
                        }
                        else throw new FalhaErroDeSintaxe("Operando invalido para o operador \"<=\" ");
                        break;
                }
            }
        }
        return aux.pop();
    }
    
    public static Lista<SimbolosCefetiny> toPosfix(Lista<SimbolosCefetiny> exp){
        Lista<SimbolosCefetiny> pos = new Lista<>();
        ManipuladorDaLista<SimbolosCefetiny> aux = new ManipuladorDaLista<>();
        int abriuPar = 0;
        for(int i=0;i<exp.getTamanho();i++){
            if(exp.get(i).getToken()==ContantesDaLinguagem.ABREPAR){
                abriuPar++;
                aux.push(exp.get(i));
            }
            else if(exp.get(i).getToken()==ContantesDaLinguagem.FECHAPAR){
                if(abriuPar==0) throw new FalhaErroDeSintaxe("Parenteses nao foi aberto ");
                while((aux.peek().getToken()!=ContantesDaLinguagem.ABREPAR)){
                    pos.add(aux.pop());
                }
                abriuPar--;
                aux.pop();
            }
            else if(exp.get(i).getToken()==ContantesDaLinguagem.T_IDENTIFIER || exp.get(i).getToken()==ContantesDaLinguagem.T_CONSTANT) pos.add(exp.get(i));
            else if(aux.isEmpty() || !precedenciaMenor(exp.get(i).getToken(),aux.peek().getToken())){
                aux.push(exp.get(i));
            }
            else if(precedenciaMenor(exp.get(i).getToken(),aux.peek().getToken())){
                while(!aux.isEmpty() && precedenciaMenor(exp.get(i).getToken(),aux.peek().getToken()) ){
                    pos.add(aux.pop());
                }
                aux.push(exp.get(i));
            }   
        }
        while(!aux.isEmpty()) pos.add(aux.pop());
        return pos;
    }
    
    
    private static boolean precedenciaMenor(int op1,int op2){
        if(op2 == ContantesDaLinguagem.ABREPAR) return false;
        else if(op2 == ContantesDaLinguagem.UNOP) return true;
        else if(op1 == ContantesDaLinguagem.UNOP) return false;        
        else return op1<=op2;
    }
}
