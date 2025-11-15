package substituicaodecondicionalporpolimorfismo;

/**
 * EXERCÍCIO: Substituição de Condicional por Polimorfismo
 * 
 * PROBLEMA: O método calcularImposto() usa uma cadeia de if-else baseada no tipo.
 * 
 * TAREFA: Substitua o condicional por polimorfismo criando uma hierarquia de classes.
 * 
 * DICA: Use herança e polimorfismo para eliminar condicionais baseados em tipo.
 */
public class CalculadoraImposto {
    
    public double calcularImposto(Contribuinte contribuinte, double valorImposto) {
        return contribuinte.calcularImposto(valorImposto);
    }

}

