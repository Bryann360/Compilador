package compilador;
import java.util.*;
import java.io.*;

public class Verificador extends PalavrasReservadas implements Validavel{
    PilhaDeVerificacao Pilha;
    PalavrasReservadas ComandosSalvos;
    
    
    public Verificador(PilhaDeVerificacao pilha, PalavrasReservadas ComandosSalvos){
        this.Pilha = pilha;
        this.ComandosSalvos = ComandosSalvos;
    }
    
    public void VerificaSintaxe() throws FileNotFoundException, IOException{
        String linha;
        BufferedReader in;

        in = new BufferedReader(new FileReader("..\\cefet.txt"));
        linha = in.readLine();

        while(linha != null)
        {       
               String linhaSemEspaco = linha;
               
               //System.out.println(linha);
               Pilha.AddPilha(linhaSemEspaco.trim());
               linha = in.readLine();
        }

    }
    
    @Override
    public void valida(){
        Iterator it = Pilha.getLinhas().iterator();
        while (it.hasNext()) {
            String linhaDeVerificacao = (String) it.next();
            System.out.println(linhaDeVerificacao);
            String[] primeiraPalavraLinhaDeVerificacao = linhaDeVerificacao.split(" ");
            char[] primeiraLetra = primeiraPalavraLinhaDeVerificacao[0].toCharArray();
                
                if(Character.isLetter(primeiraLetra[0])){
            
                
            
                    Iterator it2 = ComandosSalvos.getcomandos().iterator();

                    while(it2.hasNext()){
                        if(primeiraPalavraLinhaDeVerificacao[0] != it2.next()){
                            if(primeiraPalavraLinhaDeVerificacao[1]== ":="){
                                
                            }else{
                                System.out.println("Erro de compilação: Variável sem atribuição"+ primeiraPalavraLinhaDeVerificacao[0]);
                                /*Erro porque apos a variavel, não tem atribuição*/
                            }
                        }else{
                            /*Codigo de verificacao de palavras reservadas*/
                        }
                    }
                    
                    
                }else {
                    System.out.println("Erro de compilação: "+primeiraPalavraLinhaDeVerificacao[0]+" começa com um digito");
                }
        }
    }    
}
