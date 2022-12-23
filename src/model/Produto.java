package model;

public class Produto {
	//Atributos
	private int id;
	private String nome;
	private double preco;
	
	//Add Construtor
	public void addConstrutor(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return id + " - " + nome + "----------------------Preco: " + preco;
	}
	
	
	
	//Getters e Setters
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
	
	
	public double getPreco() {
		return preco;
	}
	
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
