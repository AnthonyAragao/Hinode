package model;

public class DetalhesCompra {
	//Atributos
	private int id;
	private String nome;
	private String data;
	private String descricao;
	private double valorPago, saldoDevedor;
	private Produto idProduto;
	
	
	@Override
	public String toString() {
		return "------------------------------------" + "\nNome: " + nome + "\n" +  descricao + "\nCompra realizada na data: " + data;	
	}
	
	
	
	public void addConstrutor(String nome, String data, String descricao, double valorPago, Produto idProduto) {
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.valorPago = valorPago;
		this.idProduto = idProduto;
		saldoDevedor = idProduto.getPreco() - valorPago;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getSaldoDevedor() {
		return saldoDevedor;
	}
	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public Produto getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}
}
