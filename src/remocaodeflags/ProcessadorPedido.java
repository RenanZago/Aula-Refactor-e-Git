package remocaodeflags;

/**
 * EXERCÍCIO: Remoção de Flags
 * 
 * PROBLEMA: O código usa uma flag booleana para controlar o comportamento,
 * tornando o método difícil de entender e manter.
 * 
 * TAREFA: Remova a flag e crie métodos separados para cada comportamento.
 * 
 * DICA: Flags tornam o código menos claro. Prefira métodos separados ou polimorfismo.
 */
public class ProcessadorPedido {
    
    public void processarComDesconto(Pedido pedido) {
        double desconto = 0.9;
        double valor = pedido.getValor() * desconto;
        System.out.println(valor);
        pedido.setValor(valor);
    }

    public void processarSemDesconto(Pedido pedido) {
        double valor = pedido.getValor();
        System.out.println(valor);
        pedido.setValor(valor);
    }
}

