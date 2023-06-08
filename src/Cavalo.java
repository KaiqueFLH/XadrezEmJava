import java.util.ArrayList;

public class Cavalo extends Peca {
    @Override
    public ArrayList<Posicao> possiveisMovimentos(Tabuleiro tabuleiro) {
        ArrayList<Posicao> possiveisMovimentos = new ArrayList<>();

        Posicao posicaoAtual = this.getPosicao();
        int posicaoNoTabuleiro = tabuleiro.getPosicoes().indexOf(posicaoAtual);

        if (posicaoNoTabuleiro % 8 == 0) {
            if (posicaoNoTabuleiro > 8 && posicaoNoTabuleiro < 48) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 15));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 6));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 10));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 17));
            }
            if (posicaoNoTabuleiro == 0) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 10));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro + 17));
            } else if (posicaoNoTabuleiro == 56) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 15));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 6));
            } else if (posicaoNoTabuleiro == 8) {
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 17));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 17));
                possiveisMovimentos.add(tabuleiro.getPosicoes().get(posicaoNoTabuleiro - 17));
            }
        }

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
                if ((posicaoNoTabuleiro + 1) % 8 == 0 && !(
                        indice == posicaoNoTabuleiro - 15 ||
                        indice == posicaoNoTabuleiro - 6 ||
                        indice == posicaoNoTabuleiro + 10 ||
                        indice == posicaoNoTabuleiro + 17)) {
                    verificaPeca(posicao,possiveisMovimentos);

                }
                //Coluna Da A.
                else if ((posicaoNoTabuleiro) % 8 == 0 && !(
                        indice == posicaoNoTabuleiro - 17 ||
                        indice == posicaoNoTabuleiro - 10 ||
                        indice == posicaoNoTabuleiro + 6 ||
                        indice == posicaoNoTabuleiro + 15)) {
                    verificaPeca(posicao,possiveisMovimentos);
                }
                //Coluna B
                else if((posicaoNoTabuleiro-1)%8==0 && !(
                        indice == posicaoNoTabuleiro - 10 ||
                        indice == posicaoNoTabuleiro +6)){
                    verificaPeca(posicao,possiveisMovimentos);
                }
                //Coluna G
                else if((posicaoNoTabuleiro+2)%8 ==0 && !(
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
