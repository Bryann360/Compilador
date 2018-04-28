package InterpretadorDoPrograma;

import Excecoes.*;
public class ContantesDaLinguagem {

    public static final int T_BOOLEAN = 0;
    public static final int T_INTEGER = 1;
    public static final int T_DOUBLE = 2;
    public static final int T_STRING = 3;
    public static final int T_IDENTIFIER = 4;
    public static final int T_CONSTANT = 5;
    public static final int RELOP = 6;
    public static final int ADDOP = 7;
    public static final int MULOP = 8;
    public static final int UNOP = 9;
    public static final int ATRIBOP = 10;
    public static final int ABREPAR = 11;
    public static final int FECHAPAR = 12;
    public static final int END = 13;
    public static final int PRINT = 14;
    public static final int PRINTLN = 15;
    public static final int READINT = 16;
    public static final int IF = 17;
    public static final int ELSE = 18;
    public static final int ENDIF = 19;
    public static final int WHILE = 20;
    public static final int ENDWHILE = 21;
    public static final int FOR = 22;
    public static final int ENDFOR = 23;
    public static final int PRINTSTR = 24;
    public static final int START = 25;
    public static final int POWOP = 26;
    public static final int READFLOAT = 27;
    public static final int SEMICOLON = 28;
    
    
    public static String toString(int item) {
        switch (item) {
            case T_BOOLEAN:
                return "Boolean";
            case T_INTEGER:
                return "Integer";
            case T_DOUBLE:
                return "Double";
            case T_STRING:
                return "String";
            case T_IDENTIFIER:
                return "Identificador";
            case T_CONSTANT:
                return "Constante";
            case RELOP:
                return "Operador Relacional";
            case ADDOP:
                return "Operador +|-|or";
            case MULOP:
                return "Operador *|/|and";
            case UNOP:
                return "Operador sqrt|not";
            case POWOP:
                return "Operador ^";
            case ATRIBOP:
                return "Operador de atribuido";
            case ABREPAR:
                return "Abre parenteses";
            case FECHAPAR:
                return "Fecha Parenteses";
            case END:
                return "end";
            case PRINT:
                return "print";
            case PRINTLN:
                return "println";
            case READINT:
                return "readint";
            case READFLOAT:
                return "readfloat";                
            case IF:
                return "if";
            case ELSE:
                return "else";
            case ENDIF:
                return "endif";
            case WHILE:
                return "while";
            case ENDWHILE:
                return "endwhile";
            case FOR:
                return "for";
            case ENDFOR:
                return "endfor";
            case PRINTSTR:
                return "printstr";
            case START:
                return "begin";
        }

        throw new FalhaErroDeSintaxe("Elemento '" + item + "' nao eh valido.");
    }
}