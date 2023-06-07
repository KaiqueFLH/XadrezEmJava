import java.util.ArrayList;

public class Bispo extends Peca{

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();

        int posicaoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        for (int i = (posicaoTabuleiro % 8 == 0 ? 64 : posicaoTabuleiro + 7); i < tabuleiro.getPosicoes().size(); i += 7) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if (i % 8 == 0) {
                break;
            }
        }

        for (int i = ((posicaoTabuleiro + 1) % 8 == 0 ? 64 : posicaoTabuleiro - 7); i >= tabuleiro.getPosicoes().size(); i -= 7) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if ((i + 1) % 8 == 0) {
                break;
            }
        }

        for (int i = (posicaoTabuleiro % 8 == 0 ? 64 : posicaoTabuleiro + 9); i < tabuleiro.getPosicoes().size(); i += 9) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if (i % 8 == 0) {
                break;
            }
        }

        for (int i = ((posicaoTabuleiro + 1) % 8 == 0 ? 64 : posicaoTabuleiro - 9); i >= tabuleiro.getPosicoes().size(); i -= 9) {
            possiveisMovimentos.add(tabuleiro.getPosicoes().get(i));
            if ((i + 1) % 8 == 0) {
                break;
            }
        }

//        for (Posicao posicao : tabuleiro.getPosicoes()) {
//            int cont = tabuleiro.getPosicoes().indexOf(posicao);
//            if (cont - posicaoTabuleiro % 7 == 0) {
//                possiveisMovimentos.add(posicao);
//            }
//            else if (cont - posicaoTabuleiro % 9 == 0) {
//                possiveisMovimentos.add(posicao);
//            }
//
//            if () {
//                break;
//            }
//        }
        return possiveisMovimentos;
    }
}
