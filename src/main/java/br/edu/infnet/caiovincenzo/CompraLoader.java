package br.edu.infnet.caiovincenzo;

import br.edu.infnet.caiovincenzo.model.domain.Compra;
import br.edu.infnet.caiovincenzo.model.service.CompraService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Order(3)
@Component
public class CompraLoader implements ApplicationRunner {
    private final CompraService compraService;

    public CompraLoader(CompraService compraService) {
        this.compraService = compraService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader arquivo = new FileReader("compras.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linha = leitura.readLine();

        String[] campos;

        while (linha != null) {

            campos = linha.split(";");

            Compra compra = new Compra();

            compra.setDataDaCompra(LocalDate.parse(campos[0], formatter));
            compra.setEstabelecimento(campos[1]);
            compra.setNotaFiscal(campos[2]);

            compraService.incluir(compra);

            linha = leitura.readLine();
        }

        List<Compra> compras = compraService.obterLista();
        compras.forEach(System.out::println);

        leitura.close();
    }
}
