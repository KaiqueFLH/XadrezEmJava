import java.util.ArrayList;

public abstract class Peca {
    private String cor;
    private Posicao posicao;
    public String icone;
    public int posicaoN;
    Tabuleiro tabuleiro;

    public Peca(String cor, Posicao posicao,int posicaoN){
        this.cor = cor;
        this.posicao = posicao;
        this.posicaoN = posicaoN;

    }

    public boolean mover(Tabuleiro tabuleiro, Posicao posicao) {
        // Se eu retirar a ArrayList de possiveisPosicoes que chama o método abstrato possiveisMovimentos() é só implementar o this.primMov = false no Peao.

        ArrayList<Posicao> possiveisPosicoes = possiveisMovimentos(tabuleiro);

        if (this instanceof Peao){
            ((Peao) this).setPrimMov(false);
        }

        for (Posicao posicaoPossivel : possiveisPosicoes) {
            if (posicaoPossivel == posicao) {
                //Atribuindo a peça para a nova posição no tabuleiro
                posicao.setPeca(this);
                //Removendo a peça da posição anterior
                this.posicao.setPeca(null);
                //Trocando a posição atual da peça
                this.posicao = posicao;
                return true;
            }
        }
        return false;
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


    @Override
    public String toString() {
        return "Peca{" +
                "cor='" + cor + '\'' +
                ", posicao=" + posicao +
                ", icone='" + icone + '\'' +
                ", posicaoN=" + posicaoN +
                ", tabuleiro=" + tabuleiro +
                '}';
    }
}
