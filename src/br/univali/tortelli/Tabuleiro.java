package br.univali.tortelli;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {

    private int tamanho = 10;
    private String tabuleiroChutes[][];
    private String tabuleiroNavios[][];
    private ArrayList<Navios> navios;

    /**
     * Cria os tabuleiros e preenche com '-'
     */
    public Tabuleiro() {
        this.tabuleiroChutes = new String[tamanho][tamanho];
        this.tabuleiroNavios = new String[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.tabuleiroChutes[i][j] = "-";
                this.tabuleiroNavios[i][j] = "-";
            }
        }
    }

    public String[][] getTabuleiroChutes() {
        return tabuleiroChutes;
    }

    public String[][] getTabuleiroNavios() {
        return tabuleiroNavios;
    }

    /**
     * Adiciona os navios na lista de navios
     * @return retorna lista de navios
     */
    public ArrayList<Navios> adicionaNaviosLista() {
        navios = new ArrayList<>();
        navios.add(Navios.SUBMARINO);
        navios.add(Navios.CRUZADOR);
        navios.add(Navios.HIDROAVIAO);
        navios.add(Navios.ENCOURACADO);
        navios.add(Navios.PORTAAVIOES);
        return navios;
    }

    /**
     * Posiciona navios dependendo do jogador
     * @param jogador jogador ou computador
     */
    public void posicionaNavios(Jogador jogador) {
        ArrayList<Navios> navios = adicionaNaviosLista();
        if (jogador.getTipo() == TipoJogador.JOGADOR) {
            posicionaNaviosJogador(navios);
        } else {
            posicionaNaviosComputador(navios);
        }
    }

    /**
     * Verifica se o navio cabe na posição sorteada ou pedida
     * @param tiros numero de tiros dos navios
     * @param linha linha do tabuleiro
     * @param coluna coluna do tabuleiro
     * @param orientacao orientação dos navios
     * @return retorna true se couber o navio na posição e falso se não couber
     */
    private boolean cabeNavio(int tiros, int linha, int coluna, int orientacao) {
        if (tiros == 3) {
            for (int j = 0; j < tiros; j++) {
                if (j == 0) {
                    if (this.tabuleiroNavios[linha][coluna].equals("-")) {
                        if (orientacao == 0) {
                            coluna++;
                            linha--;
                        } else {
                            linha++;
                            coluna++;
                        }
                    } else {
                        return false;
                    }
                }
                if (j == 1) {
                    if (this.tabuleiroNavios[linha][coluna].equals("-")) {
                        if (orientacao == 0) {
                            coluna++;
                            linha++;
                        } else {
                            linha++;
                            coluna--;
                        }
                    } else {
                        return false;
                    }
                }
                if (j == 2) {
                    if (this.tabuleiroNavios[linha][coluna].equals("-")) {
                        if (orientacao == 0) {
                            return true;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            for (int j = 0; j < tiros; j++) {
                if (this.tabuleiroNavios[linha][coluna].equals("-")) {
                    if (orientacao == 0) {
                        coluna++;
                    } else {
                        linha++;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Posiciona os navios do computador
     * @param navios lista de navios
     */
    private void posicionaNaviosComputador(ArrayList<Navios> navios) {
        Random random = new Random();
        int linha;
        int coluna;
        int orientacao;
        int cont = 10;
        for (int i = 0; i < navios.size(); i++) {
            if(i ==0) {
                orientacao = random.nextInt(2);
                linha = random.nextInt(cont);
                coluna = random.nextInt(cont);
                posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
            }
            if(i == 1 || i== 3 || i == 4) {
                boolean cabe = false;
                while (!cabe) {
                    orientacao = random.nextInt(2);
                    if (orientacao == 0) {
                        linha = random.nextInt(10);
                        coluna = random.nextInt(cont);
                    }
                    else {
                        linha = random.nextInt(cont);
                        coluna = random.nextInt(10);
                    }
                    cabe = cabeNavio(navios.get(i).getTiros(), linha, coluna, orientacao);
                    if (cabe) {
                        posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
                    }
                }
            }
            if(i == 2){
                boolean cabe = false;
                while (!cabe) {
                    orientacao = random.nextInt(2);
                    if(orientacao == 0) {
                        linha = random.nextInt(9) + 1;
                        coluna = random.nextInt(8);
                    }else {
                        linha = random.nextInt(8);
                        coluna = random.nextInt(9);
                    }
                    cabe = cabeNavio(navios.get(i).getTiros(), linha, coluna, orientacao);
                    if(cabe) {
                        posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
                    }
                }
            }
            cont--;
        }
    }

    /**
     * Posiciona os navios do jogador
     * @param navios lista de navios
     */
    private void posicionaNaviosJogador(ArrayList<Navios> navios) {
        int cont = 10;
        for (int i = 0; i < navios.size(); i++) {
            Scanner in = new Scanner(System.in);
            int linha = -1;
            int coluna = -1;
            int orientacao = -1;
            if (i == 0) {
                try {
                    do {
                        System.out.println("Digite a orientacao do navio - 0-Horizontal / 1-Vertical");
                        orientacao = in.nextInt();
                    } while (orientacao < 0 || orientacao > 1);
                    do {
                        System.out.println("Digite a linha que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                        linha = in.nextInt();
                    } while (linha < 0 || linha >= cont);
                    do {
                        System.out.println("Digite a coluna que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                        coluna = in.nextInt();
                    } while (coluna < 0 || coluna >= cont);
                } catch (Exception e) {
                    System.out.println("Digite um número válido!");
                }
                posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
                mostraTabNavios();
            }
            if (i == 1 || i == 3 || i == 4) {
                boolean cabe = false;
                while (!cabe) {
                    try {
                        do {
                            System.out.println("Digite a orientacao do navio - 0-Horizontal / 1-Vertical");
                            orientacao = in.nextInt();
                        } while (orientacao < 0 || orientacao > 1);
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um número válido!");
                    }
                    if (orientacao == 0) {
                        try {
                            do {
                                System.out.println("Digite a linha que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                linha = in.nextInt();
                            } while (linha < 0 || linha >= 10);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                        try {
                            do {
                                System.out.println("Digite a coluna que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                coluna = in.nextInt();
                            } while (coluna < 0 || coluna >= cont);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                    } else {
                        try {
                            do {
                                System.out.println("Digite a linha que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                linha = in.nextInt();
                            } while (linha < 0 || linha >= cont);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                        try {
                            do {
                                System.out.println("Digite a coluna que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                coluna = in.nextInt();
                            } while (coluna < 0 || coluna >= 10);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                    }
                    cabe = cabeNavio(navios.get(i).getTiros(), linha, coluna, orientacao);
                    if(!cabe){
                        System.out.println("Seu navio nao cabe nesta posicao!");
                    }
                    if (cabe) {
                        posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
                        mostraTabNavios();
                    }
                }
            }
            if (i == 2) {
                boolean cabe = false;
                while (!cabe) {
                    try {
                        do {
                            System.out.println("Digite a orientacao do navio - 0-Horizontal / 1-Vertical");
                            orientacao = in.nextInt();
                        } while (orientacao < 0 || orientacao > 1);
                    } catch (Exception e) {
                        System.out.println("Digite um número válido!");
                    }
                    if (orientacao == 0) {
                        try {
                            do {
                                System.out.println("Digite a linha que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                linha = in.nextInt();
                            } while (linha < 1 || linha >= 10);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                        try {
                            do {
                                System.out.println("Digite a coluna que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                coluna = in.nextInt();
                            } while (coluna < 0 || coluna >= cont);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                    } else {
                        try {
                            do {
                                System.out.println("Digite a linha que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                linha = in.nextInt();
                            } while (linha < 0 || linha >= cont);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                        try {
                            do {
                                System.out.println("Digite a coluna que deseja colocar o navio " + navios.get(i).getNome() + " que tem " + navios.get(i).getTiros() + " posicao:");
                                coluna = in.nextInt();
                            } while (coluna < 0 || coluna >= 9);
                        } catch (Exception e) {
                            System.out.println("Digite um número válido!");
                        }
                    }
                    cabe = cabeNavio(navios.get(i).getTiros(), linha, coluna, orientacao);
                    if(!cabe){
                        System.out.println("Seu navio nao cabe nesta posicao!");
                    }
                    if (cabe) {
                        posiciona(navios.get(i).getTiros(), linha, coluna, orientacao);
                        mostraTabNavios();
                    }
                }
            }
            cont--;
        }
    }

    /**
     * Posiciona os navios se couberem nas posicoes
     * @param tiros numero de tiros dos navios
     * @param linha linha do tabuleiro
     * @param coluna coluna do tabuleiro
     * @param orientacao orientação dos navios
     */
    private void posiciona(int tiros, int linha, int coluna, int orientacao) {
        switch (tiros) {
            case 1:
                this.tabuleiroNavios[linha][coluna] = "S";
                break;
            case 2:
                if (orientacao == 0) {
                    for(int i=0; i<tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "C";
                        coluna++;
                    }
                } else {
                    for (int i = 0; i < tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "C";
                        linha++;
                    }
                }
                break;
            case 3:
                for (int j = 0; j < tiros; j++) {
                    if (j == 0) {
                        if (orientacao == 0) {
                            this.tabuleiroNavios[linha][coluna] = "H";
                            coluna++;
                            linha--;
                        } else {
                            this.tabuleiroNavios[linha][coluna] = "H";
                            linha++;
                            coluna++;
                        }
                    }
                    if (j == 1) {
                        if (orientacao == 0) {
                            this.tabuleiroNavios[linha][coluna] = "H";
                            coluna++;
                            linha++;
                        } else {
                            this.tabuleiroNavios[linha][coluna] = "H";
                            linha++;
                            coluna--;
                        }
                    }
                    if (j == 2) {
                        this.tabuleiroNavios[linha][coluna] = "H";
                    }
                }
                break;
            case 4:
                if (orientacao == 0) {
                    for(int i=0; i<tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "E";
                        coluna++;
                    }
                } else {
                    for (int i = 0; i < tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "E";
                        linha++;
                    }
                }
                break;
            case 5:
                if (orientacao == 0) {
                    for(int i=0; i<tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "P";
                        coluna++;
                    }
                } else {
                    for (int i = 0; i < tiros; i++) {
                        this.tabuleiroNavios[linha][coluna] = "P";
                        linha++;
                    }
                }
                break;
        }
    }

    /**
     * Mostra o exemplo do tabuleiro
     */
    public void mostraExemploTabuleiro() {
        System.out.println("--------Exemplo Posições Tabuleiros--------");
        System.out.println("- | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        System.out.println("0 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("1 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("2 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("3 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("4 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("5 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("6 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("7 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("8 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");
        System.out.println("9 | _ | _ | _ | _ | _ | _ | _ | _ | _ | _ |");

    }

    /**
     * Mostra Posições dos navios no tabuleiro quando o jogador escolhe
     */
    public void mostraTabNavios(){
        System.out.println("--------------Posições Navios---------------");
        System.out.println("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < tamanho; j++) {
                System.out.print(" " + this.tabuleiroNavios[i][j] + " |");
            }
            System.out.println();
        }
    }

    /**
     * Mostra as posições finais do tabuleiro do jogador
     * @param jogadores lista dos jogadores
     */
    public void mostraTabNaviosJog(ArrayList<Jogador> jogadores){
        System.out.println("------Posições Navios " + jogadores.get(0).getNome() +"------");
        System.out.println("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(" " + i + " |");
            for (int j = 0; j < tamanho; j++) {
                System.out.print(" " + this.tabuleiroNavios[i][j] + " |");
            }
            System.out.println();
        }
    }

    /**
     * Mostra tabuleiro dos chutes de cada jogador
     * @param jogadores lista dos jogadores
     */
    public void mostraTabChutes(ArrayList<Jogador> jogadores){
        for(int k=0; k< jogadores.size(); k++) {
            if(k ==0){
                System.out.println("------Tabuleiro de Chutes do Jogador " + jogadores.get(1).getNome() + "------");
            } else {
                System.out.println("------Tabuleiro de Chutes do Jogador " + jogadores.get(0).getNome() + "------");
            }
            System.out.println("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
            for (int i = 0; i < tamanho; i++) {
                System.out.print(" " + i + " |");
                for (int j = 0; j < tamanho; j++) {
                    System.out.print(" " + jogadores.get(k).getTabuleiros().tabuleiroChutes[i][j] + " |");
                }
                System.out.println();
            }
        }
    }

}