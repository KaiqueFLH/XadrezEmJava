import java.util.ArrayList;

public class Rei extends Peca {
    private boolean primMov;

    public Rei(String cor){
        super(cor);
        this.icone = gerarIcone();
    }

    public char gerarIcone() {
        if (this.getCor().equals("Branco")) {
            return '♔';
        }
        return '♚';
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        Posicao posicaoAtual = this.getPosicao();

        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);

            if (indice == posicaoNoTabuleiro - 9 ||
                    indice == posicaoNoTabuleiro - 8 ||
                    indice == posicaoNoTabuleiro - 7 ||
                    indice == posicaoNoTabuleiro - 1 ||
                    indice == posicaoNoTabuleiro + 1 ||
                    indice == posicaoNoTabuleiro + 7 ||
                    indice == posicaoNoTabuleiro + 8 ||
                    indice == posicaoNoTabuleiro + 9) {

                //Coluna H.
                if (validaExtremidade(posicaoNoTabuleiro + 1) && !(
                        indice == posicaoNoTabuleiro - 7 ||
                                indice == posicaoNoTabuleiro + 1 ||
                                indice == posicaoNoTabuleiro + 9)) {
                    verificaPeca(posicao, possiveisMovimentos);

                }
                //Coluna Da A.
                else if (validaExtremidade(posicaoNoTabuleiro) && !(
                        indice == posicaoNoTabuleiro -9 ||
                                indice == posicaoNoTabuleiro - 1 ||
                                indice == posicaoNoTabuleiro +7)) {
                    verificaPeca(posicao, possiveisMovimentos);
                }
            }
        }


        return possiveisMovimentos;
    }

}
