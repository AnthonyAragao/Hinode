package view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import dao.DetalhesCompraDAO;
import dao.ProdutoDAO;
import model.DetalhesCompra;
import model.Produto;


public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int opc, idProduto, idCompra;
		String nome, nomeComprador, descricaoVenda, dataCompra;
		double preco, valorPago;
		List<Produto> listaProdutos;
		List<DetalhesCompra> listaBalanco;
		Produto produto;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		DetalhesCompra detalhesCompra;
		DetalhesCompraDAO detalhesCompraDAO = new DetalhesCompraDAO();
		DecimalFormat formatador = new DecimalFormat("0.00");
		
		teclado.useLocale(Locale.ENGLISH);
		do {
			System.out.println("\n------------Hinode do Querido------------");
			System.out.println("Escolha uma opcao");
			System.out.println("1 - Cadastrar um Produto");
			System.out.println("2 - Consultar Produtos");
			System.out.println("3 - Atualizar Produto");
			System.out.println("4 - Deletar o Produto");
			System.out.println("5 - Comprar Produto");
			System.out.println("6 - Consultar balanço de compras");
			System.out.println("7 - Atualizar detalhes de compra");
			System.out.println("8 - Deletar detalhes de compras");
			System.out.println("9 - Consultar saldo na rua");
			System.out.println("0 - Sair");
			
			opc = teclado.nextInt();
			
			teclado.nextLine(); //Limpando o buffer
			switch(opc) {
				case 1:
					System.out.println("Nome do produto: ");
					nome = teclado.nextLine();
					System.out.println("Preco do produto: ");
					preco = teclado.nextDouble();
					produto = new Produto();
					produto.addConstrutor(nome, preco);
					produtoDAO.create(produto);
					break;
					
				
				case 2:
					System.out.println("\n\nLista de produtos: ");
					produtoDAO.funcaoAux(); //Essa funcao imprimi o relatorio de produtos no Banco
					break;
					
					
				case 3:
					System.out.println("\nSelecione o produto que voce deseja atualizar: ");
					produtoDAO.funcaoAux(); //Relatorio dos produtos do Banco
					idProduto = teclado.nextInt();
					System.out.println("\nSelecione a opcao desejada: ");
					System.out.println("1 - Atualizar o preco do produto");
					System.out.println("2 - Atualizar o nome do produto");
					int opcaoAtt = teclado.nextInt();
					
					//Atualizar o preco do produto
					if(opcaoAtt == 1) { 
						System.out.println("Selecione o novo preco do produto: ");
						preco = teclado.nextDouble();
						produto = new Produto();
						produto.setId(idProduto);
						produto.setPreco(preco);
						produtoDAO.attPreco(produto);
					
					//Atualizar o nome do produto
					}else if(opcaoAtt == 2) {
						teclado.nextLine(); //Limpando o buffer
						System.out.println("Selecione o novo nome do produto: ");
						nome = teclado.nextLine();
						produto = new Produto();
						produto.setId(idProduto);
						produto.setNome(nome);
						produtoDAO.attNome(produto);
						
					}else {
						System.out.println("\nOpcao invalida....");
					}
					break;
					
				case 4:
					System.out.println("\nSelecione o produto que voce deseja excluir: ");
					produtoDAO.funcaoAux();
					idProduto = teclado.nextInt();
					produtoDAO.deletarProduto(idProduto);
					break;
					
					
				case 5:
					System.out.println("Selecione o produto que voce deseja comprar: ");
					produtoDAO.funcaoAux();
					idProduto = teclado.nextInt();
					
					produto = new Produto();
					produto = produtoDAO.consultarProduto(idProduto); //A instancia do "detalhesCompra" Recebe um Produto e nao um Numero
					teclado.nextLine(); //Limpando o buffer
					System.out.println("Nome do comprador: ");
					nomeComprador = teclado.nextLine();
					System.out.println("Valor pago: ");
					valorPago = teclado.nextDouble();
					teclado.nextLine(); //Limpando o buffer
					System.out.println("Data da Compra: ");
					dataCompra = teclado.nextLine();
					System.out.println("Descricao: ");
					descricaoVenda = teclado.nextLine();
					
					detalhesCompra = new DetalhesCompra();
					detalhesCompra.addConstrutor(nomeComprador, dataCompra, descricaoVenda, valorPago, produto);
					detalhesCompraDAO.comprarProduto(detalhesCompra);
					
					break;
					
				
				case 6:
					System.out.println("\nBalanço de compras");
					listaBalanco = detalhesCompraDAO.consultarBalancoCompras();
					for(DetalhesCompra balancoLista : listaBalanco) {
						System.out.println(
								"----------------------------\n" 
								+ balancoLista.getId() + " - " + balancoLista.getNome() 
								+ "\n  Produto: " + balancoLista.getIdProduto().getNome()
								+ "\n  Saldo Devedor: " +  formatador.format(balancoLista.getSaldoDevedor())
								+ "\n  Valor pago: " + formatador.format(balancoLista.getValorPago())
								+ "\n  Preco do produto: " + formatador.format(balancoLista.getIdProduto().getPreco())
								+ "\n  Data da compra: " + balancoLista.getData()
								+ "\n  Descricao: " + balancoLista.getDescricao()
								);
					}
					break;
				
				
				case 7:
					System.out.println("Selecione o numero do balanço que voce deseja atualizar");
					listaBalanco = detalhesCompraDAO.consultarBalancoCompras();
					for(DetalhesCompra balancoLista : listaBalanco) {
						System.out.println(
								"----------------------------\n" 
								+ balancoLista.getId() + " - " + balancoLista.getNome() 
								+ "\n  Produto: " + balancoLista.getIdProduto().getNome()
								+ "\n  Saldo Devedor: " +  formatador.format(balancoLista.getSaldoDevedor())
								+ "\n  Valor pago: " + formatador.format(balancoLista.getValorPago())
								+ "\n  Data da compra: " + balancoLista.getData()
								+ "\n  Descricao: " + balancoLista.getDescricao()
								);
					}
					idCompra = teclado.nextInt();
					detalhesCompra = detalhesCompraDAO.consultarBalanco(idCompra); //Pego o objeto pelo Id selecionado 
					
					System.out.println("Selecione a soma das parcelas do valor pago: ");
					valorPago = teclado.nextDouble();
					teclado.nextLine(); //Limpando o buffer
					detalhesCompra.setValorPago(valorPago);//Atualiza o valor pago
	
					double saldoDevedor = detalhesCompra.getIdProduto().getPreco() -  detalhesCompra.getValorPago();//Calcula o saldo devedor 
					detalhesCompra.setSaldoDevedor(saldoDevedor); //Atualiza o saldo devedor 
					
					System.out.println("Descricao: ");
					descricaoVenda = teclado.nextLine();
					detalhesCompra.setDescricao(descricaoVenda); //Atualiza a descricao
					
					detalhesCompraDAO.attDetalhesVenda(detalhesCompra); //E por fim atualizo no Banco
					break;
					
				
				case 8:
					System.out.println("\nSelecione o produto que voce deseja deletar: ");
					listaBalanco = detalhesCompraDAO.consultarBalancoCompras();
					for(DetalhesCompra balancoLista : listaBalanco) {
						System.out.println(
								"----------------------------\n" 
								+ balancoLista.getId() + " - " + balancoLista.getNome() 
								+ "\n  Produto: " + balancoLista.getIdProduto().getNome()
								+ "\n  Saldo Devedor: " +  formatador.format(balancoLista.getSaldoDevedor())
								+ "\n  Valor pago: " + formatador.format(balancoLista.getValorPago())
								+ "\n  Data da compra: " + balancoLista.getData()
								+ "\n  Descricao: " + balancoLista.getDescricao()
								);
					}
					
					idProduto = teclado.nextInt();
					detalhesCompraDAO.deletarBalanco(idProduto);
					break;
				
					
				case 9:
					listaBalanco = detalhesCompraDAO.consultarBalancoCompras();
					double saldoRua = 0;
					for(DetalhesCompra dt : listaBalanco) {
						saldoRua += dt.getSaldoDevedor();
					}	
					
					System.out.println("Saldo na rua: " + formatador.format(saldoRua));
					break;
				
					
				case 0:
					System.out.println("Tchau Coracaum <3");
					break;
				
					
				default:
					System.out.println("Opcao invalida");
			}
			
			
			
		}while(opc != 0);
	}
}
