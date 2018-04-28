package cefetiny;
import java.util.ArrayList;
import InterpretadorDoPrograma.*;
import Lista.*;
import Excecoes.*;

public class ManipuladorArquivo {
    private InterpretadorDoPrograma LeituraSimbolos;         
    private ArrayList<Comando> LeituraComandos;    
    private SimbolosCefetiny LeituraEntrada;           


    public ManipuladorArquivo(String fileName) throws java.io.FileNotFoundException {
        this.LeituraSimbolos = new InterpretadorDoPrograma(fileName);      
        this.LeituraComandos = new ArrayList<>();              
    }    
    
    public void run() {
        
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();        

        if(this.LeituraEntrada.getToken() != ContantesDaLinguagem.START)
            throw new FalhaErroDeSintaxe("Erro: bloco de comando invalido na linha "
                        + this.LeituraSimbolos.getNumeroDaLinha() + ": era esperado begin");

        do {

            this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
            
   
            Comando cmd = null;

    
            cmd = getComando(this.LeituraEntrada.getToken());


            this.LeituraComandos.add(cmd);

   
        } while (this.LeituraEntrada.getToken() != ContantesDaLinguagem.END);


        this.executa();
    }
    

    
    
    public Comando getComando(int identificador){
        Comando cmd;
        switch (identificador) {
                case ContantesDaLinguagem.FOR:
                    cmd = trataFor();
                    break;
                case ContantesDaLinguagem.WHILE:
                    cmd = trataWhile();
                    break;
                case ContantesDaLinguagem.ENDWHILE:
                    cmd = trataEndWhile();
                    break;
                case ContantesDaLinguagem.ELSE:
                    cmd = trataElse();
                    break;
                case ContantesDaLinguagem.ENDIF:
                    cmd = trataEndIf();
                    break;
                case ContantesDaLinguagem.IF:
                    cmd = trataIf();
                    break;
                case ContantesDaLinguagem.T_IDENTIFIER:
                    cmd = trataAtrib();
                    break; 
                case ContantesDaLinguagem.READINT:
                    cmd = trataComandoReadInt();
                    break;               
                case ContantesDaLinguagem.PRINTLN:
                    cmd = trataComandoPrintln();
                    break;
                case ContantesDaLinguagem.PRINT:
                    cmd = trataComandoPrint();
                    break;
                case ContantesDaLinguagem.END:
                    cmd = trataComandoEnd();
                    break;
                default:					
                   
                    throw new FalhaErroDeSintaxe("Erro: bloco de comando invalido na linha "
                            + this.LeituraSimbolos.getNumeroDaLinha() + ": foi encontrado '"
                            + this.LeituraEntrada.getLexema() + "' ");
            }
        return cmd;
    }
    
    
    
    
    private Comando trataAtrib(){
        
   
        LeituraSimbolos.usarUltimoSimbolo = false;
        

        SimbolosCefetiny var = this.LeituraEntrada; 
        
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        
 
        if (this.LeituraEntrada.getToken() != ContantesDaLinguagem.ATRIBOP) {
            throw new FalhaErroDeSintaxe("Era esperado um := depois de uma variavel na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }

        
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        
 
        Lista<SimbolosCefetiny> expressao = new Lista(); 
        getExpressao(expressao);
        

        return new ComandoDeAtribuicao(expressao, var.getLexema());
    }
    
    
    
    
    private void getExpressao(Lista<SimbolosCefetiny> exp){
        

        boolean isOp = false;
        Lista<SimbolosCefetiny> aux = exp;
        
   
        int abriu=0;
        
     
        while(isValid(this.LeituraEntrada.getToken())){

           
            if(this.LeituraEntrada.getToken()==ContantesDaLinguagem.ABREPAR || this.LeituraEntrada.getToken()==ContantesDaLinguagem.FECHAPAR){
                if(this.LeituraEntrada.getToken()==ContantesDaLinguagem.ABREPAR){
                    
                    abriu++;
                    aux.add(this.LeituraEntrada);
                }else if(this.LeituraEntrada.getToken()==ContantesDaLinguagem.FECHAPAR){
                    
         
                    if(abriu>0){
                        abriu--;
                        aux.add(this.LeituraEntrada);
                    } 
                }
                this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
            }
            
           
            else if(this.LeituraEntrada.getToken()== ContantesDaLinguagem.T_IDENTIFIER || this.LeituraEntrada.getToken()== ContantesDaLinguagem.T_CONSTANT){
                
               
                if(isOp){
                    break;
                }
                
                
                else{
                    aux.add(this.LeituraEntrada);
                    isOp=true;
                    this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
                }
            }
            
          
            else if(this.LeituraEntrada.getToken()==ContantesDaLinguagem.UNOP){
                
             
                aux.add(this.LeituraEntrada);
                this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
                
              
                Lista<SimbolosCefetiny> expressao = new Lista<>();
                getExpressao(expressao);
                for(int i=0;i<expressao.getTamanho();i++){
                    aux.add(expressao.get(i));
                }
                isOp = true;
            }
            
          
            else {
                
              
                if(!isOp){
                    throw new FalhaErroDeSintaxe("Era esperado um operador apos um operando na linha "
                    + this.LeituraSimbolos.getNumeroDaLinha());
                }
                
             
                aux.add(this.LeituraEntrada);
                isOp=false;
                this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
                
            } 
        }
        LeituraSimbolos.ungetSimbol(); 
    }
    
 
    
    
    private boolean isValid(int a){
        return(a== ContantesDaLinguagem.T_IDENTIFIER ||
                a== ContantesDaLinguagem.T_CONSTANT ||
                a== ContantesDaLinguagem.RELOP ||
                a== ContantesDaLinguagem.ADDOP ||
                a== ContantesDaLinguagem.MULOP ||
                a== ContantesDaLinguagem.UNOP||
                a== ContantesDaLinguagem.POWOP||
                a== ContantesDaLinguagem.RELOP||
                a== ContantesDaLinguagem.ABREPAR||
                a== ContantesDaLinguagem.FECHAPAR);
    }
    
 
    
    
    private Comando trataEndWhile(){
        return new ComandoEndWhile();
    }
    
  
    private Comando trataEndIf(){
        return new ComandoEndIf();
    }
    
    
    
  
    private Comando trataElse(){
        return new ComandoElse();
    }
    
    
    

    private Comando trataFor(){
        
  
        LeituraSimbolos.usarUltimoSimbolo=false;

        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        
    
        if(this.LeituraEntrada.getToken()!=ContantesDaLinguagem.ABREPAR){
            throw new FalhaErroDeSintaxe("Era esperado '(' logo apos 'for' na linha " 
                + this.LeituraSimbolos.getNumeroDaLinha());
        }

        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        Comando atrib = getComando(this.LeituraEntrada.getToken());
        

        Lista<SimbolosCefetiny> condicao = new Lista();
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(this.LeituraEntrada.getToken()!=ContantesDaLinguagem.SEMICOLON){
          throw new FalhaErroDeSintaxe("Era esperado um ; entre a atribuicao e a condicao do 'for' na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
   
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        getExpressao(condicao);
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(this.LeituraEntrada.getToken()!=ContantesDaLinguagem.SEMICOLON){
          throw new FalhaErroDeSintaxe("Era esperado um ; entre a condicao e o incremento do 'for' na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
   
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        Comando inc = getComando(this.LeituraEntrada.getToken());
        
        
        Lista<Comando> cmds = new Lista();
        while(this.LeituraEntrada.getToken()!= ContantesDaLinguagem.ENDFOR){
            cmds.add(getComando(this.LeituraEntrada.getToken()));
            this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        }
        
    
        return new ComandoFor(condicao,cmds,inc,atrib);
    }
    
    
    

    private Comando trataWhile(){
        
    
        LeituraSimbolos.usarUltimoSimbolo=false;
        
   
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(this.LeituraEntrada.getToken()!=ContantesDaLinguagem.ABREPAR){
            throw new FalhaErroDeSintaxe("Era esperado '(' logo apos 'while' na linha " 
                + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
        
        Lista<SimbolosCefetiny> expressao = new Lista();
        getExpressao(expressao);
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(!this.LeituraEntrada.getLexema().equals("do")){
            throw new FalhaErroDeSintaxe("Era esperado 'do' logo apos a expressao do while na linha " 
                + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
  
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        Lista<Comando> comandos = new Lista();
        while(this.LeituraEntrada.getToken()!= ContantesDaLinguagem.ENDWHILE){
            comandos.add(getComando(this.LeituraEntrada.getToken()));
            this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        }
        
    
        return new ComandoWhile(expressao,comandos);
    }
    
  
    
    
    private Comando trataIf(){
        
  
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(this.LeituraEntrada.getToken()!=ContantesDaLinguagem.ABREPAR){
            throw new FalhaErroDeSintaxe("Era esperado '(' logo apos 'if' na linha " 
                + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
        
        Lista<SimbolosCefetiny> expressao = new Lista();
        getExpressao(expressao);
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if(!this.LeituraEntrada.getLexema().equals("then")){
            throw new FalhaErroDeSintaxe("Era esperado 'then' logo apos a expressao do if na linha " 
                + this.LeituraSimbolos.getNumeroDaLinha());
        }
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        
     
        Lista<Comando> comandos = new Lista();
        while(this.LeituraEntrada.getToken()!= ContantesDaLinguagem.ENDIF){
            comandos.add(getComando(this.LeituraEntrada.getToken()));
            this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        }
        
  
        return new ComandoIf(expressao,comandos);
    }
    
    
    
    
    private Comando trataComandoReadInt(){
        
 
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if (this.LeituraEntrada.getToken() != ContantesDaLinguagem.ABREPAR) {
            throw new FalhaErroDeSintaxe("Era esperado um ( depois de 'readint' na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }
        this.LeituraEntrada =this.LeituraSimbolos.obterSimbolo();
        if (this.LeituraEntrada.getToken() != ContantesDaLinguagem.T_IDENTIFIER) {
             throw new FalhaErroDeSintaxe("O comando readint so aceita variaveis(linha "
             + this.LeituraSimbolos.getNumeroDaLinha() + ")");
        }
        
      
        String aux = this.LeituraEntrada.getLexema();
        
      
        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        if (this.LeituraEntrada.getToken() != ContantesDaLinguagem.FECHAPAR) {
            throw new FalhaErroDeSintaxe("Era esperado um ) depois de \"" + aux + "\" na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }
        
   
        return new ComandoReadInt(aux);
        
    }
    
    
    
    
    private Comando trataComandoPrint(){
        
    
        LeituraSimbolos.usarUltimoSimbolo=false;
        this.LeituraEntrada =this.LeituraSimbolos.obterSimbolo();

       
        if (this.LeituraEntrada.getToken() != ContantesDaLinguagem.ABREPAR) {
            throw new FalhaErroDeSintaxe("Era esperado um ( depois de print na linha "
            + this.LeituraSimbolos.getNumeroDaLinha());
        }
        

        this.LeituraEntrada = this.LeituraSimbolos.obterSimbolo();
        Lista<SimbolosCefetiny> expressao = new Lista();
        getExpressao(expressao);

   
        return new ComandoPrint(expressao);
    }

 
    
    
    private Comando trataComandoEnd() {
        
        return new ComandoEnd();	
    }





    private Comando trataComandoPrintln() {
      
        return new ComandoPrintln();
    }
    
   
    
    
    private void executa() {
        for(Comando comando: this.LeituraComandos)
            comando.executa();
    }
}