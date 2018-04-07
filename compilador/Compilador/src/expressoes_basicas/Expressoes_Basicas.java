package expressoes_basicas;
import java.util.regex.*; 

import java.util.Scanner;
import java.lang.*;

public class Expressoes_Basicas {
    public static void main(String[] args) {
        
        System.out.println("Insira a expressão:");
        Scanner scanner = new Scanner(System.in);
        
        String expBasica = scanner.next();
        
        char[] vetorExpressao = expBasica.toCharArray(); 
        int numParenteseAberto = 0, numParenteseFechado = 0;
        for(int i=0 ; i<vetorExpressao.length; i++){
            if(expBasica.charAt(i)== '('){
                numParenteseAberto++;
            }
            if(expBasica.charAt(i)== ')'){
                numParenteseFechado++;
            }
        }
        try{
        if(numParenteseFechado!=numParenteseAberto){
            throw new ExcessaoInsercao("Os parenteses não fecham/abrem");
        }
        }catch(ExcessaoInsercao EI){
            System.out.println(EI.getMessage());
            return;
        }
        
        String regEx = "[A-Za-z]*";
        boolean verificaCaracter;
        verificaCaracter = expBasica.matches(regEx);
        
        try{
            
            if(verificaCaracter){
                throw new ExcessaoInsercao("Valor inválido digitado");
            }
             
        } catch(ExcessaoInsercao EI){
            System.out.println(EI.getMessage());
            return;
        }
        
        
           
           
        
        Expressao expressao = new Expressao(vetorExpressao);
        System.out.printf("Resultado: %.2f\n", expressao.resolveExpressao());
    
    }
}
