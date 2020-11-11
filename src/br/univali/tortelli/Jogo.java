package br.univali.tortelli;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

    ArrayList<Jogador> jogadores;

    /**
     * Método que roda o jogo
     */
    public void roda(){
        mostraTitulo();
        Jogador jogador = new Jogador(TipoJogador.JOGADOR);
        Jogador computador = new Jogador(TipoJogador.COMPUTADOR);
        jogadores = new ArrayList<>();
        jogadores.add(jogador);
        jogadores.add(computador);
        boolean novoJogo;
        do {
            Tabuleiro tabJogador = new Tabuleiro();
            Tabuleiro tabComputador = new Tabuleiro();
            tabJogador.mostraExemploTabuleiro();
            tabJogador.posicionaNavios(jogador);
            tabComputador.posicionaNavios(computador);
            jogadores.get(0).setTabuleiros(tabJogador);
            jogadores.get(1).setTabuleiros(tabComputador);
            Disparos disparos = new Disparos();
            int num = sorteiaJogador();
            boolean ganhou;
            ganhou = disparos.dispara(jogadores, num);
            if (num == 0) {
                num += 1;
            } else {
                num -= 1;
            }
            while (!ganhou) {
                ganhou = disparos.dispara(jogadores, num);
                if (!ganhou) {
                    if (num == 0) {
                        num += 1;
                    } else {
                        num -= 1;
                    }
                }
            }
            disparos.resultado(jogadores);
            novoJogo = perguntaNovoJogo();
        }while(novoJogo);
    }

    /**
     * Mostra título do jogo
     */
    public void mostraTitulo(){
        System.out.println("-------------------------------------------------------");
        System.out.println("------------------- Batalha Naval ---------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("---------------------Bem vindo-------------------------");
    }

    /**
     * Sorteia o jogador
     * @return retorna 0 ou 1
     */
    public int sorteiaJogador(){
        Random random = new Random();
        return random.nextInt(2);
    }

    /**
     * Método que pergunta se o jogador quer jogar um novo jogo
     * @return retorna true se quer jogar novo jogo e false se não quer
     */
    public boolean perguntaNovoJogo() {
        String leu;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Gostaria de jogar novamente? (s/n)");
            leu = in.nextLine();
            if (leu.equalsIgnoreCase("s")){
                return true;
            } else if(leu.equalsIgnoreCase("n")){
                System.out.println("Fim de jogo");
                return false;
            }
        } while (!leu.equalsIgnoreCase("s") || !leu.equalsIgnoreCase("n"));
        return false;
    }
}
