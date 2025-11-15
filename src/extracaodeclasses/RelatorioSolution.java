package extracaodeclasses;

public class RelatorioSolution {
    private String titulo;
    private String conteudo;
    private String rodape;
    private FormatadorRelatorio formatador;

    public RelatorioSolution(String rodape, FormatadorRelatorio formatador, String titulo, String conteudo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.rodape = rodape;
        this.formatador = formatador;
    }

    public void imprimir () {
        formatador.formatarTitulo(titulo);
        formatador.formatarConteudo(conteudo);
        formatador.formatarRodape(rodape);
    }
}
