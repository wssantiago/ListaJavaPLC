package Q3;

public class Q3 {
	
	public static void main(String[] args) {
		Pessoa eu = new Pessoa();
		eu.setNome("Williams");
		eu.setIdade(19);
		eu.setEscolaridade("Ensino superior incompleto");
		eu.comer();
		System.out.println(eu.getNome());
		System.out.println(eu.getIdade());
		System.out.println(eu.getEscolaridade());
		System.out.println(eu.getSexo());
		eu.cagar();
		eu.trabalhar();
		
		System.out.println("-----------");
		
		Pessoa mcSheldon = new Pessoa("Sheldon Ferrer", 29, "Pós-doutorado", identidadesGenero.CISGENERO, "Heterossexual", "Masculino");
		System.out.println(mcSheldon.getNome());
		mcSheldon.cagar();
		System.out.println(mcSheldon.getIdGenero());
		System.out.println(mcSheldon.getEscolaridade());
		mcSheldon.dormir();
		mcSheldon.trabalhar();
		mcSheldon.estudar();
		
	}
}

enum identidadesGenero {
	
	CISGENERO("cisgenero"),
	TRANSGENERO("transgenero"),
	GENERO_FLUIDO("genero fluido"),
	NAO_BINARIO("nao-binario");
	
	private String genero;
	
	private identidadesGenero(String genero) {
		this.genero = genero;
	}
	
	public String getGenero() { return this.genero; }
}

class Pessoa {
	protected String nome;
	protected int idade;
	protected String escolaridade;
	protected String idGenero;
	protected String orientacaoSexual;
	protected String sexo;
	
	public String getNome() { return this.nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public int getIdade() { return this.idade; }
	public void setIdade(int idade) { this.idade = idade; }
	
	public String getEscolaridade() { return this.escolaridade; }
	public void setEscolaridade(String escolaridade) { this.escolaridade = escolaridade; }
	
	public String getIdGenero() { return this.idGenero; }
	
	public String getOrientacaoSexual() { return this.orientacaoSexual; }
	
	public String getSexo() { return this.sexo; }
	
	public Pessoa() {}
	
	public Pessoa(String nome, int idade, String escolaridade, identidadesGenero idGenero, String orientacaoSexual, String sexo) {
		this.nome = nome;
		this.idade = idade;
		this.escolaridade = escolaridade;
		this.idGenero = idGenero.getGenero();
		this.orientacaoSexual = orientacaoSexual;
		this.sexo = sexo;
	}
	
	public void dormir() {
		System.out.println("Que sono da misera, vou dormir!!");
	}
	
	public void cagar() {
		System.out.println("Entupi a privada: o tolete nao passou pelo encanamento :(");
	}
	
	public void comer() {
		System.out.println("Que fome, quero comerrr!!");
	}
	
	public void estudar() {
		System.out.println("Tenho que estudar para passar de periodo!");
	}
	public void viver() {
		System.out.println("Viver intensamente ate o ultimo momento!");
	}
	public void sobreviver() {
		System.out.println("Sobreviver nesse mundo cruel eh dificil!");
	}
	public void trabalhar() {
		System.out.println("Trabalhar para sustentar minha familia!");
	}
}

