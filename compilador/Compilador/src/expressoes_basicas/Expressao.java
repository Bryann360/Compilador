package expressoes_basicas;

public class Expressao {
    private char operando1;
    private String[] pilhaToken;
    private char[] expressao;
    private String[] pilhaSaida;
    private String[] pilhaOperadores;
    private char operador;
    private int tamanhoExpressao;

    public Expressao(char[] expressao) {
        this.expressao = expressao;
    }
    
    public void contaTokens(){
        String aux = new String();
        int contaTokens = 0;
        boolean negativo=false;
        
        for(int i=0; i<expressao.length; i++){
            if(expressao[i]>='0'&&expressao[i]<='9'){
                aux=String.valueOf(expressao[i]);
                if(negativo==true){
                    aux=String.valueOf(expressao[i-1]);
                    aux+=String.valueOf(expressao[i]);
                }
                while((i<expressao.length-1)&&(expressao[i+1]>='0'&&expressao[i+1]<='9')){
                    aux+=String.valueOf(expressao[i+1]);
                    i++;
                }

                contaTokens++;
                negativo=false;
                aux = new String();
            }
            
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]>='0')&&(expressao[i+1]<='9'))||((i>0)&&(i<expressao.length)&&(expressao[i]=='-')&&(expressao[i-1]=='(')&&!(expressao[i+1]=='('))){
                negativo=true;
            }
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]=='('))){
                contaTokens+=2;
            }
            
            else if((i<expressao.length)&&(expressao[i]=='*'||expressao[i]=='+'||expressao[i]=='/'||expressao[i]=='-'||expressao[i]=='X'||expressao[i]=='('||expressao[i]==')'||expressao[i]=='^')){
                contaTokens++;
            }
        }
        tamanhoExpressao = contaTokens;
    }
    
    public void ordenaExpressao(){
        this.contaTokens();
        String aux = new String();
        int indiceOrdenada = 0;
        int contaNumeros = 0;
        int contaOperadores = 0;
        boolean negativo=false;
        pilhaToken=new String[tamanhoExpressao+1];
        for(int i=0; i<expressao.length; i++){
            if(expressao[i]>='0'&&expressao[i]<='9'){
                contaNumeros++;
                aux=String.valueOf(expressao[i]);
                if(negativo==true){
                    aux=String.valueOf(expressao[i-1]);
                    aux+=String.valueOf(expressao[i]);
                }
                while((i<expressao.length-1)&&(expressao[i+1]>='0'&&expressao[i+1]<='9')){
                    aux+=String.valueOf(expressao[i+1]);
                    i++;
                }
                
                pilhaToken[indiceOrdenada]=aux;
                indiceOrdenada++;
                negativo=false;
                aux = new String();
            }
            
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]>='0')&&(expressao[i+1]<='9'))||((i>0)&&(i<expressao.length)&&(expressao[i]=='-')&&(expressao[i-1]=='(')&&!(expressao[i+1]=='('))){
                negativo=true;
            }
            
            else if(((i==0)&&(expressao[i]=='-')&&(expressao[i+1]=='('))){
                pilhaToken[indiceOrdenada]="-1";
                indiceOrdenada++;
                pilhaToken[indiceOrdenada]="*";
                indiceOrdenada++;
                contaOperadores+=2;
            }
            
            else if((i<expressao.length)&&(expressao[i]=='*'||expressao[i]=='+'||expressao[i]=='/'||expressao[i]=='-'||expressao[i]=='X'||expressao[i]=='('||expressao[i]==')'||expressao[i]=='^')){
                contaOperadores++;
                pilhaToken[indiceOrdenada]=String.valueOf(expressao[i]);
                indiceOrdenada++;
            }
        }
        pilhaSaida = new String[contaNumeros+contaOperadores];
        pilhaOperadores = new String[contaOperadores];
    }
    
    public void separaPilhas(){
        pilhaToken[tamanhoExpressao]="end";
        int indicePilhaSaida = 0;
        int indicePilhaOperadores = 0;
        for(int i=0; i<=tamanhoExpressao; i++){
            if(pilhaToken[i].equalsIgnoreCase("*")||pilhaToken[i].equalsIgnoreCase("+")||pilhaToken[i].equalsIgnoreCase("/")||pilhaToken[i].equalsIgnoreCase("-")||pilhaToken[i].equalsIgnoreCase("(")||pilhaToken[i].equalsIgnoreCase(")")||pilhaToken[i].equalsIgnoreCase("^")){
                switch (pilhaToken[i]){
                    case "+":
                        if((indicePilhaOperadores>0)&&(!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("("))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaSaida++;
                            }
                            indicePilhaOperadores++;
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "-":
                        if((indicePilhaOperadores>0)&&(!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("("))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&(!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("(")); indicePilhaOperadores--){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaSaida++;
                            }
                            indicePilhaOperadores++;
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "*":
                        if((indicePilhaOperadores>0)&&((pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("*"))||(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("/"))||(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("^")))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&indicePilhaOperadores-j>=0&&!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("("); j++){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaOperadores--;
                                indicePilhaSaida++;
                            }
                        }
                        
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "/":
                        if((indicePilhaOperadores>0)&&(pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("*")||pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("/")||pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("^"))){
                            for(int j=1; (indicePilhaOperadores-j)>=0&&indicePilhaOperadores-j>=0&&!pilhaOperadores[indicePilhaOperadores-j].equalsIgnoreCase("("); j++){
                                pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-j];
                                pilhaOperadores[indicePilhaOperadores-j]=pilhaToken[i];
                                indicePilhaOperadores--;
                                indicePilhaSaida++;
                            }
                        }
                        else{
                            pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                            indicePilhaOperadores++;
                        }
                        break;
                        
                    case "(":
                        pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                        indicePilhaOperadores++;
                        break;
                        
                    case ")":
                        while(indicePilhaOperadores>0&&!pilhaOperadores[indicePilhaOperadores-1].equalsIgnoreCase("(")){
                            pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores-1];
                            indicePilhaSaida++;
                            indicePilhaOperadores--;
                        }
                        indicePilhaOperadores--;
                        break;
                    
                    case "^":
                        pilhaOperadores[indicePilhaOperadores]=pilhaToken[i];
                        indicePilhaOperadores++;
                        break;
                }
            }
            
            else if(pilhaToken[i].equalsIgnoreCase("end")){
                indicePilhaOperadores--;
                if(indicePilhaOperadores>=0){
                do{
                    pilhaSaida[indicePilhaSaida]=pilhaOperadores[indicePilhaOperadores];
                    indicePilhaSaida++;
                    indicePilhaOperadores--;
                    }
                    while(indicePilhaOperadores>=0);
                }
                tamanhoExpressao = indicePilhaSaida;
            }
            
            else{
                pilhaSaida[indicePilhaSaida]=pilhaToken[i];
                indicePilhaSaida++;
            }
        }
    }
    
    public void resolveExpressao(){
        int numOperacoes = pilhaOperadores.length;
        int i=0;
        int topo=tamanhoExpressao-1;
        double auxiliar;
        while(topo>1){
            if(pilhaSaida[i].equalsIgnoreCase("*")||pilhaSaida[i].equalsIgnoreCase("+")||pilhaSaida[i].equalsIgnoreCase("/")||pilhaSaida[i].equalsIgnoreCase("-")||pilhaSaida[i].equalsIgnoreCase("(")||pilhaSaida[i].equalsIgnoreCase(")")||pilhaSaida[i].equalsIgnoreCase("^")){
                switch (pilhaSaida[i]){
                    case "+":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])+Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "-":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])-Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "*":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])*Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                        
                    case "/":
                        auxiliar=Double.parseDouble(pilhaSaida[i-2])/Double.parseDouble(pilhaSaida[i-1]);
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                    
                    case "^":
                        auxiliar=Math.pow(Float.parseFloat(pilhaSaida[i-2]), Float.parseFloat(pilhaSaida[i-1]));
                        pilhaSaida[i-2]=String.valueOf(auxiliar);
                        topo-=2;
                        if(topo>1){
                            for(int j=i-1; j<=topo; j++){
                                pilhaSaida[j]=pilhaSaida[j+2];
                            }
                        }
                        i-=2;
                        break;
                }
            }
            i++;
        }
        System.out.println(pilhaSaida[0]);
    }
}
