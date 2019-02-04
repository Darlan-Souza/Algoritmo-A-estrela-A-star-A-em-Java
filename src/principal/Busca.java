package principal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Darlan
 */
public class Busca extends JFrame {

    private List<No> caminho = new ArrayList<>();
    private PriorityQueue<No> borda = new PriorityQueue<>();
    private int matriz[][];
    private Posicao posicaofinal;
    private Posicao posicaoinicial;
    private int numlinhas;
    private int numcolunas;

    public Busca() {
    }

    public Busca(int[][] matriz, Posicao posicaofinal, Posicao posicaoinicial) {
        this.matriz = matriz;
        this.posicaofinal = posicaofinal;
        this.posicaoinicial = posicaoinicial;
    }

    public List<No> getCaminho() {
        return caminho;
    }

    public void setCaminho(List<No> caminho) {
        this.caminho = caminho;
    }

    public PriorityQueue<No> getBorda() {
        return borda;
    }

    public void setBorda(PriorityQueue<No> borda) {
        this.borda = borda;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public Posicao getPosicaofinal() {
        return posicaofinal;
    }

    public void setPosicaofinal(Posicao posicaofinal) {
        this.posicaofinal = posicaofinal;
    }

    public Posicao getPosicaoinicial() {
        return posicaoinicial;
    }

    public void setPosicaoinicial(Posicao posicaoinicial) {
        this.posicaoinicial = posicaoinicial;
    }

    public int getNumlinhas() {
        return numlinhas;
    }

    public void setNumlinhas(int numlinhas) {
        this.numlinhas = numlinhas;
    }

    public int getNumcolunas() {
        return numcolunas;
    }

    public void setNumcolunas(int numcolunas) {
        this.numcolunas = numcolunas;
    }

    public int Manhatan(Posicao atual) {
        int distancia = Math.abs(this.getPosicaofinal().getLinha() - atual.getLinha()) + Math.abs(this.getPosicaofinal().getColuna() - atual.getColuna());
        return distancia;
    }

    public void sucessor(Posicao atual, No node) {
        int Pai = this.caminho.indexOf(node);
        //int matriz[][] = new int[atual.getLinha()][atual.getColuna()];
        No novo;
        if (atual.getLinha() - 1 >= 0) {
            //Movimenta para Linha acima 
            if (matriz[atual.getLinha() - 1][atual.getColuna()] == 1) {
                novo = new No(Manhatan(new Posicao(atual.getLinha() - 1, atual.getColuna())), node.getCusto() + 1, new Posicao(atual.getLinha() - 1, atual.getColuna()));
                novo.setFuncao();
                novo.setPai(Pai);
                borda.add(novo);
            }
        }

        if (atual.getLinha() + 1 < matriz.length) {
            //Movimenta para Linha abaixo
            if (matriz[atual.getLinha() + 1][atual.getColuna()] == 1) {
                novo = new No(Manhatan(new Posicao(atual.getLinha() + 1, atual.getColuna())), node.getCusto() + 1, new Posicao(atual.getLinha() + 1, atual.getColuna()));
                novo.setFuncao();
                novo.setPai(Pai);
                borda.add(novo);
            }
        }
        if (atual.getColuna() - 1 >= 0) {
            //Movimenta para Coluna esquerda
            if (matriz[atual.getLinha()][atual.getColuna() - 1] == 1) {
                novo = new No(Manhatan(new Posicao(atual.getLinha(), atual.getColuna() - 1)), node.getCusto() + 1, new Posicao(atual.getLinha(), atual.getColuna() - 1));
                novo.setFuncao();
                novo.setPai(Pai);
                borda.add(novo);
            }
        }
        if (atual.getColuna() + 1 < this.matriz[0].length) {
            //Movimenta para Coluna direita
            if (matriz[atual.getLinha()][atual.getColuna() + 1] == 1) {
                novo = new No(Manhatan(new Posicao(atual.getLinha(), atual.getColuna() + 1)), node.getCusto() + 1, new Posicao(atual.getLinha(), atual.getColuna() + 1));
                novo.setFuncao();
                novo.setPai(Pai);
                borda.add(novo);

            }
        }
    }

    public void imprimeCaminho() {
        StringBuilder s = new StringBuilder();
        No novo = caminho.get(caminho.size() - 1);
        List<No> certo = new ArrayList<>();
        certo.add(novo);
        while (novo.getPai() != -1) {
            novo = caminho.get(novo.getPai());
            certo.add(novo);
        }

        DefineInterface pilha = new DefineInterface();
        Posicao[] vetor = pilha.converte(certo);
        DefineInterface nova = new DefineInterface();
        nova.componentes(matriz, numlinhas, numcolunas, vetor);

        //Imprime coordenadas do caminho
        for (int i = 0; i < certo.size(); i++) {
            s.append("->").append(certo.get(i).getPosicao());
        }
        //Imprime a rota percorrida
        System.out.println(s.toString());
    }

    public boolean testaObjetivo(No e) {
        return e.getPosicao().getLinha() == posicaofinal.getLinha() && e.getPosicao().getColuna() == posicaofinal.getColuna();
    }

    public void buscaEstrela() {
        No novo = new No(0, this.posicaoinicial);
        novo.setPai(-1);
        novo.setHeuristica(this.Manhatan(posicaoinicial));
        novo.setFuncao();
        borda.add(novo);
        while (!borda.isEmpty()) {
            novo = borda.remove();
            caminho.add(novo);

            //Mostra os passos da busca na tela
            //System.out.println(novo);
            if (testaObjetivo(novo)) {
                imprimeCaminho();
                return;
            } else {
                sucessor(novo.getPosicao(), novo);
            }
        }
    }

    public void passaMatriz() {
        Scanner ler;
        try {
            ler = new Scanner(new FileReader("mat8.txt"));
            this.numlinhas = ler.nextInt();
            this.numcolunas = ler.nextInt();

            //int numlinhas = ler.nextInt();
            //int numlolunas = ler.nextInt();
            this.posicaoinicial = new Posicao(ler.nextInt(), ler.nextInt());
            this.posicaofinal = new Posicao(ler.nextInt(), ler.nextInt());

            //passo essa matriz na DefineInterface
            this.matriz = new int[numlinhas][numcolunas];

            for (int i = 0; i < this.matriz.length; i++) {
                for (int j = 0; j < this.matriz[i].length; j++) {
                    this.matriz[i][j] = ler.nextInt();
                }
            }
            //Imprime a matriz que é passada
            /*for (int i = 0; i < this.matriz.length; i++) {
                for (int j = 0; j < this.matriz[i].length; j++) {
                    System.out.print(this.matriz[i][j] + " ");
                }
                System.out.println(" ");
            }*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Busca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
