package Q1;

public class Q1 {
	public static void main(String[] args) {
		LivroBiblioteca livro = new LivroBiblioteca("Uma breve historia do tempo", "Stephen Hawking", "Cosmologia", 255, 5, 2.00, 1);
		livro.emprestado();
		for(int dias = 0; dias <= 7; dias++) {
			livro.checarEmprestimo(dias);
		}
		
		System.out.println("-----------------");
		
		LivroBiblioteca livro2 = new LivroBiblioteca("Por uma outra globalizacao", "Milton Santos", "Geografia", 174, 5, 3.52, 1);
		livro2.emprestado();
		for(int dias = 0; dias <= 7; dias++) {
			livro2.checarEmprestimo(dias);
		}
		System.out.println("-----------------");
		livro2.emprestado();
		
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println();
		
		LivroLivraria livro3 = new LivroLivraria("Uma breve historia do tempo", "Stephen Hawking", "Cosmologia", 255, 35.00, 5, 10);
		livro3.venda(50.00, 10);
		System.out.println("----------------");
		LivroLivraria livro4 = new LivroLivraria("Por uma outra globalizacao", "Milton Santos", "Geografia", 174, 52.00, 3, 7);
		livro4.venda(35.00, 8);
	}

}

abstract class Livro {

	public abstract String getTitulo();
	public abstract void setTitulo(String titulo);
	
	public abstract String getAutor();
	public abstract void setAutor(String autor);
	
	public abstract String getGenero();
	public abstract void setGenero(String genero);
	
	public abstract int getPaginas();
	public abstract void setPaginas(int paginas);

}

class LivroBiblioteca extends Livro {
	
	private String titulo;
	private String autor;
	private String genero;
	private int paginas;
	
	private int tempoEmprestimo; //em dias
	private double multa; //diária
	private int unidadesDisponiveis;
	
	public String getTitulo() { return this.titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getAutor() { return this.autor; }
	public void setAutor(String autor) { this.autor = autor; }
	
	public String getGenero() { return this.genero; }
	public void setGenero(String genero) { this.genero = genero; }
	
	public int getPaginas() { return this.paginas; }
	public void setPaginas(int paginas) { this.paginas = paginas; }
	
	public int getTempoEmprestimo() { return this.tempoEmprestimo; }
	public void setTempoEmprestimo(int tempoEmprestimo) { this.tempoEmprestimo = tempoEmprestimo; }
	
	public double getMulta() { return this.multa; }
	public void setMulta(double multa) { this.multa = multa; }
	
	public int getUnidadesDisponiveis() { return this.unidadesDisponiveis; }
	public void setUnidadesDisponiveis(int unidadesDisponiveis) { this.unidadesDisponiveis = unidadesDisponiveis; }
	
	public LivroBiblioteca(String titulo, String autor, String genero, int paginas, int tempoEmprestimo, double multa, int unidadesDisponiveis) {
		setTitulo(titulo);
		setAutor(autor);
		setGenero(genero);
		setPaginas(paginas);
		setTempoEmprestimo(tempoEmprestimo);
		setMulta(multa);
		setUnidadesDisponiveis(unidadesDisponiveis);
	}
	
	public void emprestado() {
		if(this.unidadesDisponiveis >= 1) {
			this.unidadesDisponiveis--;
			System.out.println("Livro emprestado com sucesso. Unidades disponiveis: " + getUnidadesDisponiveis());
		}
		else System.out.println("Nao ha unidade disponivel do livro. Emprestimo nao realizado.");
		
		System.out.println("Detalhes do livro ----------");
		System.out.println("Titulo: \"" + getTitulo() + "\"");
		System.out.println("Autor: \"" + getAutor() + "\"");
		System.out.println("Genero: \"" + getGenero() + "\"");
		System.out.println("Paginas: \"" + getPaginas() + "\"");
		System.out.println();
	}
	
	public void checarEmprestimo(int dias) {
		double pagamento = 0;
		if(dias > this.tempoEmprestimo) {
			pagamento = (dias - this.tempoEmprestimo) * this.multa;
			System.out.println("Livro \"" + this.titulo + "\" esta atrasado. Multa totalizada: " + pagamento);
		}
		else System.out.println("Emprestimo no prazo, tudo ok.");
		
	}
	
}

class LivroLivraria extends Livro {
	
	private String titulo;
	private String autor;
	private String genero;
	private int paginas;
	
	private double precoVenda;
	private int qtdEstoque;
	private double recomendacao; //recomendação dos clientes da loja [0, 10]
	
	public String getTitulo() { return this.titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	
	public String getAutor() { return this.autor; }
	public void setAutor(String autor) { this.autor = autor; }
	
	public String getGenero() { return this.genero; }
	public void setGenero(String genero) { this.genero = genero; }
	
	public int getPaginas() { return this.paginas; }
	public void setPaginas(int paginas) { this.paginas = paginas; }

	public double getPrecoVenda() { return this.precoVenda; }
	public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
	
	public int getQtdEstoque() { return this.qtdEstoque; }
	public void setQtdEstoque(int qtdEstoque) { this.qtdEstoque = qtdEstoque; }
	
	public double getRecomendacao() { return this.recomendacao; }
	public void setRecomendacao(double recomendacao) { this.recomendacao = recomendacao; }
	
	public LivroLivraria(String titulo, String autor, String genero, int paginas, double precoVenda, int qtdEstoque, double recomendacao) {
		setTitulo(titulo);
		setAutor(autor);
		setGenero(genero);
		setPaginas(paginas);
		setPrecoVenda(precoVenda);
		setQtdEstoque(qtdEstoque);
		setRecomendacao(recomendacao);
	}
	
	public double venda(double pagamento, double recomendacaoRequisitada) {
		double troco = 0;
		if(recomendacaoRequisitada >= this.recomendacao && pagamento >= this.precoVenda && this.qtdEstoque >= 1) {
			this.qtdEstoque--;
			troco = pagamento - precoVenda;
			System.out.println("Livro vendido. Estoque disponivel: " + this.qtdEstoque + ". Troco: " + troco + ".");
		}
		else System.out.println("Requisitos nao atendidos. Venda nao efetuada.");
		
		System.out.println("Detalhes do livro ----------");
		System.out.println("Titulo: \"" + getTitulo() + "\"");
		System.out.println("Autor: \"" + getAutor() + "\"");
		System.out.println("Genero: \"" + getGenero() + "\"");
		System.out.println("Paginas: \"" + getPaginas() + "\"");
		
		return troco;
	}
	
}