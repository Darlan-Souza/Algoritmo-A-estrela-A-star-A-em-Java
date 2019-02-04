package principal;

/**
 *
 * @author Darlan
 */
public class Posicao {

    private int linha;
    private int coluna;

    //construtor 
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public Posicao() {
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    //exibe as coordenadas da matriz geradas pela pesquisa
    @Override
    public String toString() {
        return "Posicao{" + "Linha=" + linha + ", Coluna=" + coluna + '}';
    }

}
