package model;

public class Atendente {
	
	  private int id;
	  private String nome;
	  private int codAtend;
	  private double salario;
	  
	  
	public int getCodAtend() {
		return codAtend;
	}


	public void setCodAtend(int codAtend) {
		this.codAtend = codAtend;
	}


	public Atendente(int id) {
		super();
		this.id = id;
	}


	public Atendente(String nome) {
		super();
		this.nome = nome;
	}
	
	

	public Atendente(int id, String nome, int codAtend, double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.codAtend=codAtend;
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

