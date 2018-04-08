package compilador;
import expressoes_basicas.Expressao;

import java.io.*;
import java.util.Iterator;

public class Compilador {

    public static void main(String[] args) throws IOException {
        PilhaDeVerificacao PilhaVer = new PilhaDeVerificacao();
        PalavrasReservadas ComandosSalvos = new PalavrasReservadas();
        Verificador Verifica = new Verificador(PilhaVer, ComandosSalvos);

        
        /*exp binarias implementação
        
        char[] VetorExpressao = new char[5];
        VetorExpressao[0]='5';
        VetorExpressao[1]='5';
        VetorExpressao[2]='+';
        VetorExpressao[3]='2';
        VetorExpressao[4]='2';
        
        
        expressoes_basicas.Expressao exp =  new Expressao(VetorExpressao); 
        exp.ordenaExpressao();
        exp.separaPilhas();
        exp.resolveExpressao();
        
        */
        ComandosSalvos.Reservando();
        
        Verifica.VerificaSintaxe();

        Verifica.valida();
        
        
    }
    
}
