package InterpretadorDoPrograma;

import java.io.FileNotFoundException;
import Excecoes.*;

public class InterpretadorDoPrograma {

    private LeitorDoArquivo entrada = null;      
    private SimbolosCefetiny simboloAtual = null;    
    public boolean usarUltimoSimbolo = false; 
    private int linhaAvaliada = 1;              


    public InterpretadorDoPrograma(String nomeArquivo) throws FileNotFoundException {

        this.entrada = new LeitorDoArquivo(nomeArquivo);
    }

  
    public SimbolosCefetiny obterSimbolo() {
        String Lex = "";		
        SimbolosCefetiny simb = new SimbolosCefetiny();	

   
        if (this.usarUltimoSimbolo == false) {
            try {
                int s = 0;		
                char ch;	

                
                while (s != -1) {
                    ch = this.entrada.getItem(); 

                    switch (s) { 
                        case 0:	
                            if ((ch == ' ') || (ch == '\r')) {
                                s = 0;
                            } else if (ch == '\n') {
                                this.linhaAvaliada++;
                                s = 0;
                            } else if (caracterAlpha(ch)) {	
                                Lex += ch;
                                s = 1;
                            } else if (caracterNumerico(ch)) {	
                                Lex += ch;
                                s = 2;
                            } else if (ch == ':') {	
                                Lex += ch;
                                s = 4;
                            } else if (ch == '<') {	
                                Lex += ch;
                                s = 5;
                            } else if (ch == '>') {	
                                Lex += ch;
                                s = 6;
                            } 
                            else if ((ch == '+') || (ch == '-')) {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.ADDOP);
                                s = -1;
                            } 
                            else if ((ch == '*') || (ch == '/')) {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.MULOP);
                                s = -1;
                            } 
                            else if (ch == '^') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.POWOP);
                                s = -1;
                            } 
                            else if (ch == '=') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.RELOP);
                                s = -1;
                            } 
                            else if (ch == '(') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.ABREPAR);
                                s = -1;
                            } 
                            else if (ch == ')') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.FECHAPAR);
                                s = -1;
                            }else if (ch == ';') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.SEMICOLON);
                                s = -1;
                            }else if (ch == '\'') {
                                s = 7;
                            } else {            
                                Lex += ch;
                              
                                throw new FalhaErroDeSintaxe("Simbolo '" + Lex + "' invalido na linha " + this.linhaAvaliada);
                            }
                            break;
                        case 1: 
                           
                            if (caracterAlpha(ch) || caracterNumerico(ch))
                                Lex += ch;
                            else {
                                simb.setToken(ContantesDaLinguagem.T_IDENTIFIER);
                                this.entrada.ungetItem();
                                s = -1; 
                            }
                            break;
                        case 2: 
                            if (caracterNumerico(ch)) 
                                Lex += ch;
                            else if (ch == '.') {
                                Lex += ch;
                                s = 3;
                            } else {
                                simb.setToken(ContantesDaLinguagem.T_CONSTANT);
                                simb.setTipo(ContantesDaLinguagem.T_INTEGER);
                                this.entrada.ungetItem();
                                s = -1; 
                            }
                            break;
                        case 3:
                            if (caracterNumerico(ch))
                                Lex += ch;
                            else {
                                simb.setToken(ContantesDaLinguagem.T_CONSTANT);
                                simb.setTipo(ContantesDaLinguagem.T_DOUBLE);
                                this.entrada.ungetItem();
                                s = -1; 
                            }
                            break;
                        case 4:
                            if (ch == '=') {
                                Lex += ch;
                                simb.setToken(ContantesDaLinguagem.ATRIBOP);
                                s = -1;             
                            } else 
                                throw new FalhaErroDeSintaxe("Simbolo invalido '" + ch + "' na linha " + this.linhaAvaliada + ": era esperado '='");
                            break;
                        case 5:            
                            simb.setToken(ContantesDaLinguagem.RELOP);
                            if ((ch == '>') || (ch == '='))
                                Lex += ch;
                            else
                                this.entrada.ungetItem();

                            s = -1;              
                            break;
                        case 6:             
                            simb.setToken(ContantesDaLinguagem.RELOP);
                            if (ch == '=') 
                                Lex += ch;
                            else
                                this.entrada.ungetItem();

                            s = -1;                
                            break;
                        case 7:
                            if (ch != '\'')
                                Lex += ch;
                            else {
                                simb.setToken(ContantesDaLinguagem.T_CONSTANT);
                                simb.setTipo(ContantesDaLinguagem.T_STRING);
                                s = -1;
                            }

                            break;
                    }
                }
            } catch (FalhaAbrirArquivo e) {
                
            }

            Lex = Lex.toLowerCase();
            simb.setLexema(Lex);

            switch (Lex) {
                case "start":
                    simb.setToken(ContantesDaLinguagem.START);
                    break;
                case "true":
                case "false":
                    simb.setToken(ContantesDaLinguagem.T_CONSTANT);
                    simb.setTipo(ContantesDaLinguagem.T_BOOLEAN);
                    break;
                case "div":
                case "mod":
                case "and":
                    simb.setToken(ContantesDaLinguagem.MULOP);
                    break;
                case "not":
                case "sqrt":
                    simb.setToken(ContantesDaLinguagem.UNOP);
                    break;
                case "or":
                    simb.setToken(ContantesDaLinguagem.ADDOP);
                    break;
                case "print": 
                    simb.setToken(ContantesDaLinguagem.PRINT);
                    break;
                case "println":
                    simb.setToken(ContantesDaLinguagem.PRINTLN);
                    break;
                case "readint":
                    simb.setToken(ContantesDaLinguagem.READINT);
                    break;
                case "readfloat":
                    simb.setToken(ContantesDaLinguagem.READFLOAT);
                    break;
                case "if":
                    simb.setToken(ContantesDaLinguagem.IF);
                    break;
                case "else":
                    simb.setToken(ContantesDaLinguagem.ELSE);
                    break;
                case "endif":
                    simb.setToken(ContantesDaLinguagem.ENDIF);
                    break;
                case "while":
                    simb.setToken(ContantesDaLinguagem.WHILE);
                    break;
                case "endwhile":
                    simb.setToken(ContantesDaLinguagem.ENDWHILE);
                    break;
                case "for":
                    simb.setToken(ContantesDaLinguagem.FOR);
                    break;
                case "endfor":
                    simb.setToken(ContantesDaLinguagem.ENDFOR);
                    break;
                case "end":
                    simb.setToken(ContantesDaLinguagem.END);
                    break;
                case "printstr":
                    simb.setToken(ContantesDaLinguagem.PRINTSTR);
                    break;
            }                                        

            this.simboloAtual = simb;
        } else
            this.usarUltimoSimbolo = false;

        return this.simboloAtual;
    }

    public void ungetSimbol() {
        this.usarUltimoSimbolo = true;
    }

    private boolean caracterNumerico(char c) {
        String aux = "" + c;
        return ("0123456789".contains(aux));
    }

    private boolean caracterAlpha(char c) {
        String aux = "" + c;
        return ("abcdefghijklmnopqrstuvwxyz".contains(aux.toLowerCase()));
    }

    public int getNumeroDaLinha() {
        return this.linhaAvaliada;
    }
}

