import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class Torre extends Peca {
    private boolean primMov;

    public Torre(String cor){
        super(cor);
        this.icone = gerarIcone();
    }

    public char gerarIcone() {
        if (this.getCor().equals("Branco")) {
            return '♖';
        }
        return '♜';
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();

        int posicaoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();



        for (int i = posicaoTabuleiro+8; i < tabuleiro.getPosicoes().size(); i += 8) {

            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        for (int i = posicaoTabuleiro-8; i >= 0; i -= 8) {
            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }
        }

        for (int i = (validaExtremidade(posicaoTabuleiro+1)? 64 : posicaoTabuleiro + 1); i < tabuleiro.getPosicoes().size(); i ++) {

            if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)||validaExtremidade(i+1)){
                break;
            }
        }

        for (int i = (validaExtremidade(posicaoTabuleiro) ? -1 : posicaoTabuleiro - 1); i >= 0; i --) {
            Posicao posicao = tabuleiro.getPosicoes().get(i);

            this.verificaPeca(posicao, possiveisMovimentos);

            if (validaExtremidade(i)||
                    verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos)) {
                break;
            }

        }

        return possiveisMovimentos;
    }

}
