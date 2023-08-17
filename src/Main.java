import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Jogador j1 = new Jogador("jorge", "Senh@123");
    static Jogador j2 = new Jogador("Wilson", "wilson");
    static Tabuleiro tabuleiro = new Tabuleiro();
    static Scanner sc = new Scanner(System.in);
    static boolean vitoria = false;
    static Jogador jogadorAtual;
    static Jogador jogadorAdversario;


    public static void main(String[] args) {

        j1.setCor("Branco", tabuleiro);
        j2.setCor("Preto", tabuleiro);

        jogadorAtual = j1;
        jogadorAdversario = j2;
        geraTabuleiro();
        partida();
    }

    public static void partida() {
        while (!vitoria) {



            // Escolha da peça
            Peca peca = null;
            menuPartida();

            int escolhaPeca = sc.nextInt();

            // Peca escolhida
            if (testeContains(jogadorAtual, tabuleiro.getPosicoes().get(escolhaPeca).getPeca())) {
                peca = tabuleiro.getPosicoes().get(escolhaPeca).getPeca();
            } else {
                System.out.println("Peça inválida");
                continue; // Volta ao início do loop para o jogador selecionar outra peça
            }

            // Escolha da posição para o movimento
            ArrayList<Posicao> posicoes = testeAvisaXequeMate(peca, peca.possiveisMovimentos(tabuleiro), jogadorAdversario);
            geraTabuleiroPossibilidades(posicoes);

            //Verifica os possiveis movimentos de cada peça, e se caso a peça não tenha possiveis movimentos ela não é movida.
            if (posicoes.size()==0){
                System.out.println("Voce não pode mover essa peça.");
                //Volta para o início do loop.
                continue;
            }
            else {
                System.out.println("Agora escolha a posição que deseja ir.");
            }

            int escolhaPosicao = sc.nextInt();

            if (escolhaPosicao < 63 && escolhaPosicao >= 0 && posicoes.contains(tabuleiro.getPosicoes().get(escolhaPosicao))) {
                Posicao posicao = tabuleiro.getPosicoes().get(escolhaPosicao);
                // Movimentação da peça escolhida para a posição desejada.

                if (jogadorAtual.moverPeca(peca, posicao, tabuleiro, jogadorAdversario, posicoes)) {
                    geraTabuleiro();
                    testePromocao(tabuleiro);
                    boolean XequeMate = true;
                    for (Peca pecaAdv : jogadorAdversario.getPecas()) {
                        if (testeAvisaXequeMate(pecaAdv, pecaAdv.possiveisMovimentos(tabuleiro), jogadorAtual).size() > 0) {
                            XequeMate = false;

                        }
                    }
                    if (XequeMate) {
                        System.out.println("Xeque mate");
                        System.out.println(jogadorAtual.getNome() + " Venceu!");
                        System.exit(0);
                    }
                    trocaJogador();
                } else {
                    System.out.println("Movimento inválido");
                }

            } else {
                System.out.println("Posição invalida");
            }


        }
    }



    public static ArrayList<Posicao> testeAvisaXequeMate(Peca peca, ArrayList<Posicao> posicoes, Jogador jogadorAdv) {
        Posicao posicaoAtual = peca.getPosicao();
        ArrayList<Posicao> possiveisMovimentosLimpo = new ArrayList<>(posicoes);
        for (Posicao posicaoTeste : posicoes) {
            Peca pecaTemp = posicaoTeste.getPeca();

            //Atribuindo a peça para a nova posição no tabuleiro
            posicaoTeste.setPeca(peca);
            //Removendo a peça da posição anterior
            peca.getPosicao().setPeca(null);
            //Trocando a posição atual da peça
            peca.setPosicao(posicaoTeste);

            for (Peca pecasFor : jogadorAdv.getPecas()) {
                for (Posicao possivelMovimentoXeque : pecasFor.possiveisMovimentos(tabuleiro)) {
                    Peca pecaPosicao = possivelMovimentoXeque.getPeca();
                    if (pecaPosicao != null) {
                        if (pecaPosicao instanceof Rei) {
                            possiveisMovimentosLimpo.remove(posicaoTeste);
                        }
                    }
                }
            }

            posicaoTeste.setPeca(pecaTemp);
            posicaoAtual.setPeca(peca);
            peca.setPosicao(posicaoAtual);
        }
        return possiveisMovimentosLimpo;
    }


    public static void testePromocao(Tabuleiro tabuleiro) {

        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (tabuleiro.getPosicoes().indexOf(posicao) < 8) {
                if (posicao.getPeca() instanceof Peao) {
                    promoverPeao(posicao);
                    return;
                }
            }

            if (tabuleiro.getPosicoes().indexOf(posicao) >= 56){
                if (posicao.getPeca() instanceof Peao) {
                    promoverPeao(posicao);
                    return;
                }
            }
        }

    }

    public static void promoverPeao(Posicao posicao) {
        int indice = 0;
        do {
            System.out.println("""
                    Para qual Peça você deseja fazer a promoção?
                    [0] - Rainha
                    [1] - Torre
                    [2] - Bispo
                    [3] - Cavalo
                    """);
            indice = sc.nextInt();

            switch (indice) {
                case 0:

                    if (posicao.getPeca().getCor().equals("Branco")) {
                        Peca rainha = new Rainha("Branco", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(rainha);
                        rainha.getPosicao().getPeca().setPosicao(null);
                        rainha.getPosicao().setPeca(rainha);
                        rainha.setPosicao(posicao);
                    } else {
                        Peca rainha = new Rainha("Preto", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(rainha);
                        rainha.getPosicao().getPeca().setPosicao(null);
                        rainha.getPosicao().setPeca(rainha);
                        rainha.setPosicao(posicao);
                    }
                    break;
                case 1:

                    if (posicao.getPeca().getCor().equals("Branco")) {
                        Peca torre = new Torre("Branco", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(torre);
                        torre.getPosicao().getPeca().setPosicao(null);
                        torre.getPosicao().setPeca(torre);
                        torre.setPosicao(posicao);
                    } else {
                        Peca torre = new Torre("Preto", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(torre);
                        torre.getPosicao().getPeca().setPosicao(null);
                        torre.getPosicao().setPeca(torre);
                        torre.setPosicao(posicao);
                    }
                    break;
                case 2:

                    if (posicao.getPeca().getCor().equals("Branco")) {
                        Peca bispo = new Bispo("Branco", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(bispo);
                        bispo.getPosicao().getPeca().setPosicao(null);
                        bispo.getPosicao().setPeca(bispo);
                        bispo.setPosicao(posicao);
                    } else {
                        Peca bispo = new Bispo("Preto", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(bispo);
                        bispo.getPosicao().getPeca().setPosicao(null);
                        bispo.getPosicao().setPeca(bispo);
                        bispo.setPosicao(posicao);
                    }
                    break;
                case 3:

                    if (posicao.getPeca().getCor().equals("Branco")) {
                        Peca cavalo = new Cavalo("Branco", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(cavalo);
                        cavalo.getPosicao().getPeca().setPosicao(null);
                        cavalo.getPosicao().setPeca(cavalo);
                        cavalo.setPosicao(posicao);
                    } else {
                        Peca cavalo = new Cavalo("Preto", posicao.getPeca().getPosicao(), posicao.getPeca().posicaoN);
                        jogadorAtual.getPecas().add(cavalo);
                        cavalo.getPosicao().getPeca().setPosicao(null);
                        cavalo.getPosicao().setPeca(cavalo);
                        cavalo.setPosicao(posicao);
                    }
                    break;
            }
        } while (indice < 0 || indice > 3);
    }

    private static void trocaJogador() {
        if (jogadorAtual == j1 || jogadorAtual == null) {
            jogadorAtual = j2;
            jogadorAdversario = j1;
        } else {
            jogadorAtual = j1;
            jogadorAdversario = j2;
        }
    }

    public static void listarPecasJogador() {
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

    private static void menuPartida() {
        int indice = 0;
        do {
            System.out.println("""
                    [0] - Escolher Peça.
                    [1] - Propor Empate.
                    """);
            indice = sc.nextInt();
        } while (indice < 0 || indice > 1);

        if (indice == 0) {
            listarPecasJogador();
        } else {
            proporEmpate();
        }
    }

    private static void proporEmpate() {
        int indice = 0;

        System.out.println("Você Deseja Aceitar o Empate Proposto?");
        System.out.println("""
                [0] - Sim
                [1] - Não
                """);
        indice = sc.nextInt();

        if (indice == 0) {
            System.out.println("Ambos os Jogadores aceitaram o empate. Portanto o jogo será finalizado!");
            System.exit(0);
        } else {
            menuPartida();
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
                if (posicoes.contains(posicao)) {
                    System.out.printf(posicao.getPeca().icone + tabuleiro.getPosicoes().indexOf(posicao));
                } else {
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

}
