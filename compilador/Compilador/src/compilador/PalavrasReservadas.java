package compilador;

import java.util.*;

public class PalavrasReservadas implements Reservado{
    /**ArrayList com as palavras reservadas*/
    ArrayList<String> comandosCefetiny = new ArrayList();

    @Override
    public void Reservando(){
        //Cria ArrayList com todos os comandos da linguagem cefetiny...
    }
    
    
    public ArrayList <String> getcomandos(){
        return comandosCefetiny;
    }
}
