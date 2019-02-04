package principal;

/**
 *
 * @author Darlan
 */
public class No implements Comparable {

    private int heuristica;
    private int custo;
    private int funcao;
    private Posicao posicao;
    private int pai;

    //construtor padrão
    public No(int heuristica, int custo, int funcao, Posicao posicao) {
        this.heuristica = heuristica;
        this.custo = custo;
        this.funcao = funcao;
        this.posicao = posicao;
    }

    //construtor da sucessor()
    public No(int heuristica, int custo, Posicao posicao) {
        this.heuristica = heuristica;
        this.custo = custo;
        this.posicao = posicao;
    }

    public No(int custo, Posicao posicao) {
        this.custo = custo;
        this.posicao = posicao;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getFuncao() {
        return funcao;
    }

    //calcula a função
    public void setFuncao() {
        this.funcao = this.custo + this.heuristica;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public int getPai() {
        return pai;
    }

    public void setPai(int pai) {
        this.pai = pai;
    }

    @Override
    public int compareTo(Object t) {
        if (this.funcao > ((No) t).funcao) {
            return 1;
        } else if (this.funcao < ((No) t).funcao) {
            return -1;
        }
        return 0;
    }

    //imprime os passsos
    @Override
    public String toString() {
        return "No{" + "Heuristica = " + heuristica + ", Custo = " + custo + ", Funcao = " + funcao + ", Posicao = " + posicao + "}";
    }

}
