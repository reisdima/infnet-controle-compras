package br.edu.infnet.caiovincenzo;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import br.edu.infnet.caiovincenzo.model.domain.enums.Categoria;
import br.edu.infnet.caiovincenzo.model.domain.enums.TipoUnidade;
import br.edu.infnet.caiovincenzo.model.service.ProdutoGeralService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Order(2)
@Component
public class ProdutoGeralLoader implements ApplicationRunner {
    private final ProdutoGeralService produtoGeralService;

    public ProdutoGeralLoader(ProdutoGeralService produtoGeralService) {
        this.produtoGeralService = produtoGeralService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("produtoGeral.txt");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null) {

            campos = linha.split(";");

            ProdutoGeral produto = new ProdutoGeral();
            produto.setNome(campos[0]);
            produto.setMarca(campos[1]);
            produto.setCodigoDeBarras(campos[2]);
            produto.setQuantidade(Integer.valueOf(campos[3]));
            produto.setUnidade(TipoUnidade.valueOf(campos[4]));
            produto.setCategoria(Categoria.valueOf(campos[5]));


            produtoGeralService.incluir(produto);

            linha = leitura.readLine();
        }

        List<ProdutoGeral> produtos = produtoGeralService.obterLista();
        produtos.forEach(System.out::println);

        leitura.close();
    }
}
