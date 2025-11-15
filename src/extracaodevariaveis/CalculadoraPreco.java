package extracaodevariaveis;

/**
 * EXERCÍCIO: Extração de Variáveis
 * 
 * PROBLEMA: A expressão no return é complexa e difícil de entender.
 * 
 * TAREFA: Extraia partes da expressão em variáveis com nomes descritivos.
 * 
 * DICA: Variáveis extraídas tornam o código mais legível e facilitam a depuração.
 */
public class CalculadoraPreco {
    
    public double calcularPrecoFinal(double precoBase, int quantidade, double taxaImposto) {
        double subtotal = precoBase * quantidade;
        double imposto = subtotal * 0.1;
        double taxaFixa = 5.0;
        return subtotal * (1 + taxaImposto) - imposto + taxaFixa;
    }
    
    public boolean podeAplicarDesconto(double preco, int quantidade, boolean clienteVIP) {
        boolean descontoVolume = preco > 100 && quantidade > 5;
        boolean descontoClienteVIP = clienteVIP && preco > 50;
        return descontoVolume || descontoClienteVIP;
    }
}

