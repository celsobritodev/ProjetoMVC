package model;

public class Secretario {

	private int id;
	private String nome;
	private int codSecret;
	private double salario;
	

	public int getCodSecret() {
		return codSecret;
	}


	public void setCodSecret(int codSecret) {
		this.codSecret = codSecret;
	}


	public Secretario(int id) {
		super();
		this.id = id;
	}


	public Secretario(String nome) {
		super();
		this.nome = nome;
	}


	public Secretario(int id, String nome, int codSecret, double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.codSecret=codSecret;
		this.salario = salario;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	

}
