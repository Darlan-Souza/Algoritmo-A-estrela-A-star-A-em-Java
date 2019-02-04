package principal;

/**
 *
 * @author Darlan
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DefineInterface extends JFrame {

    public JPanel painelInterface;
    public JButton[][] matriztela;
    public Posicao[] posicao;
    Stack<Posicao> pilha;
    Icon obstaculo = new ImageIcon("C:/Users/Darlan/Pictures/alga1.png");
    Icon ator = new ImageIcon("C:/Users/Darlan/Pictures/peixe.png");

    public DefineInterface() {
        //tamanho da janela
        this.setSize(600, 600);
        //inicia a execução com a tela no centro
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DefineInterface.DISPOSE_ON_CLOSE);
    }

    //converte o caminho do pai em um vetor 
    public Posicao[] converte(List<No> compara) {
        Posicao[] vetor = new Posicao[compara.size()];
        int i = 0;
        for (No no : compara) {
            vetor[i] = no.getPosicao();
            //Mostra as posições passadas para o vetor 
            //System.out.println(vetor[i].getLinha()+ " " + vetor[i].getColuna());
            i++;
        }
        return vetor;
    }

    //Insere os valores do vetor em uma pilha
    public static Stack<Posicao> preencherPilhaPosicao(Posicao[] vetorposicao) {
        Stack<Posicao> pilha = new Stack<>();
        for (Posicao vetPosicao : vetorposicao) {
            pilha.push(vetPosicao);
        }
        return pilha;
    }

    public void componentes(int matriz[][], int numlinha, int numcoluna, Posicao[] vetorcaminho) {

        Posicao itemAtual;

        this.pilha = preencherPilhaPosicao(vetorcaminho);
        this.setLayout(new BorderLayout());
        //matriztela vai ser utilizada para preencher a tela 
        matriztela = new JButton[numlinha][numcoluna];
        painelInterface = new JPanel();
        //O GridLayout tem a funcionalidade de organizar a matriz, dado as linhas e colunas
        painelInterface.setLayout(new GridLayout(numlinha, numcoluna));

        while (!pilha.empty()) {
            itemAtual = pilha.pop();
            for (int i = 0; i < numlinha; i++) {
                for (int j = 0; j < numcoluna; j++) {
                    //É instanciado um botão com as posições   
                    matriztela[i][j] = new JButton("");
                    if ((itemAtual.getLinha() == i) && (itemAtual.getColuna() == j)) {
                        //Compara se a posição atual agente é a mesma dos indices
                        //O fundo é colorido
                        matriztela[i][j].setBackground(Color.blue);
                        //É adicionado um icone para identificar a posição agente
                        matriztela[i][j].setIcon(ator);
                        //É adicionado a posiçao do agente no painel 
                        painelInterface.add(matriztela[i][j]);
                    } else {
                        if (matriz[i][j] == 0) {
                            //Caso a posição i:j na matriz seje muro
                            matriztela[i][j].setBackground(Color.blue);
                            //É adicionado um icone para identificar a posição do muro
                            matriztela[i][j].setIcon(obstaculo);
                            painelInterface.add(matriztela[i][j]);
                        } else {
                            //Caso a posição i:j seja estrada
                            //O fundo é colorido
                            matriztela[i][j].setBackground(Color.blue);
                            painelInterface.add(matriztela[i][j]);
                        }
                    }
                }
            }
            //É adicionado o painel no JFrame
            this.add(painelInterface, BorderLayout.CENTER);
            //Seta Jframe para ser exibida
            this.setVisible(true);

            try {
                Thread.currentThread().sleep(400);
            } catch (InterruptedException ex) {
                Logger.getLogger(DefineInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Remove tudo que foi adicionado no painel, isso quer dizer que a matrizBotoes foi removida
            painelInterface.removeAll();
            if (pilha.empty()) {
                JOptionPane.showMessageDialog(rootPane, "O peixe chegou!");
            }
        }
    }

}
