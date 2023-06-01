import java.util.ArrayList;

public abstract class Peca {
    private String cor;
    private Posicao posicao;

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

    public abstract ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro);

    //public abstract char icone();




}
