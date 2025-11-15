package extracaometodo;

/**
 * EXERCÍCIO: Extração de Método
 * 
 * PROBLEMA: O método calcularTotal() está fazendo muitas coisas e tem código duplicado.
 * 
 * TAREFA: Extraia os cálculos de desconto e imposto em métodos separados.
 * 
 * DICA: Identifique blocos de código que fazem uma coisa específica e extraia-os em métodos nomeados.
 */
public class Calculadora {
    
    public double calcularTotal(double preco, int quantidade, boolean temDesconto, boolean temImposto) {
        double subtotal = preco * quantidade;
        
        // Cálculo de desconto
        subtotal = calculoDesconto(quantidade, temDesconto, subtotal);

        // Cálculo de imposto
        subtotal = calculoImposto(temImposto, subtotal);

        return subtotal;
    }

    private static double calculoImposto(boolean temImposto, double subtotal) {
        if (temImposto) {
            if (subtotal > 1000) {
                subtotal = subtotal + (subtotal * 0.20);
            } else if (subtotal > 500) {
                subtotal = subtotal + (subtotal * 0.15);
            } else {
                subtotal = subtotal + (subtotal * 0.10);
            }
        }
        return subtotal;
    }

    private static double calculoDesconto(int quantidade, boolean temDesconto, double subtotal) {
        if (temDesconto) {
            if (quantidade > 10) {
                subtotal = subtotal - (subtotal * 0.15);
            } else if (quantidade > 5) {
                subtotal = subtotal - (subtotal * 0.10);
            } else {
                subtotal = subtotal - (subtotal * 0.05);
            }
        }
        return subtotal;
    }
}

