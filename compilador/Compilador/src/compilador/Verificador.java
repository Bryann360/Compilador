package compilador;
import java.util.*;
import java.io.*;

public class Verificador extends PalavrasReservadas implements Validavel{
    PilhaDeVerificacao Pilha;
    PalavrasReservadas ComandosSalvos;
    ArrayList<String> Comandos = new ArrayList();
    
    public Verificador(PilhaDeVerificacao pilha, PalavrasReservadas ComandosSalvos){
        this.Pilha = pilha;
    }
    
    public void VerificaSintaxe() throws FileNotFoundException, IOException{
        String linha;
        BufferedReader in;

        in = new BufferedReader(new FileReader("..\\..\\cefet.tiny.txt"));
        linha = in.readLine();

        while(linha != null)
        {       
               
               System.out.println(linha);
               Pilha.AddPilha(linha);
               linha = in.readLine();
        }

    }
    
    @Override
    public void valida(){
        
    }
    
    
    
    
    
}
