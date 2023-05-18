import java.util.Arrays;
import java.util.Random;

public class BatalhaNaval {

    static final int VAZIO = 0;
    static final int NAVIO = 1;
    static final int ERROU_TIRO = 2;
    static final int ACERTOU_TIRO = 3;

    static final int POSICAO_X = 0;
    static final int POSICAO_Y = 1;
    static final int tamanhoX = 5;
    static final int tamanhoY = 5;

    static String nomeJogador1 = "A";
    static String nomeJogador2 = "B";
    static int quantidadeDeNavios = 3 ;

    static int naviosJogador1 = quantidadeDeNavios;
    static int naviosJogador2 = quantidadeDeNavios;

//    public static void main(String[] args) {
//        inserirOsNaviosNosTabuleirosDosJogadores();
 //       boolean jogoAtivo = true;
   //     do{
     //       exibirTabuleirosDosJogadores();
       //     if (acaoDoJogador(1)) {
         //       if (naviosJogador2 <= 0) {
           //         System.out.println(nomeJogador1 + " venceu o jogo!");
               //     break;
             //   }
                // Verifico fim do jogo
       //         acaoDoJogador(2);
                //if (naviosJogador1 <= 0) {
                 //   System.out.println(nomeJogador2 + " venceu o jogo!");
             //       break;
                //}
           // }

        //}while (jogoAtivo);
        //exibirTabuleirosDosJogadores();
        //input.close();
   // }

    public static int[][] retornarNovoTabuleiroComOsNavios() {
        int[][] novoTabuleiro = new int[5][5];
        int quantidadeRestanteDeNavios = quantidadeDeNavios;
        int x= 0, y= 0;
        Random numeroAleatorio = new Random();
        do {
            x = 0;
            y = 0;
            for(int[] linha : novoTabuleiro) {
                for (int coluna : linha) {
                    if (numeroAleatorio.nextInt(100) <= 10) {
                        if(coluna == VAZIO) {
                            novoTabuleiro[x][y] = NAVIO;
                            quantidadeRestanteDeNavios--;
                            break;
                        }
                        if(quantidadeRestanteDeNavios < 0) {
                            break;
                        }
                    }
                    y++;
                }
                y = 0;
                x++;
                if(quantidadeRestanteDeNavios <= 0) {
                    break;
                }
            }
        } while (quantidadeRestanteDeNavios > 0);
        return novoTabuleiro;
    }

    public static void inserirOsNaviosNosTabuleirosDosJogadores() {
        Main.tabuleiroJogador1 = retornarNovoTabuleiroComOsNavios();
        Main.tabuleiroJogador2 = retornarNovoTabuleiroComOsNavios();
    }

    public static boolean validarPosicoesInseridasPeloJogador(int[] posicoes) {
        boolean retorno = true;
        if (posicoes[0] > tamanhoX -1) {
            retorno = false;
            System.out.println("A posicao das letras não pode ser maior que " + (char)(tamanhoX + 64));
        }
        if (posicoes[1] > tamanhoY) {
            retorno = false;
            System.out.println("A posicao numérica não pode ser maior que " + tamanhoY);
        }
        return retorno;
    }

    public static boolean validarTiroDoJogador(String tiroDoJogador) {

        int quantidadeDeNumeros = (tamanhoY > 10) ? 2 : 1;
        String expressaoDeVerificacao = "^[A-Za-z]{1}[0-9]{"
                + quantidadeDeNumeros + "}$";
        return tiroDoJogador.matches(expressaoDeVerificacao);
    }

    public static int[] retornarPosicoesDigitadasPeloJogador(String tiroDoJogador) {
        String tiro = tiroDoJogador.toLowerCase();
        int[] retorno = new int[2];
        retorno[POSICAO_X] = tiro.charAt(0) - 97;
        retorno[POSICAO_Y] = Integer.parseInt(tiro.substring(1)) - 1;
        return retorno;
    }

    public static void inserirValoresDaAcaoNoTabuleiro(int[] posicoes, int numeroDoJogador) {
        if (numeroDoJogador == 1) {
            if (Main.tabuleiroJogador2[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] == NAVIO) {
                Main.tabuleiroJogador2[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ACERTOU_TIRO;
                naviosJogador2--;
                System.out.println("Você acertou um navio!");
            } else {
                Main.tabuleiroJogador2[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ERROU_TIRO;
                System.out.println("Você errou o tiro!");
            }
        } else {
            if (Main.tabuleiroJogador1[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] == NAVIO) {
                Main.tabuleiroJogador1[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ACERTOU_TIRO;
                naviosJogador1--;
                System.out.println("Você acertou um navio!");
            } else {
                Main.tabuleiroJogador1[posicoes[POSICAO_X]][posicoes[POSICAO_Y]] = ERROU_TIRO;
                System.out.println("Você errou o tiro!");
            }
        }
    }

    public static void setNomeJogador1(String nomeJogador1) {
        BatalhaNaval.nomeJogador1 = nomeJogador1;
    }

    public static void setNomeJogador2(String nomeJogador2) {
        BatalhaNaval.nomeJogador2 = nomeJogador2;
    }



    public static void setTabuleiroJogador2(int[][] tabuleiroJogador2) {
        tabuleiroJogador2 = tabuleiroJogador2;
    }
}