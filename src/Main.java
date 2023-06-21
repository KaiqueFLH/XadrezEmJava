import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Scanner;

public class Main {
    static Tabuleiro tabuleiro = new Tabuleiro();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Jogador j1 = new Jogador("jorge", "Senh@123");
        Jogador j2 = new Jogador("Wilson", "wilson");

        j1.setCor("Branco", tabuleiro);
        j2.setCor("Preto",tabuleiro);

        geraTabuleiro();

        // Escolha da peça
        System.out.println(j1.getPecas());
        Peca peca = null;

        int escolhaPeca = sc.nextInt();
        peca = j1.getPecas().get(escolhaPeca);

        // Escolha da posição para o movimento
        ArrayList <Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
        System.out.println(posicoes);
        int escolhaPosicao = sc.nextInt();
        Posicao posicao = posicoes.get(escolhaPosicao);

        // Movimentação da peça escolhida para a posição desejada.
        j1.moverPeca(peca,posicao,tabuleiro,j2);
        System.out.println(validarVitoria(j2));
    }

     private static void geraTabuleiro(){
        ArrayList<Posicao> posicaoNoTabuleiro = tabuleiro.getPosicoes();
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            System.out.println(posicao.getPeca().icone);
            if ((tabuleiro.getPosicoes().indexOf(posicao)+1)%8 == 0){
                    System.out.println("\n");

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
