import java.util.ArrayList;

public class Cavalo extends Peca {

    public Cavalo(String cor){
        super(cor);
        this.icone = gerarIcone();
    }

    public char gerarIcone() {
        if (this.getCor().equals("Branco")) {
            return '♘';
        }
        return '♞';
    }

    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 17));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 15));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 10));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 6));
//
//
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 6));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 10));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 15));
//        possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 17));

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            int indice = tabuleiro.getPosicoes().indexOf(posicao);
            if (indice == posicaoNoTabuleiro - 17 ||
                    indice == posicaoNoTabuleiro - 15 ||
                    indice == posicaoNoTabuleiro - 10 ||
                    indice == posicaoNoTabuleiro - 6 ||
                    indice == posicaoNoTabuleiro + 6 ||
                    indice == posicaoNoTabuleiro + 10 ||
                    indice == posicaoNoTabuleiro + 15 ||
                    indice == posicaoNoTabuleiro + 17) {
                //Coluna H.
                if (validaExtremidade(posicaoNoTabuleiro+1) && !(
                        indice == posicaoNoTabuleiro - 15 ||
                        indice == posicaoNoTabuleiro - 6 ||
                        indice == posicaoNoTabuleiro + 10 ||
                        indice == posicaoNoTabuleiro + 17)) {
                    verificaPeca(posicao,possiveisMovimentos);

                }
                //Coluna Da A.
                else if (validaExtremidade(posicaoNoTabuleiro) && !(
                        indice == posicaoNoTabuleiro - 17 ||
                        indice == posicaoNoTabuleiro - 10 ||
                        indice == posicaoNoTabuleiro + 6 ||
                        indice == posicaoNoTabuleiro + 15)) {
                    verificaPeca(posicao,possiveisMovimentos);
                }
                //Coluna B
                else if(validaExtremidade(posicaoNoTabuleiro-1) && !(
                        indice == posicaoNoTabuleiro - 10 ||
                        indice == posicaoNoTabuleiro +6)){
                    verificaPeca(posicao,possiveisMovimentos);
                }
                //Coluna G
                else if(validaExtremidade(posicaoNoTabuleiro+2) && !(
                        indice == posicaoNoTabuleiro - 15 ||
                                indice == posicaoNoTabuleiro + 17)){
                    verificaPeca(posicao,possiveisMovimentos);
                }
                //Não faz parte de nenhum canto.
                else{
                    verificaPeca(posicao,possiveisMovimentos);
                }
            }
        }

        return possiveisMovimentos;
    }
}
