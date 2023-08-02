public class Posicao {
    private Peca peca;



    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }


    public String mostraPossiveis(Tabuleiro tabuleiro) {
        return "As casas das posições possiveis são:{" +
                tabuleiro.getPosicoes().indexOf(this) +
                "}";
    }
}
