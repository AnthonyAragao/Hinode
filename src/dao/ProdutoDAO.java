package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conection.ConnectionFactory;
import model.Produto;


public class ProdutoDAO {
	//Atributos para conexao
	private Connection conn;
	private ConnectionFactory conexao;
	
	
	//Construtor
	public ProdutoDAO() {
		conexao = new ConnectionFactory();
	}
	
	
	//Cadastrar um produto
	public void create(Produto produto) {
		conn = conexao.getConnection(); //Abrir conexao
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO produto(nome, preco) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.execute();
			System.out.println("\nProduto criado com Sucesso!!!");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}finally {
			ConnectionFactory.closeConnection(conn, stmt); //Fecho a conexao
		}
	}
	
	
	//Consultar todos os produtos
	public List<Produto> consultaTotal() {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT * FROM produto";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Produto> listaProdutos = new ArrayList<>();
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				listaProdutos.add(produto);	
			}
			
			return listaProdutos;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}

	
	//Consultar produto
	public Produto consultarProduto(int id) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM produto WHERE id =?";
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			Produto produto = new Produto();
			rs.first();
			produto.setId(id);
			produto.setNome(rs.getString("nome"));
			produto.setPreco(rs.getDouble("preco"));
			return produto;
			
		} catch (Exception e) {
			return null;
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}
	
	
	//Atualizar preco do produto
	public void attPreco(Produto produto) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "UPDATE produto SET preco = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, produto.getPreco());
			stmt.setInt(2, produto.getId());
			stmt.executeUpdate();
			System.out.println("Produto atualizado com Sucesso!!!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
	
	
	
	//Atualizar nome do produto
	public void attNome(Produto produto) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE produto SET nome = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getId());
			stmt.executeUpdate();
			System.out.println("Produto atualizado com Sucesso!!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
	
	
	//Deletar um produto
	public void deletarProduto(int id) {
		conn = conexao.getConnection(); //Estabeleço conexao
		PreparedStatement stmt = null;
		String sql = "DELETE FROM produto WHERE id = ?"; 
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			System.out.println("Produto excluido com suecesso!!!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, stmt); //Fecho a conexao
		}
	}
	
	
	

	public void funcaoAux() {
		List<Produto> listaProdutos;
		listaProdutos = consultaTotal();
		for(Produto produtoLista : listaProdutos) {
			System.out.println(produtoLista);
		}
	}
	
	

}
