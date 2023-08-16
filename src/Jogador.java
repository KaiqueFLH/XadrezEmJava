import java.util.ArrayList;

public class Jogador {
    private ArrayList<Peca> pecas ;
    private String nome;
    private String cor;
    private double pontos;
    private String senha;

    public Jogador(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.pecas = new ArrayList<>();
    }

    public boolean moverPeca(Peca peca, Posicao posicao, Tabuleiro tabuleiro, Jogador adversario, ArrayList<Posicao> movimentos) {

        Peca pecaAdversaria = posicao.getPeca();
        boolean valida = peca.mover(tabuleiro, posicao, movimentos);
        if (pecaAdversaria != null && valida) {
            adversario.pecas.remove(pecaAdversaria);
        }
        return valida;
    }


    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public boolean proporEmpate(Jogador jogador) {
        return true;
    }

    public void desistir() {

    }

    public void setCor(String cor, Tabuleiro tabuleiro) {
        this.cor = cor;
        for (Posicao posicao : tabuleiro.getPosicoes()) {
            if (posicao.getPeca() != null && posicao.getPeca().getCor().equals(this.cor)) {
                this.pecas.add(posicao.getPeca());
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
