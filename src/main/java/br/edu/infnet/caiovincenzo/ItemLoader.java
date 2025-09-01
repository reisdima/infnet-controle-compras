package br.edu.infnet.caiovincenzo;

import br.edu.infnet.caiovincenzo.model.domain.Compra;
import br.edu.infnet.caiovincenzo.model.domain.Item;
import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeNaoEncontradaException;
import br.edu.infnet.caiovincenzo.model.service.CompraService;
import br.edu.infnet.caiovincenzo.model.service.ItemService;
import br.edu.infnet.caiovincenzo.model.service.ProdutoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

@Order(4)
@Component
public class ItemLoader implements ApplicationRunner {
    private final ItemService itemService;
    private final ProdutoService produtoService;
    private final CompraService compraService;

    public ItemLoader(ItemService itemService, ProdutoService produtoService, CompraService compraService) {
        this.itemService = itemService;
        this.produtoService = produtoService;
        this.compraService = compraService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("itens.txt");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();

        String[] campos;

        System.out.println("[ItemLoader] Iniciando carregamento de itens do arquivo...");

        while (linha != null) {

            campos = linha.split(";");

            String codigoDeBarras = campos[0];
            Integer quantidade = Integer.parseInt(campos[1]);
            BigDecimal preco = new BigDecimal(campos[2]);
            String notaFiscal = campos[3];

            Produto produto;
            Compra compra;
            try {
                produto = produtoService.obterProdutoPorCodigoDeBarras(campos[0]);
                if(produto == null) {
                    System.err.println("  [ERRO] produto com codigo de barras " + codigoDeBarras + " não encontrado para o item ");
                    linha = leitura.readLine();
                    continue;
                }
                compra = compraService.obterPorNotaFiscal(notaFiscal);
                if(compra == null) {
                    System.err.println("  [ERRO] compra com nota fiscal " + notaFiscal + " não encontrada para o item ");
                    linha = leitura.readLine();
                    continue;
                }

            } catch (EntidadeNaoEncontradaException ex) {
                linha = leitura.readLine();
                continue;
            }

            Item item = new Item();
            item.setPreco(preco);
            item.setQuantidade(quantidade);
            item.setProduto(produto);
            item.setCompra(compra);

            itemService.incluir(item);

            linha = leitura.readLine();
        }

        List<Item> items = itemService.obterLista();
        items.forEach(System.out::println);

        leitura.close();
    }
}
