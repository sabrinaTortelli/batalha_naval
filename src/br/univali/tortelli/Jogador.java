package br.univali.tortelli;

import java.util.Scanner;

public class Jogador {

    private final String nome;
    private final TipoJogador tipo;
    private Tabuleiro tabuleiros;
    private int disparos;
    private int disparosCertos;

    /**
     * Construtor de jogador
     * @param tipo se jogador for do tipo jogador ele pede o nome, se for do tipo computador ele cria e atribui computador ao nome
     */
    public Jogador(TipoJogador tipo) {
        if(tipo == TipoJogador.JOGADOR){
            this.tipo = TipoJogador.JOGADOR;
            this.nome = perguntaNome();
        } else{
            this.tipo = TipoJogador.COMPUTADOR;
            this.nome = "COMPUTADOR";
        }

    }

    public String getNome() {
        return nome;
    }

    public int getDisparos() {
        return disparos;
    }

    public void setDisparos() {
        this.disparos++;
    }

    public int getDisparosCertos() {
        return disparosCertos;
    }

    public void setDisparosCertos() {
        this.disparosCertos++;
    }

    public TipoJogador getTipo() {
        return tipo;
    }

    /**
     * Pergunta nome do jogador
     * @return retorna nome em maiúsulo
     */
    public String perguntaNome(){
        Scanner in = new Scanner(System.in).useDelimiter("\r\n");
        System.out.println("Qual seu nome?");
        return in.nextLine().toUpperCase();
    }

    public void setTabuleiros(Tabuleiro tabuleiros) {
        this.tabuleiros = tabuleiros;
    }

    public Tabuleiro getTabuleiros() {
        return tabuleiros;
    }
}
