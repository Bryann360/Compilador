package Lista;

public class ManipuladorDaLista<T> {
    private int top=-1,tamanho=15, gapAumento=5;
    private T vetor[]= (T[]) new Object[this.tamanho];
    
    
    protected void aumenta(int tam){
        T aux[] = (T[])  new Object[tam+1];
        for(int i=0;i<vetor.length;i++){
            aux[i]=vetor[i];
        }
        this.tamanho=tam;
        vetor=aux;
    }
    
    public void push(T obj){
        if(top + 1 >= tamanho) this.aumenta(gapAumento);
        top++;
        vetor[top]=obj;
        
    }
    
    public T peek(){
        return vetor[top];
    }
    
    public void inverte(){
        T aux[]= (T[]) new Object[this.tamanho];
        for(int i=this.getTamanho()-1, j = 0;i>=0;i--, j++){
            aux[j] = vetor[i];
        }
    }
    
    public T pop(){
        T aux=vetor[top];
        top--;
        return aux;
    }
    
    public boolean isEmpty(){
        return top==-1;
    }
    
    public int getTamanho(){
        int aux = 0;
        for(int i=0;i<this.tamanho;i++){
            if(vetor[i]!= null) aux++;
        }
        return aux;
    }
}
