package Memoria;
import InterpretadorDoPrograma.*;
import Excecoes.*;

public class Memoria  {
    
    
    protected static int lastIndex = 0, tamanho=15, gapAumento=5;
    private static ManipuladorDaMemoria[] vetor  = new ManipuladorDaMemoria[tamanho];
    
    private static void aumenta(int tam){
        ManipuladorDaMemoria aux[] = new ManipuladorDaMemoria[tam+1];
        for(int i=0;i<vetor.length;i++){
            aux[i]=vetor[i];
        }
        tamanho=tam;
        vetor=aux;
    }
    
    public static int exists(String idx){
        for(int i=0;i<tamanho;i++){
            if(vetor[i]!= null){
                if(vetor[i].getIdentificador().equals(idx)) return i;
            }
        }
        return -1;
    }
    
    public static void add(ManipuladorDaMemoria objeto){
        if(lastIndex<tamanho && exists(objeto.getIdentificador())==-1){
            vetor[lastIndex] = objeto;
            lastIndex++;
        }
        else if(exists(objeto.getIdentificador())!=-1){
            vetor[exists(objeto.getIdentificador())].setSimbolo(objeto.getSimbolo());
        }
        else{
            aumenta(tamanho+gapAumento);
            vetor[lastIndex] = objeto;
            lastIndex++;
        }
    }
    
    public static SimbolosCefetiny get(String idx){
        if(exists(idx)!= -1){
            return vetor[exists(idx)].getSimbolo();
        }
        else throw new FalhaErroDeSintaxe("Simbolo " + idx + " não existe na memoria");
    }
    
    
    private static void removeAt(int idx){
        if(idx>= tamanho){
            throw new IndexOutOfBoundsException();
        }
        else{
            for(int i=idx;i<vetor.length;i++){
                if(i+1<vetor.length)vetor[i]=vetor[i+1];
                else vetor[i]=null; 
            }
        }
    }

}