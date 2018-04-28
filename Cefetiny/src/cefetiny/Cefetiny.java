package cefetiny;
import Excecoes.*;
import java.io.IOException;
import java.util.Scanner;

public class Cefetiny {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Qual o caminho do arquivo a ser executado? ");
        String fileName = in.next();
        if(!fileName.contains("/")){
            fileName = "src/Programas/" + fileName + ".tiny";
        }
        while(!fileName.equals("sair")){
            try {
                ManipuladorArquivo cefeTiny = new ManipuladorArquivo(fileName);
                cefeTiny.run();
                
                System.out.print("\nQual o caminho do arquivo a ser executado? ('sair' para sair): ");
                fileName = in.next();
                if(fileName.equals("sair")) break;
                if(!fileName.contains("/")){
                    fileName = "src/Programas/" + fileName + ".tiny";
                }
                
            } catch (FalhaErroDeSintaxe | IOException e) {
                System.out.println("Arquivo não encontrado");
                System.out.print("\nQual o caminho do arquivo a ser executado? ('sair' para sair): ");
                fileName = in.next();
                if(fileName.equals("sair")) break;
            }
        }
    }
}