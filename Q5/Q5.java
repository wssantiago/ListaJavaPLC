package Q5;

import java.util.ArrayList;
import java.util.List;

public class Q5 {
	public static void main(String[] args) {
		ColecaoVetor colecao = new ColecaoVetor();
		
		colecao.inserir(new Professor("Gustavo", "123.456.789-0", "CC", "Metodos Formais"));
		System.out.println("Vagas ocupadas: " + colecao.tamanhoColecaoNaoNull());
		colecao.inserir(new Aluno("Williams Santiago", "657.663.124-9", "11119978", "EC"));
		System.out.println("Vagas ocupadas: " + colecao.tamanhoColecaoNaoNull());
		colecao.inserir(new Aluno("Koala", "111.111.112-3", "99987622", "Med"));
		System.out.println("Vagas ocupadas: " + colecao.tamanhoColecaoNaoNull());
		
		System.out.println("-------------");
		for(String s : colecao.getNomesInseridos()) {
			System.out.println("Nome inserido: " + s);
		}
	}
}

interface Colecao {
	void inserir(Pessoa p);
	int tamanhoColecaoNaoNull();
}

class ColecaoVetor implements Colecao {
	
	private Pessoa[] dados;
	private int ocupados;
	private List<String> nomesInseridos;
	
	public ColecaoVetor() {
		this.dados = new Pessoa[5];
		this.ocupados = 0;
		this.nomesInseridos = new ArrayList<String>();
	}
	
	public void inserir(Pessoa p) {
		this.dados[this.ocupados++] = p;
		this.nomesInseridos.add(p.getNome());
	}

	public int tamanhoColecaoNaoNull() { return this.ocupados; }
	
	public List<String> getNomesInseridos() { return this.nomesInseridos; }
}

abstract class Pessoa {
	protected String nome;
	protected String cpf;
	
	public String getNome() { return this.nome; }
	public abstract void setNome(String nome);
	
	public String getCpf() { return this.cpf; }
	public abstract void setCpf(String cpf);
	
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
}

class Professor extends Pessoa {
	
	private String formacao;
	private String area;

	public Professor(String nome, String cpf, String formacao, String area) {
		super(nome, cpf);
		this.formacao = formacao;
		this.area = area;
	}

	public void setNome(String nome) { this.nome = nome; }

	public void setCpf(String cpf) { this.cpf = cpf; }
	
}

class Aluno extends Pessoa {
	
	private String matricula;
	private String curso;

	public Aluno(String nome, String cpf, String matricula, String curso) {
		super(nome, cpf);
		this.matricula = matricula;
		this.curso = curso;
	}

	public void setNome(String nome) { this.nome = nome; }

	public void setCpf(String cpf) { this.cpf = cpf; }
	
}





































