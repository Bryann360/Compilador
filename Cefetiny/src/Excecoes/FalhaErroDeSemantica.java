package Excecoes;

public class FalhaErroDeSemantica extends RuntimeException {

    public FalhaErroDeSemantica(String msg) {
        super("\nErro de Semantica\n=>"
                + msg);
    } 
}
