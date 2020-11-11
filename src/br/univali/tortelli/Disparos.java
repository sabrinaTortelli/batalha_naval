package br.univali.tortelli;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Disparos {
    /**
     * Método que dispara os tiros
     * @param jogadores lista dos jogadores
     * @param num numero sorteado que indica qual jogador está na vez
     * @return retorna true se algum dos jogadores já ganhou o jogo e false se não ganhou
     */
    public boolean dispara(ArrayList<Jogador> jogadores, int num) {
        boolean acertou;
        boolean ganhou;
        if(num == 0){
            do{
                acertou = disparaJogador(jogadores);
                jogadores.get(0).setDisparos();
                jogadores.get(0).getTabuleiros().mostraTabNaviosJog(jogadores);
                jogadores.get(0).getTabuleiros().mostraTabChutes(jogadores);
                ganhou = ganhou();
                if(ganhou){
                    return ganhou;
                }
            } while(acertou);
        } else{
            do {
                acertou = disparaComputador(jogadores);
                jogadores.get(1).setDisparos();
                jogadores.get(0).getTabuleiros().mostraTabNaviosJog(jogadores);
                jogadores.get(0).getTabuleiros().mostraTabChutes(jogadores);
                ganhou = ganhou();
                if(ganhou){
                    return ganhou;
                }
            } while (acertou);
        }
        return false;
    }

    /**
     * Método dos disparos do computador - sorteia aleatoriamente
     * @param jogadores lista dos jogadores
     * @return retorna true se acertou e false se não acertou
     */
    private boolean disparaComputador(ArrayList<Jogador> jogadores) {
        Random random = new Random();
        int linha = random.nextInt(10);
        int coluna = random.nextInt(10);
        if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("-")) {
            jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "O";
            System.out.println("O adversario errou o chute! Perde a vez");
            return false;
        } else {
            if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("S")) {
                jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                System.out.println("O adversario acertou um míssil no Submarino!");
                System.out.println("O adversario afundou o Submarino!");
                Navios.SUBMARINO.setAcertosComp();
                jogadores.get(1).setDisparosCertos();
                return true;
            }
            if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("C")) {
                jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.CRUZADOR.setAcertosComp();
                System.out.println("O adversario acertou um míssil no Cruzador!");
                if (Navios.CRUZADOR.getAcertosComp() == 0) {
                    System.out.println("O adversario afundou o Cruzador!");
                }
                jogadores.get(1).setDisparosCertos();
                return true;
            }
            if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("H")) {
                jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.HIDROAVIAO.setAcertosComp();
                System.out.println("O adversario acertou um míssil no Hidroaviao!");
                if (Navios.HIDROAVIAO.getAcertosComp() == 0) {
                    System.out.println("O adversario afundou o Hidroaviao!");
                }
                jogadores.get(1).setDisparosCertos();
                return true;
            }
            if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("E")) {
                jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.ENCOURACADO.setAcertosComp();
                System.out.println("O adversario acertou um míssil no Encouracado!");
                if (Navios.ENCOURACADO.getAcertosComp() == 0) {
                    System.out.println("O adversario afundou o Encouracado!");
                }
                jogadores.get(1).setDisparosCertos();
                return true;
            }
            if (jogadores.get(0).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("P")) {
                jogadores.get(0).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.PORTAAVIOES.setAcertosComp();
                System.out.println("O adversario acertou um míssil no Porta-avioes!");
                if (Navios.PORTAAVIOES.getAcertosComp() == 0) {
                    System.out.println("O adversario afundou o Porta-avioes!");
                }
                jogadores.get(1).setDisparosCertos();
                return true;
            }
        }
        return false;
    }

    /**
     * Método que dispara os tiros do jogador
     * @param jogadores lista de jogadores
     * @return retorna true se acertou e false se não acertou
     */
    private boolean disparaJogador(ArrayList<Jogador> jogadores) {
        int linha = 0;
        int coluna = 0;
        Scanner in = new Scanner(System.in);
        try {
            do {
                System.out.println("Digite a linha que deseja chutar:");
                linha = in.nextInt();
            } while (linha < 0 || linha >= 10);
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        }
        try {
            do {
                System.out.println("Digite a coluna que deseja chutar:");
                coluna = in.nextInt();
            } while (coluna < 0 || coluna >= 10);
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        }
        if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("-")) {
            jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "O";
            System.out.println("Você errou o chute! Perde a vez!");
            return false;
        } else {
            if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("S")) {
                jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                System.out.println("Voce acertou um míssil no Submarino!");
                System.out.println("Voce afundou o Submarino!");
                Navios.SUBMARINO.setAcertosJog();
                jogadores.get(0).setDisparosCertos();
                return true;
            }
            if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("C")) {
                jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.CRUZADOR.setAcertosJog();
                System.out.println("Voce acertou um míssil no Cruzador!");
                if (Navios.CRUZADOR.getAcertosJog() == 0) {
                    System.out.println("Voce afundou o Cruzador!");
                }
                jogadores.get(0).setDisparosCertos();
                return true;
            }
            if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("H")) {
                jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.HIDROAVIAO.setAcertosJog();
                System.out.println("Voce acertou um míssil no Hidroaviao!");
                if (Navios.HIDROAVIAO.getAcertosJog() == 0) {
                    System.out.println("Voce afundou o Hidroaviao!");
                }
                jogadores.get(0).setDisparosCertos();
                return true;
            }
            if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("E")) {
                jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.ENCOURACADO.setAcertosJog();
                System.out.println("Voce acertou um míssil no Encouracado!");
                if (Navios.ENCOURACADO.getAcertosJog() == 0) {
                    System.out.println("Voce afundou o Encouracado!");
                }
                jogadores.get(0).setDisparosCertos();
                return true;
            }
            if (jogadores.get(1).getTabuleiros().getTabuleiroNavios()[linha][coluna].equals("P")) {
                jogadores.get(1).getTabuleiros().getTabuleiroChutes()[linha][coluna] = "X";
                Navios.PORTAAVIOES.setAcertosJog();
                System.out.println("Voce acertou um míssil no Porta-avioes!");
                if (Navios.PORTAAVIOES.getAcertosJog() == 0) {
                    System.out.println("Voce afundou o Porta-avioes!");
                }
                jogadores.get(0).setDisparosCertos();
                return true;
            }
        }
        return false;
    }

    /**
     * Método que verifica se algum jogador ganhou
     * @return retorna true se ganhou e false se não ganhou
     */
    public boolean ganhou() {
        if(Navios.SUBMARINO.getAcertosJog() == 0 &&
                    Navios.CRUZADOR.getAcertosJog() <= 0 &&
                    Navios.HIDROAVIAO.getAcertosJog() <= 0 &&
                    Navios.ENCOURACADO.getAcertosJog() <= 0 &&
                    Navios.PORTAAVIOES.getAcertosJog() <= 0) {
            return true;
        }
        if(Navios.SUBMARINO.getAcertosComp() <= 0 &&
                    Navios.CRUZADOR.getAcertosComp() <= 0 &&
                    Navios.HIDROAVIAO.getAcertosComp() <= 0 &&
                    Navios.ENCOURACADO.getAcertosComp() <= 0 &&
                    Navios.PORTAAVIOES.getAcertosComp() <= 0){
            return true;
        }
        return false;

    }

    /**
     * Método que mostra os resultados dos jogadores
     * @param jogadores lista de jogadores
     */
    public void resultado(ArrayList<Jogador> jogadores) {
        System.out.println("Resultados:");
        if(jogadores.get(0).getDisparosCertos()> jogadores.get(1).getDisparosCertos()){
            System.out.println("Você foi o vencedor!! Parabéns!!");
        } else{
            System.out.println("Você perdeu!! Tente outra vez!!");
        }
        for(int i=0; i< jogadores.size(); i++){
            System.out.println("Total de Disparos do " + jogadores.get(i).getNome() + ": " + jogadores.get(i).getDisparos());
            System.out.println("Total de Disparos Certos do " + jogadores.get(i).getNome() + ": " + jogadores.get(i).getDisparosCertos());
        }
    }
}
