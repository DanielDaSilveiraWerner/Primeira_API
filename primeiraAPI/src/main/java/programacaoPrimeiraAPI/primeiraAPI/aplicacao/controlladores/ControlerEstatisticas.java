package programacaoPrimeiraAPI.primeiraAPI.aplicacao.controlladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.PeriodosDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.StatisticasDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.StatisticasServico;

@RestController
@RequestMapping("/estatisticas")
public class ControlerEstatisticas {
    
    @Autowired
    private StatisticasServico estatisticaServico;

    @GetMapping
    public StatisticasDTO getStatisticas(){
        return estatisticaServico.calculaStatistica();
    }

    @GetMapping("/periodo")
    public StatisticasDTO getBuscaTodosOsPeriodos(@RequestBody PeriodosDTO periodo){
        return estatisticaServico.calculaStatisticasPorPerioso(periodo);
    }

    @GetMapping("/ultima")
    public ResponseEntity<Transacao> buscaUltimaTransacao(){
        Optional<Transacao> ultima = estatisticaServico.findLastTransacao();

        if (ultima.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ultima.get(), HttpStatus.OK);
    }

    @DeleteMapping("/periodo")
    public ResponseEntity<StatisticasDTO> excluirAsTransacoesPorPeriodo(
        @RequestParam("dataInicial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam("dataFinal") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
            StatisticasDTO resposta = estatisticaServico.removeStatistica(dataInicial, dataFinal);


            if (resposta == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(resposta);
        }
    
}
