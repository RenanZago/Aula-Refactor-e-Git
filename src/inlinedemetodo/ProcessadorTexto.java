package inlinedemetodo;

/**
 * EXERCÍCIO: Inline de Método
 * 
 * PROBLEMA: O método formatarTexto() é muito simples e é chamado apenas uma vez.
 * 
 * TAREFA: Faça inline do método formatarTexto() diretamente no método processar().
 * 
 * DICA: Quando um método é muito simples e tem apenas uma chamada, pode ser melhor
 * incorporar seu conteúdo diretamente no método chamador.
 */
public class ProcessadorTexto {
    
    public String processar(String texto) {
        return texto.trim().toLowerCase();
    }

}

