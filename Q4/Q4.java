package Q4;

public class Q4 {
	public static void main(String[] args) {
		Conta minhaConta = new Conta("12345-6", "7890-1", new Senha("B9A8C7D5", 42365228));
		System.out.println("Numero de identificacao da conta: " + minhaConta.id);
		System.out.println("Senha de letras dessa conta: " + minhaConta.senha.getSenhaDificil());
		minhaConta.senha.setSenhaDificil("S3NH4D1F");
		System.out.println("Senha de letras dessa conta: " + minhaConta.senha.getSenhaDificil());
	}
}

class Conta {
	protected String id;
	protected String agencia;
	protected Senha senha;
	
	public String getId() { return this.id; }
	public void setId(String id) { this.id = id; }
	
	public String getAgencia() { return this.agencia; }
	public void setAgencia(String agencia) { this.agencia = agencia; }
	
	public Conta(String id, String agencia, Senha senha) {
		this.id = id;
		this.agencia = agencia;
		this.senha = senha;
	}
	
	public Conta(String id, String agencia, String senhaDificil, int senhaFacil) {
		this.id = id;
		this.agencia = agencia;
		this.senha = new Senha(senhaDificil, senhaFacil);
	}
}

class Senha {
	protected String senhaDificil;
	protected int senhaFacil;
	
	public String getSenhaDificil() { return this.senhaDificil; }
	public void setSenhaDificil(String senhaDificil) { this.senhaDificil = senhaDificil; }
	
	public int getSenhaFacil() { return this.senhaFacil; }
	public void setSenhaFacil(int senhaFacil) { this.senhaFacil = senhaFacil; }
	
	public Senha(String senhaDificil, int senhaFacil) {
		this.senhaDificil = senhaDificil;
		this.senhaFacil = senhaFacil;
	}
}











