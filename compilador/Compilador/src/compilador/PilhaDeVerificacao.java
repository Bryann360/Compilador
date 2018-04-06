package compilador;

import java.util.ArrayList;

public class PilhaDeVerificacao {
    ArrayList<String> Linhas = new ArrayList();
    
    public PilhaDeVerificacao(){
        
    }
    
    public void teste(){
        
    }
    
    public void AddPilha(String linha){
        Linhas.add(linha);
    }
    
    public ArrayList <String> getLinhas(){
        return Linhas;
    }
}
