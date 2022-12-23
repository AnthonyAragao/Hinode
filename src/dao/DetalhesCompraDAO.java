package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conection.ConnectionFactory;
import model.DetalhesCompra;
import model.Produto;

public class DetalhesCompraDAO {
	//Atributos para conexao
	public Connection conn;
	public ConnectionFactory conexao;
	
	//Construtor
	public DetalhesCompraDAO() {
		conexao = new ConnectionFactory();
	}
	
	
	//Comprar produto
	public void comprarProduto(DetalhesCompra detalhesCompra) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "INSERT INTO detalhescompra(nome,descricao, data, valorpago, idproduto, saldodevedor) VALUES(?,?,?,?,?,?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, detalhesCompra.getNome());
			stmt.setString(2, detalhesCompra.getDescricao());
			stmt.setString(3, detalhesCompra.getData());
			stmt.setDouble(4, detalhesCompra.getValorPago());
			stmt.setInt(5, detalhesCompra.getIdProduto().getId());
			stmt.setDouble(6, detalhesCompra.getSaldoDevedor());
			stmt.execute();
			System.out.println("Compra realizada com sucesso!!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
	
	//Consultar apenas um balanço
	public DetalhesCompra consultarBalanco(int id) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM  detalhescompra  JOIN produto ON  detalhescompra.idproduto = produto.id WHERE detalhescompra.id = ? ";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();
			
			DetalhesCompra balanco = new DetalhesCompra();
			Produto produto = new Produto();
			
			
			balanco.setData(rs.getString("data"));
			balanco.setDescricao(rs.getString("descricao"));
			balanco.setId(rs.getInt("id"));
			balanco.setNome(rs.getString("nome"));
			balanco.setSaldoDevedor(rs.getDouble("saldodevedor"));
			balanco.setValorPago(rs.getDouble("valorpago"));
			
			produto.setId(rs.getInt("idproduto"));
			produto.setNome(rs.getString("produto.nome"));
			produto.setPreco(rs.getDouble("preco"));
			
			balanco.setIdProduto(produto);
			return balanco;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	//Consultar balancos de compras
	public List<DetalhesCompra> consultarBalancoCompras(){
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM detalhescompra JOIN produto "
				+ "ON  detalhescompra.idproduto = produto.id "
				+ "ORDER BY detalhescompra.id";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<DetalhesCompra> lista = new ArrayList<>();
			
			while(rs.next()) {
				DetalhesCompra balanco = new DetalhesCompra();
				Produto produto = new Produto();
				
				balanco.setData(rs.getString("data"));
				balanco.setDescricao(rs.getString("descricao"));
				balanco.setId(rs.getInt("id"));
				balanco.setNome(rs.getString("nome"));
				balanco.setSaldoDevedor(rs.getDouble("saldodevedor"));
				balanco.setValorPago(rs.getDouble("valorpago"));
				
				produto.setId(rs.getInt("produto.id"));
				produto.setNome(rs.getString("produto.nome"));
				produto.setPreco(rs.getDouble("preco"));
				
				balanco.setIdProduto(produto);
				lista.add(balanco);
			}
			return lista;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			ConnectionFactory.closeConnection(conn, stmt, rs);
		}
	}
	
	
	//Deletar balanco
	public void deletarBalanco(int id) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM detalhescompra WHERE ID =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			System.out.println("\nProduto deleteado com sucesso!!!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}
	}
	
	
	
	//Atualizar detalhes da venda
	public void attDetalhesVenda(DetalhesCompra detalhesCompra) {
		conn = conexao.getConnection();
		PreparedStatement stmt = null;
		String sql = "UPDATE detalhescompra SET valorpago =?, saldodevedor = ?, descricao =? WHERE ID =?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, detalhesCompra.getValorPago());
			stmt.setDouble(2, detalhesCompra.getSaldoDevedor());
			stmt.setString(3, detalhesCompra.getDescricao());
			stmt.setInt(4, detalhesCompra.getId());
			stmt.execute();	
			
			System.out.println("Produto atualizado com Sucesso!!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(conn, stmt);
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
