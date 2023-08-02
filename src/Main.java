import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Scanner;

public class Main {
    static Tabuleiro tabuleiro = new Tabuleiro();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Jogador j1 = new Jogador("jorge", "Senh@123");
        Jogador j2 = new Jogador("Wilson", "wilson");

        j1.setCor("Branco", tabuleiro);
        j2.setCor("Preto",tabuleiro);

        geraTabuleiro();
        partida(j1,j2);
    }

    public static void partida(Jogador j1,Jogador j2){

        // Escolha da peça
        System.out.println(j1.getPecas());
        Peca peca = null;
        int escolhaPeca = sc.nextInt();
        // Peca escolhida
        if (testeContains(j1,tabuleiro.getPosicoes().get(escolhaPeca).getPeca())){
            peca = tabuleiro.getPosicoes().get(escolhaPeca).getPeca();
        }else {
            System.out.println("Peça invalida");
            partida(j1,j2);
        }

        // Escolha da posição para o movimento
        ArrayList <Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
        for (Posicao posicao : posicoes) {
            System.out.println(posicao.mostraPossiveis(tabuleiro));
        }
        int escolhaPosicao = sc.nextInt();
        Posicao posicao = posicoes.get(escolhaPosicao);

        // Movimentação da peça escolhida para a posição desejada.
        j1.moverPeca(peca,posicao,tabuleiro,j2);
        geraTabuleiro();

    }

    private static boolean testeContains(Jogador jogador, Peca peca) {
        return jogador.getPecas().contains(peca);
    }


    private static void geraTabuleiro(){
        ArrayList<Posicao> posicaoNoTabuleiro = tabuleiro.getPosicoes();
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null) {
                System.out.printf(posicao.getPeca().icone);
            }else {
                System.out.printf("(- -)");
            }
            if ((tabuleiro.getPosicoes().indexOf(posicao)+1)%8 == 0){
                    System.out.printf("\n");

            }

        }
    }

    private static boolean validarVitoria(Jogador adversario){
        for (Peca peca: adversario.getPecas()) {
            if (peca instanceof Rei){
                return false;
            }
        }
        return true;
    }
}
