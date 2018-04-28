package Lista;

public class Lista<T> {
    
    private T vetor[];
    protected int lastIndex = 0, tamanho=15, gapAumento=5;

    public int getTamanho() {
        int aux=0;
        for(int i=0;i<this.tamanho;i++){
            if(vetor[i]!=null)aux++;
        }
        return aux;
    }
    
    public Lista(int tamanho){
        vetor  = (T[]) new Object[tamanho];
        this.tamanho = tamanho;
    }
    
    public Lista(){
        vetor  = (T[]) new Object[this.tamanho];
    }
    
    protected void aumenta(int tam){
        T aux[] = (T[])  new Object[tam+1];
        for(int i=0;i<vetor.length;i++){
            aux[i]=vetor[i];
        }
        this.tamanho=tam;
        vetor=aux;
    }
    
    public void inverte(){
        T aux[]= (T[]) new Object[this.tamanho];
        for(int i=this.getTamanho()-1, j = 0;i>=0;i--, j++){
            aux[j] = vetor[i];
        }
    }
    
    public void add(T objeto){
        if(lastIndex<tamanho){
            vetor[lastIndex] = objeto;
            lastIndex++;
        }
        else{
            this.aumenta(tamanho+gapAumento);
            vetor[lastIndex] = objeto;
            lastIndex++;
        }
    }
 
    public void remove(int idx){
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
    
    public T get(int idx){
        return vetor[idx];
    }
    
    public void set(int idx, T obj){
        vetor[idx]=obj; 
    }
    
    @Override
    public String toString(){
        String r = "[";
        for(int i=0;i<tamanho;i++){
            r += vetor[i];
            if(i<tamanho-1)r += ",";
        }
        r += "]";
        
        return r;
    }
    
    
}
