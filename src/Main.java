import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.Scanner;

public class Main {
    static Jogador j1 = new Jogador("jorge", "Senh@123");
    static Jogador j2 = new Jogador("Wilson", "wilson");
    static Tabuleiro tabuleiro = new Tabuleiro();
    static Scanner sc = new Scanner(System.in);
    static boolean vitoria = false;


    public static void main(String[] args) {

        j1.setCor("Branco", tabuleiro);
        j2.setCor("Preto", tabuleiro);

        Jogador jogadorAtual = j1;

        geraTabuleiro();
        while (!vitoria) {
            partida(jogadorAtual);
        }
    }

    public static void partida(Jogador jogadorAtual) {
        while (!vitoria) {
            validarVitoria(jogadorAtual);
            // Escolha da peça
            Peca peca = null;
            menuPartida(jogadorAtual);

            int escolhaPeca=sc.nextInt();

            // Peca escolhida
            if (testeContains(jogadorAtual, tabuleiro.getPosicoes().get(escolhaPeca).getPeca())) {
                jogadorAtual = trocaJogador(jogadorAtual);
                peca = tabuleiro.getPosicoes().get(escolhaPeca).getPeca();
            } else {
                System.out.println("Peça inválida");
                continue; // Volta ao início do loop para o jogador selecionar outra peça
            }

            // Escolha da posição para o movimento
            ArrayList<Posicao> posicoes = peca.possiveisMovimentos(tabuleiro);
            geraTabuleiroPossibilidades(posicoes);


            int escolhaPosicao = sc.nextInt();
            if (escolhaPosicao<63 && escolhaPosicao>=0 && posicoes.contains(tabuleiro.getPosicoes().get(escolhaPosicao))) {
                Posicao posicao = tabuleiro.getPosicoes().get(escolhaPosicao);
                // Movimentação da peça escolhida para a posição desejada.
                jogadorAtual.moverPeca(peca, posicao, tabuleiro, j2);
                geraTabuleiro();

            } else {
                jogadorAtual = trocaJogador(jogadorAtual);
                System.out.println("Posição invalida");
            }


        }
    }

    private static Jogador trocaJogador(Jogador jogadorAtual) {
        if (jogadorAtual == j1 || jogadorAtual == null) {
            jogadorAtual = j2;
        } else {
            jogadorAtual = j1;
        }
        return jogadorAtual;
    }

    public static void listarPecasJogador(Jogador jogadorAtual) {
        System.out.println();
        for (Posicao indice : tabuleiro.getPosicoes()) {
            if (jogadorAtual.getPecas().contains(indice.getPeca())) {
                System.out.printf("/" + indice.getPeca().icone + " - " + tabuleiro.getPosicoes().indexOf(indice));
            }
        }
    }

    private static boolean testeContains(Jogador jogador, Peca peca) {
        return jogador.getPecas().contains(peca);
    }

    private static void menuPartida(Jogador jogadorAtual){
        int indice=0;
        do {
            System.out.println("""
                [0] - Escolher Peça.
                [1] - Propor Empate.
                """);
            indice = sc.nextInt();
        }while (indice<0 || indice>1);

        if (indice == 0) {
            listarPecasJogador(jogadorAtual);
        }else {
            proporEmpate(jogadorAtual);
        }
    }

    private static void proporEmpate(Jogador jogadorAtual){
        int indice =0;

        System.out.println("Você Deseja Aceitar o Empate Proposto?");
        System.out.println("""
                [0] - Sim
                [1] - Não
                """);
        indice = sc.nextInt();

        if (indice == 0) {
            System.out.println("Ambos os Jogadores aceitaram o empate. Portanto o jogo será finalizado!");
            System.exit(0);
        }else {
            menuPartida(jogadorAtual);
        }
    }

    private static void geraTabuleiro() {
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null) {
                System.out.printf(posicao.getPeca().icone);
            } else {
                System.out.printf("(- -)");
            }
            if ((tabuleiro.getPosicoes().indexOf(posicao) + 1) % 8 == 0) {
                System.out.printf("\n");

            }

        }
    }

    private static void geraTabuleiroPossibilidades(ArrayList<Posicao> posicoes) {
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null) {
                if (posicoes.contains(posicao)){
                    System.out.printf(posicao.getPeca().icone + tabuleiro.getPosicoes().indexOf(posicao));
                }
                else{
                    System.out.printf(posicao.getPeca().icone);
                }
            } else if (posicoes.contains(posicao)) {
                System.out.printf("( " + tabuleiro.getPosicoes().indexOf(posicao) + " )");
            } else {
                System.out.printf("(- -)");
            }
            if ((tabuleiro.getPosicoes().indexOf(posicao) + 1) % 8 == 0) {
                System.out.printf("\n");

            }

        }
    }

    private static boolean validarVitoria(Jogador adversario) {
        for (Peca peca : adversario.getPecas()) {
            if (peca instanceof Rei) {
                return true;
            }
        }
        return false;
    }
}
