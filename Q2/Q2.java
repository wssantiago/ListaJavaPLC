package Q2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2 {
	public static void main(String[] args) {
		Texto ex = new Texto("Boa (plc)noite?[]^^''>5,	meu (plc) nome eh Williams Santiago!!Esse aqui(plc) eh um teste simples :)   ");
		ex.textoCompleto();
		System.out.println("------------");
		ex.tornaTokenizavel();
		System.out.println("Corpo do texto tokenizavel: \"" + ex.getCorpoTokenizavel() + "\"");
		ex.tokenizar();
		/*for(String s : oi.palavras) {
				System.out.println(s);
		}*/
		System.out.println("------------");
		System.out.println("Total de palavras: " + ex.quantasPalavras());
		System.out.println("------------");
		System.out.println("Frequencia total da subtring \"pl\" considerando o total de palavras: " + ex.frequencia("pl"));
		System.out.println("------------");
		ex.trocaPalavra("plc", "brTT");
	}
}

class Texto {
	private String corpo;
	private String corpoTokenizavel;
	private List<String> palavras;
	
	public String getCorpo() { return this.corpo; } 
	public void setCorpo(String corpo) { this.corpo = corpo; }
	
	public String getCorpoTokenizavel() { return this.corpoTokenizavel; }
	public void setCorpoTokenizavel(String corpoTokenizavel) { this.corpoTokenizavel = corpoTokenizavel; }
	
	public Texto(String corpo) {
		this.corpo = corpo;
		this.corpoTokenizavel = corpo;
		this.palavras = new ArrayList<String>();
	}
	
	
	public void textoCompleto() {
		System.out.println("Corpo do texto: \"" + this.corpo + "\"");
	}
	
	/**
	 * faz o replace de qualquer char que não é alfanumérico por um ';'
	 */
	public void tornaTokenizavel() {
		int tam = this.corpo.length();
		for(int i=0; i<tam; i++) {
			char cur = this.corpo.charAt(i);
			if((cur < 97 || cur > 122) && (cur < 65 || cur > 90) && (cur < 48 || cur > 57)) {
				this.corpoTokenizavel = this.corpoTokenizavel.replace(cur, ';');
			}
		}
	}
	
	/**
	 * tokeniza levando em consideração ';'
	 * os tokens vazios são ignorados
	 * em 'this.palavras' temos todas as palavras válidas do texto.
	 */
	public void tokenizar() {
		List<String> aux = new ArrayList<String>();
		aux = Arrays.asList(this.corpoTokenizavel.split(";"));
		for(String s : aux) {
			if(!(s.isEmpty())) this.palavras.add(s);
		}
	}
	
	public int quantasPalavras() {
		return this.palavras.size();
	}
	
	public int frequencia(String str) {
		int freq = 0;
		for(String s : this.palavras) {
			if(s.contains(str)) freq++;
		}
		
		return freq;
	}
	
	public void trocaPalavra(String alvo, String palavra) {
		for(String s : this.palavras) {
			if(s.equals(alvo)) {
				this.corpo = this.corpo.replace(alvo, palavra);
			}
		}
		System.out.println("Corpo do texto atualizado (" + alvo + " -> " + palavra + "): \"" + this.corpo + "\"");
	}
}