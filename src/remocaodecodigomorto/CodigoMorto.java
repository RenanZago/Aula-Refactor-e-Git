package remocaodecodigomorto;

/**
 * EXERCÍCIO: Remoção de Código Morto
 * 
 * PROBLEMA: O código contém métodos, variáveis e blocos de código que nunca são usados.
 * 
 * TAREFA: Remova todo o código morto (não utilizado).
 * 
 * DICA: Código morto aumenta a complexidade desnecessariamente e pode confundir leitores.
 */
public class CodigoMorto {
    
    public int calcular(int a, int b) {
        int resultado = a + b;
        return resultado;
    }
    
    // Método que nunca é chamado
    public void metodoUtilizado() {
        System.out.println("Este método é usado");
    }
    
    // Variável local não utilizada
    public void exemplo() {
        int y = 10;
        System.out.println(y); // x nunca é usado
    }
}

