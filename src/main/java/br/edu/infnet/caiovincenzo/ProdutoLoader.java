package br.edu.infnet.caiovincenzo;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.service.ProdutoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Component
public class ProdutoLoader implements ApplicationRunner {
    private final ProdutoService produtoService;

    public ProdutoLoader(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("produto.txt");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null) {

            campos = linha.split(";");

            Produto produto = new Produto();
            produto.setNome(campos[0]);
            produto.setMarca(campos[1]);
            produto.setCodigoDeBarras(campos[2]);
            produto.setQuantidade(Integer.valueOf(campos[3]));
            produto.setUnidade(campos[4]);
            produto.setPreco(Double.parseDouble(campos[5]));


            produtoService.incluir(produto);

            linha = leitura.readLine();
        }

        List<Produto> produtos = produtoService.obterLista();
        produtos.forEach(System.out::println);

        leitura.close();
    }
}
