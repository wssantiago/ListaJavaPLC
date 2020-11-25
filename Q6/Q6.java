package Q6;

import java.util.Scanner;

public class Q6 {
	
	private ExprLogica exprLogica;
	private ExprAritmetica exprAritmetica;
	private ExprOpTernario exprOpTernario;
	private int qualTipo = 0;
	
	public void descobreTipoDeExpr(String expr) {
		
		if(expr.contains("?") && expr.contains(":")) {
			qualTipo = 1;
			exprOpTernario = new ExprOpTernario(expr);
		}
		else {
			int ok = 0;
			for(OperadoresLogicos op : OperadoresLogicos.values()) {
				String opString = op.getOper();
				if(expr.contains(opString)) {
					exprLogica = new ExprLogica(expr);
					qualTipo = 2;
					ok = 1;
					break;
				}
			}
			if(ok == 0) {
				qualTipo = 3;
				exprAritmetica = new ExprAritmetica(expr);
			}
;
		}
	}
	
	public static void main(String[] args) {
		
		Q6 teste = new Q6();
		
		Scanner in = new Scanner(System.in);
		String expr = in.next();
		in.close();
		
		teste.descobreTipoDeExpr(expr);
		
		if(teste.qualTipo == 3) {
			System.out.println(teste.exprAritmetica.avaliar(teste.exprAritmetica.getExpr()));
			System.out.println(teste.exprAritmetica.imprimirArvore(teste.exprAritmetica.getExpr()));
		}
		else if(teste.qualTipo == 2){
			System.out.println(teste.exprLogica.avaliar(teste.exprLogica.getExpr()));
			System.out.println(teste.exprLogica.imprimirArvore(teste.exprLogica.getExpr()));
		
		}
		else if(teste.qualTipo == 1) {
			System.out.println(teste.exprOpTernario.avaliar(teste.exprOpTernario.getExpr()));
			System.out.println(teste.exprOpTernario.imprimirArvore(teste.exprOpTernario.getExpr()));
		}
	}
}

interface Expression {
	String avaliar(String expr);
	String imprimirArvore(String expr);
}

class ExprAritmetica implements Expression {
	
	private String expr = "";
	
	public String getExpr() { return this.expr; }
	public void setExpr(String expr) { this.expr = expr; }
	
	public ExprAritmetica(String expr) {
		this.expr = expr;
	}
	
	public int procuraOper(char op, String subExpr) {
		int tam = subExpr.length();
		int index = -1;
		for(int i=0; i<tam; i++) {
			if(subExpr.charAt(i) == op) index = i;
		}
		
		return index;
	}
	
	public String avaliar(String expr) {

		int tam = expr.length();
		int a = this.procuraOper('+', expr);
		int b = this.procuraOper('-', expr);
		int c = this.procuraOper('*', expr);
		int d = this.procuraOper('/', expr);

		
		if(a != -1) {
			return Double.toString((Double.parseDouble(this.avaliar(expr.substring(a+1, tam))) + Double.parseDouble(this.avaliar(expr.substring(0, a)))));
		}
		else if(b != -1) {
			return Double.toString((Double.parseDouble(this.avaliar(expr.substring(0, b))) - Double.parseDouble(this.avaliar(expr.substring(b+1, tam)))));
		}
		else if(c != -1) {
			return Double.toString((Double.parseDouble(this.avaliar(expr.substring(c+1, tam))) * Double.parseDouble(this.avaliar(expr.substring(0, c)))));	
		}
		else if(d != -1) {
			return Double.toString((Double.parseDouble(this.avaliar(expr.substring(0, d))) / Double.parseDouble(this.avaliar(expr.substring(d+1, tam)))));
		}
		else return expr;

	}

	public String imprimirArvore(String expr) {
		
		int tam = expr.length();
		int a = this.procuraOper('+', expr);
		int b = this.procuraOper('-', expr);
		int c = this.procuraOper('*', expr);
		int d = this.procuraOper('/', expr);
		
		
		if(a != -1) {
			return "(" + this.imprimirArvore(expr.substring(0, a)) + " + " + this.imprimirArvore(expr.substring(a+1, tam)) + ")";
		}
		else if(b != -1) {
			return "(" + this.imprimirArvore(expr.substring(0, b)) + " - " + this.imprimirArvore(expr.substring(b+1, tam)) + ")";
		}
		else if(c != -1) {
			return "(" + this.imprimirArvore(expr.substring(0, c)) + " * " + this.imprimirArvore(expr.substring(c+1, tam)) + ")";	
		}
		else if(d != -1) {
			return "(" + this.imprimirArvore(expr.substring(0, d)) + " / " + this.imprimirArvore(expr.substring(d+1, tam)) + ")";
		}
		else return "(" + expr + ")";

	}
	
	public String toString() {
		return this.expr;
	}
}	

enum OperadoresLogicos {
	MAIOR_IGUAL(">="),
	MENOR_IGUAL("<="),
	MAIOR(">"),
	MENOR("<"),
	DIFERENTE("!="),
	IGUAL("==");
	
	private String oper;
	
	public String getOper() { return this.oper; }
	
	private OperadoresLogicos(String oper) {
		this.oper = oper;
	}
}

class ExprLogica implements Expression {
	
	private String expr;
	private String leftExpr;
	private String rightExpr;
	private OperadoresLogicos oper;
	private boolean neverEvaluated;
	
	public String getExpr() { return this.expr; }
	public String getLeftExpr() { return this.leftExpr; }
	public String getRightExpr() { return this.rightExpr; }
	
	public ExprLogica(String expr) {
		this.expr = expr;
		this.leftExpr = "";
		this.rightExpr = "";
		this.neverEvaluated = true;
	}
	
	public String avaliar(String expr) {
		
		neverEvaluated = false;
		
		if(expr.contains(">=")) {
			oper = OperadoresLogicos.MAIOR_IGUAL;
			leftExpr = expr.substring(0, expr.indexOf(">="));
			rightExpr = expr.substring(expr.indexOf(">=")+2);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) >= Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else if(expr.contains("<=")) {
			oper = OperadoresLogicos.MENOR_IGUAL;
			leftExpr = expr.substring(0, expr.indexOf("<="));
			rightExpr = expr.substring(expr.indexOf("<=")+2);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) <= Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else if(expr.contains(">")) {
			oper = OperadoresLogicos.MAIOR;
			leftExpr = expr.substring(0, expr.indexOf(">"));
			rightExpr = expr.substring(expr.indexOf(">")+1);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) > Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else if(expr.contains("<")) {
			oper = OperadoresLogicos.MENOR;
			leftExpr = expr.substring(0, expr.indexOf("<"));
			rightExpr = expr.substring(expr.indexOf("<")+1);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) < Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else if(expr.contains("==")) {
			oper = OperadoresLogicos.IGUAL;
			leftExpr = expr.substring(0, expr.indexOf("=="));
			rightExpr = expr.substring(expr.indexOf("==")+2);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) == Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else if(expr.contains("!=")) {
			oper = OperadoresLogicos.DIFERENTE;
			leftExpr = expr.substring(0, expr.indexOf("!="));
			rightExpr = expr.substring(expr.indexOf("!=")+2);
			return Boolean.toString(Double.parseDouble(new ExprAritmetica(leftExpr).avaliar(leftExpr)) != Double.parseDouble(new ExprAritmetica(rightExpr).avaliar(rightExpr)));
		}
		else return null;

	}

	public String imprimirArvore(String expr) {
		if(neverEvaluated) {
			String set = this.avaliar(expr);
		}
		return "(" + new ExprAritmetica(leftExpr).imprimirArvore(leftExpr) + " " + oper.getOper() + " " + new ExprAritmetica(rightExpr).imprimirArvore(rightExpr) + ")";
	}
	
	public String toString() {
		return this.expr;
	}
	
}
	

class ExprOpTernario implements Expression {

	private String expr;
	private String pergunta;
	private String left;
	private String right;
	private boolean neverEvaluated;
	
	public String getExpr() { return this.expr; }
	public String getPergunta() { return this.pergunta; }
	public String getLeft() { return this.left; }
	public String getRight() { return this.right; }
	
	public ExprOpTernario(String expr) {
		this.expr = expr;
		this.pergunta = "";
		this.left= "";
		this.right = "";
		this.neverEvaluated = true;
		
	}
	
	
	public String avaliar(String expr) {
		
		neverEvaluated = false;
		
		pergunta = expr.substring(0, expr.indexOf('?'));
		left = expr.substring(expr.indexOf('?')+1, expr.indexOf(':'));
		right = expr.substring(expr.indexOf(':')+1);
		
		if(new ExprLogica(pergunta).avaliar(pergunta).equals("true")) {
			for(OperadoresLogicos op : OperadoresLogicos.values()) {
				if(left.contains(op.getOper())) {
					return new ExprLogica(left).avaliar(left);
				}
			}
			return new ExprAritmetica(left).avaliar(left);
		}
		else {
			for(OperadoresLogicos op : OperadoresLogicos.values()) {
				if(right.contains(op.getOper())) {
					return new ExprLogica(right).avaliar(right);
				}
			}
			return new ExprAritmetica(right).avaliar(right);
		}
	}

	public String imprimirArvore(String expr) {
		
		boolean rightIsLogical = false;
		
		if(neverEvaluated) {
			String set = this.avaliar(expr);
		}
		
		for(OperadoresLogicos op : OperadoresLogicos.values()) {
			if(left.contains(op.getOper())) {
				for(OperadoresLogicos op2 : OperadoresLogicos.values()) {
					if(right.contains(op2.getOper())) {
						rightIsLogical = true;
						//left e right são ambas lógicas
						return new ExprLogica(pergunta).imprimirArvore(pergunta) + " ? " + new ExprLogica(left).imprimirArvore(left) + " : " + new ExprLogica(right).imprimirArvore(right);
					}
				}
				//apenas left é lógica, right é aritmética
				return new ExprLogica(pergunta).imprimirArvore(pergunta) + " ? " + new ExprLogica(left).imprimirArvore(left) + " : " + new ExprAritmetica(right).imprimirArvore(right);
			}
		}
		if(rightIsLogical) {
			//left é aritmética, mas right é lógica
			return new ExprLogica(pergunta).imprimirArvore(pergunta) + " ? " + new ExprAritmetica(left).imprimirArvore(left) + " : " + new ExprLogica(right).imprimirArvore(right);
		}
		else {
			//left e right são ambas aritméticas
			return new ExprLogica(pergunta).imprimirArvore(pergunta) + " ? " + new ExprAritmetica(left).imprimirArvore(left) + " : " + new ExprAritmetica(right).imprimirArvore(right);
		}
	}
	
	public String toString() {
		return this.expr;
	}
	
}
















