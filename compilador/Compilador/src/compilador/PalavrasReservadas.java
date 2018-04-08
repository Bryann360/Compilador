package compilador;

import java.util.*;

public class PalavrasReservadas implements Reservado{
    /**ArrayList com as palavras reservadas*/
    ArrayList<String> comandosCefetiny = new ArrayList();

    @Override
    public void Reservando(){
        comandosCefetiny.add("if");
        comandosCefetiny.add("while");
        comandosCefetiny.add("for");
        comandosCefetiny.add("endif");
        comandosCefetiny.add("end");
        comandosCefetiny.add("readInt");
        comandosCefetiny.add("print");
        comandosCefetiny.add("println");
        comandosCefetiny.add("else");
        comandosCefetiny.add("do");
    }
    
    
    public ArrayList <String> getcomandos(){
        return comandosCefetiny;
    }
}
