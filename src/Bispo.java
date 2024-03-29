import java.util.ArrayList;

public class Bispo extends Peca {

    public Bispo(String cor, Posicao posicao,int posicaoN){
        super(cor,posicao,posicaoN);
        this.icone = gerarIcone();
    }

    public String gerarIcone() {
        if (this.getCor().equals("Branco")) {
            return "( ♗ )";
        }
        return "( ♝ )";
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();

        int posicaoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        for (int i = (validaExtremidade(posicaoTabuleiro) ? 64 : posicaoTabuleiro + 7); i < tabuleiro.getPosicoes().size(); i += 7) {
            if (i<64){
                Posicao posicao = tabuleiro.getPosicoes().get(i);

                this.verificaPeca(posicao, possiveisMovimentos);

                if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i)) {
                    break;
                }
            }
        }

        for (int i = (validaExtremidade(posicaoTabuleiro+1) ? -1 : posicaoTabuleiro - 7); i >= 0; i -= 7) {
            if (i<64){
                Posicao posicao = tabuleiro.getPosicoes().get(i);

                this.verificaPeca(posicao, possiveisMovimentos);

                if (!verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) && !validaExtremidade(i + 1)) {
                    continue;
                }
                break;
            }
        }

        for (int i = (validaExtremidade(posicaoTabuleiro+1)? 64 : posicaoTabuleiro + 9); i < tabuleiro.getPosicoes().size(); i += 9) {
            if (i<64){
                Posicao posicao = tabuleiro.getPosicoes().get(i);

                this.verificaPeca(posicao, possiveisMovimentos);

                if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i+1)) {
                    break;
                }
            }
        }

        for (int i = (validaExtremidade(posicaoTabuleiro) ? 64 : posicaoTabuleiro - 9); i >= 0; i -= 9) {
            if (i<64){
                Posicao posicao = tabuleiro.getPosicoes().get(i);

                this.verificaPeca(posicao, possiveisMovimentos);

                if (verificaPeca(tabuleiro.getPosicoes().get(i), possiveisMovimentos) || validaExtremidade(i)) {
                    break;
                }
            }
        }

        return possiveisMovimentos;
    }

    @Override
    public String getCor() {
        return super.getCor();
    }
}
