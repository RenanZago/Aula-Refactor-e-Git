package movimentacaometodo;

public class Pedido {
    private double valorTotal;
    
    public Pedido(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    // Este método deveria estar na classe Pedido
    public double calcularDesconto() {
        if (getValorTotal() > 1000) {
            return getValorTotal() * 0.10;
        } else if (getValorTotal() > 500) {
            return getValorTotal() * 0.05;
        }
        return 0;
    }
}

