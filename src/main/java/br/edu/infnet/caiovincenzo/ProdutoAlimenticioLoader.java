package br.edu.infnet.caiovincenzo;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import br.edu.infnet.caiovincenzo.model.service.ProdutoAlimenticioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Component
public class ProdutoAlimenticioLoader implements ApplicationRunner {
    private final ProdutoAlimenticioService produtoAlimenticioService;

    public ProdutoAlimenticioLoader(ProdutoAlimenticioService produtoAlimenticioService) {
        this.produtoAlimenticioService = produtoAlimenticioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("produtoAlimenticio.txt");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();

        String[] campos;

        while (linha != null) {

            campos = linha.split(";");

            ProdutoAlimenticio produto = new ProdutoAlimenticio();
            produto.setNome(campos[0]);
            produto.setMarca(campos[1]);
            produto.setCodigoDeBarras(campos[2]);
            produto.setQuantidade(Integer.valueOf(campos[3]));
            produto.setUnidade(campos[4]);
            produto.setPreco(Double.parseDouble(campos[5]));
            produto.setPerecivel(Boolean.parseBoolean(campos[6]));
            produto.setDiasValidade(Integer.parseInt(campos[7]));


            produtoAlimenticioService.incluir(produto);

            linha = leitura.readLine();
        }

        List<ProdutoAlimenticio> produtos = produtoAlimenticioService.obterLista();
        produtos.forEach(System.out::println);

        leitura.close();
    }
}
