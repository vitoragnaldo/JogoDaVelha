import java.util.Scanner;

public class JogoDaVelha {
    private static char[][] tabuleiro = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private static char jogadorAtual = 'X';
    private static boolean jogoEmAndamento = true;

    // Exibe o tabuleiro
    public static void exibirTabuleiro() {
        System.out.println("Tabuleiro:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            if (i < 2) {
                System.out.println("---+---+---");
            }
        }
    }

    // Verifica se alguém venceu
    public static boolean verificarVitoria() {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) || // Linha
                    (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual)) { // Coluna
                return true;
            }
        }

        // Verifica as diagonais
        if ((tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) ||
                (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual)) {
            return true;
        }

        return false;
    }

    // Verifica se o jogo terminou empatado
    public static boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false; // Se houver uma casa vazia, o jogo não acabou
                }
            }
        }
        return true; // Se não houver casas vazias, é empate
    }

    // Alterna o jogador
    public static void alternarJogador() {
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    // Realiza uma jogada
    public static boolean realizarJogada(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ') {
            System.out.println("Jogada inválida. Tente novamente.");
            return false;
        }
        tabuleiro[linha][coluna] = jogadorAtual;
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (jogoEmAndamento) {
            exibirTabuleiro();
            System.out.println("Vez do jogador " + jogadorAtual);
            System.out.print("Digite a linha (0, 1, 2): ");
            int linha = scanner.nextInt();
            System.out.print("Digite a coluna (0, 1, 2): ");
            int coluna = scanner.nextInt();

            if (realizarJogada(linha, coluna)) {
                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("Parabéns, jogador " + jogadorAtual + " venceu!");
                    jogoEmAndamento = false;
                } else if (verificarEmpate()) {
                    exibirTabuleiro();
                    System.out.println("Empate! O jogo terminou sem vencedor.");
                    jogoEmAndamento = false;
                } else {
                    alternarJogador();
                }
            }
        }

        scanner.close();
    }
}
