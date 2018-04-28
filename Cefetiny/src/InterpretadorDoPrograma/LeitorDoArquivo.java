package InterpretadorDoPrograma;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import Excecoes.*;

public class LeitorDoArquivo {

    private DataInput arquivo = null;
    private boolean usarUltimoSimbolo = false;
    private char simboloAtual = '\0';

    public LeitorDoArquivo(String fileName) throws FileNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        this.arquivo = new DataInputStream(fileIn);
    }

    public char getItem() throws FalhaAbrirArquivo {
        if (this.usarUltimoSimbolo == false) {
            try {
                this.simboloAtual = (char)this.arquivo.readByte();
            } catch (EOFException e) {
                throw new FalhaAbrirArquivo(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        } else
            this.usarUltimoSimbolo = false;

        return this.simboloAtual;
    }

    public void ungetItem() {
        if (this.usarUltimoSimbolo)
            throw new RuntimeException("Soh eh possivel retornar um elemento.");

        this.usarUltimoSimbolo = true;
    }
}
