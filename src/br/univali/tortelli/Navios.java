package br.univali.tortelli;

public enum Navios {

    SUBMARINO(1, "Submarino", 1, 1),
    CRUZADOR(2, "Cruzador", 2, 2),
    HIDROAVIAO(3, "Hidroaviao", 3, 3),
    ENCOURACADO(4, "Encouracado", 4, 4),
    PORTAAVIOES(5, "Porta-avioes", 5, 5);

    private int tiros;
    private String nome;
    private int acertosJog;
    private int acertosComp;

    Navios(int tiros, String nome, int acertosJog, int acertosComp){
        this.tiros = tiros;
        this.nome = nome;
        this.acertosJog = acertosJog;
        this.acertosComp = acertosComp;

    }

    public int getTiros() {
        return tiros;
    }

    public String getNome() {
        return nome;
    }

    public int getAcertosJog() {
        return acertosJog;
    }

    public void setAcertosJog() {
        this.acertosJog--;
    }

    public int getAcertosComp() {
        return acertosComp;
    }

    public void setAcertosComp() {
        this.acertosComp--;
    }
}
