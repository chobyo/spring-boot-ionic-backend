package com.chobyo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chobyo.cursomc.domains.Categoria;
import com.chobyo.cursomc.domains.Cidade;
import com.chobyo.cursomc.domains.Cliente;
import com.chobyo.cursomc.domains.Endereco;
import com.chobyo.cursomc.domains.Estado;
import com.chobyo.cursomc.domains.Pagamento;
import com.chobyo.cursomc.domains.PagamentoComBoleto;
import com.chobyo.cursomc.domains.PagamentoComCartao;
import com.chobyo.cursomc.domains.Pedido;
import com.chobyo.cursomc.domains.Produto;
import com.chobyo.cursomc.domains.enums.EstadoPagamento;
import com.chobyo.cursomc.domains.enums.TipoCliente;
import com.chobyo.cursomc.repositories.CategoriaRepository;
import com.chobyo.cursomc.repositories.CidadeRepository;
import com.chobyo.cursomc.repositories.ClienteRepository;
import com.chobyo.cursomc.repositories.EnderecoRepository;
import com.chobyo.cursomc.repositories.EstadoRepository;
import com.chobyo.cursomc.repositories.PagamentoRepository;
import com.chobyo.cursomc.repositories.PedidoRepository;
import com.chobyo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Urbelândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Santos", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cliente = new Cliente(null,"Maria Silva","maria@chobyo.com","22222222222",TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("1111-1111","2222-2222"));
		
		Endereco end1 = new Endereco(null, "Rua Serrote","111","ap. 1", "Vila Serra", "12345-002",cliente, cid1);
		Endereco end2 = new Endereco(null, "Rua Teste","222","ap. 2", "Vila 22222", "65478-002",cliente, cid2);
		
		cliente.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cliente));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente, end2);
		cliente.getPedidos().addAll(Arrays.asList(ped1, ped2));
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 0:00"), null);
		ped2.setPagamento(pagto2);
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}

}
