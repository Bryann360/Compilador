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
               linhaSemEspaco = linhaSemEspaco.replaceAll("\\s+", " ");
               
               
               
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
            String[] palavraLinhaDeVerificacao = linhaDeVerificacao.split(" ");
            char[] primeiraLetra = palavraLinhaDeVerificacao[0].toCharArray();
                
                if(Character.isLetter(primeiraLetra[0])){
            
                
            
                    Iterator it2 = ComandosSalvos.getcomandos().iterator();
                    String apoio = "nao";
                    
                    
                    while(it2.hasNext()){
                        
                        if(it2.next().equals(palavraLinhaDeVerificacao[0])){
                            apoio = "sim";
                            
                        
                        }
                    }
                    
                    //System.err.println(palavraLinhaDeVerificacao[0]);
                    if("nao".equals(apoio)){    
                        if(":=".equals(palavraLinhaDeVerificacao[1])){
                            String stringUnica = Arrays.toString(palavraLinhaDeVerificacao);
                            
                            stringUnica = stringUnica.replaceAll(".*:=.?", "");
                            stringUnica = stringUnica.replaceAll("]$", "");
                            stringUnica = stringUnica.trim();
                            
                            if(!stringUnica.isEmpty()){
                            
                                String[] partesExpressao = stringUnica.split("(\\+|\\-|\\*|\\/)");
                                for(int i=0 ; i<partesExpressao.length ;i++ ){
                                    partesExpressao[i] = partesExpressao[i].trim();

                                    Iterator it3 = ComandosSalvos.getcomandos().iterator();


                                    while(it3.hasNext()){

                                        if(it3.next().equals(partesExpressao[i])){
                                            System.err.println("Palavra reservada ultilizada após atribuição - "+partesExpressao[i]);
                                        }
                                    }
                                }
                            }else{
                                System.err.println("Variavel sem parametro - "+palavraLinhaDeVerificacao[0]);
                            }
                        }else{
                            System.err.println("Erro de compilação: Variável sem atribuição - "+ palavraLinhaDeVerificacao[0]);
                            /*Erro porque apos a variavel, não tem atribuição*/
                        }
                    }else{
                        switch (palavraLinhaDeVerificacao[0]){
                            case "if":
                                
                                break;
                            case "for":
                                break;
                            case "while":
                                break;
                            case "endif":
                                break;
                            case "end":
                                break;
                            case "readInt":
                                break;
                            case "print":
                                break;
                            case "println":
                                break; 
                            case "else":
                                break;
                            case "do":
                                break;   
                            default:
                                
                        }
                    }
                }else {
                    System.out.println("Erro de compilação: "+palavraLinhaDeVerificacao[0]+" começa com um digito");
                }
                
                
                
                
        }
    }    
}
