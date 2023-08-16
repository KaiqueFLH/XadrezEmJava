import java.util.ArrayList;

public class Peao extends Peca {
    private boolean primMov = true;

    public Peao(String cor, Posicao posicao, int posicaoN) {
        super(cor, posicao, posicaoN);
        this.icone = gerarIcone();
    }

    public String gerarIcone() {
        if (this.getCor().equals("Branco")) {
            return "( ♙ )";
        }
        return "( ♟ )";
    }


    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();
        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> posicoesTabuleiro = tabuleiro.getPosicoes();


        if (this.getCor().equals("Preto")) {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 8).getPeca() == null) {
                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 8));
                if (this.primMov) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro + 16).getPeca() == null) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 16));
                    }
                }
            }
            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca() != null
                    && !validaExtremidade(posicaoNoTabuleiro + 1)) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 9).getPeca().getCor().equals("Branco")) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 9));
                }
            }
            if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca() != null
                    && !validaExtremidade(posicaoNoTabuleiro)) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro + 7).getPeca().getCor().equals("Branco")) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro + 7));
                }
            }
        } else {
            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 8).getPeca() == null) {
                possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 8));
                if (this.primMov) {
                    if (posicoesTabuleiro.get(posicaoNoTabuleiro - 16).getPeca() == null) {
                        possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 16));
                    }
                }
            }
            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca() != null
                    && !validaExtremidade(posicaoNoTabuleiro)) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 9).getPeca().getCor().equals("Preto")) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 9));
                }
            }
            if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca() != null
                    && !validaExtremidade(posicaoNoTabuleiro + 1)) {
                if (posicoesTabuleiro.get(posicaoNoTabuleiro - 7).getPeca().getCor().equals("Preto")) {
                    possiveisMovimentos.add(posicoesTabuleiro.get(posicaoNoTabuleiro - 7));
                }
            }
        }
        return possiveisMovimentos;
    }


    public void setPrimMov(boolean primMov) {
        this.primMov = primMov;
    }
}
