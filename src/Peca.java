import java.util.ArrayList;

public abstract class Peca {
    private String cor;
    private Posicao posicao;

    public Peca(String cor){
        this.cor = cor;
    }

    public void mover(Tabuleiro tabuleiro, Posicao posicao) {
        ArrayList<Posicao> possiveisPosicoes = possiveisMovimentos(tabuleiro);

        for (Posicao posicaoPossivel : possiveisPosicoes) {
            if (posicaoPossivel == posicao) {
                //Atribuindo a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                //Removendo a peça da posição anterior
                this.posicao.setPeca(null);
                //Trocando a posição atual da peça
                this.posicao = posicao;
                break;
            }
        }
    }

    public boolean verificaPeca(Posicao posicao, ArrayList<Posicao> possiveisMovimentos) {
        if (posicao.getPeca() == null) {
            possiveisMovimentos.add(posicao);
            return false;
        } else {
            if (!posicao.getPeca().getCor().equals(this.getCor())) {
                possiveisMovimentos.add(posicao);
            }
            return true;
        }
    }

    public boolean validaExtremidade(int posicaoNoTabuleiro){
        return (posicaoNoTabuleiro) % 8 ==0;
    }

    public abstract ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);

    public Posicao getPosicao() {
        return posicao;
    }

    public String getCor() {
        return cor;
    }

    //public abstract char icone();




}
