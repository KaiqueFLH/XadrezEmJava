import java.util.ArrayList;

public class Bispo extends Peca {

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();

        int posicaoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        for (int i = (posicaoTabuleiro % 8 == 0 ? 64 : posicaoTabuleiro + 7); i < tabuleiro.getPosicoes().size(); i += 7) {
            Posicao posicao = tabuleiro.getPosicoes().get(i);

            this.verificaPeca(posicao, possiveisMovimentos);

            if (i % 8 == 0 ||
                    verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        for (int i = ((posicaoTabuleiro + 1) % 8 == 0 ? 64 : posicaoTabuleiro - 7); i >= tabuleiro.getPosicoes().size(); i -= 7) {
            Posicao posicao = tabuleiro.getPosicoes().get(i);

            this.verificaPeca(posicao, possiveisMovimentos);

            if ((i + 1) % 8 == 0 ||
                    verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        for (int i = (posicaoTabuleiro % 8 == 0 ? 64 : posicaoTabuleiro + 9); i < tabuleiro.getPosicoes().size(); i += 9) {
            Posicao posicao = tabuleiro.getPosicoes().get(i);

            this.verificaPeca(posicao, possiveisMovimentos);

            if (tabuleiro.getPosicoes().get(i).getPeca() == null) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            }

            if (i % 8 == 0 ||
                    verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        for (int i = ((posicaoTabuleiro + 1) % 8 == 0 ? 64 : posicaoTabuleiro - 9); i >= tabuleiro.getPosicoes().size(); i -= 9) {
            Posicao posicao = tabuleiro.getPosicoes().get(i);

            this.verificaPeca(posicao, possiveisMovimentos);

            if ((i + 1) % 8 == 0 ||
                    verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        return possiveisMovimentos;
    }

    @Override
    public String getCor() {
        return super.getCor();
    }
}
