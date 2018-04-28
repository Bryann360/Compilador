package Excecoes;

public class FalhaErroDeSintaxe extends RuntimeException {

    public FalhaErroDeSintaxe(String msg) {
        super("\nErro de sintaxe\n=>" + msg);
    }
}