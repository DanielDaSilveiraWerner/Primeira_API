package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces;

import java.time.LocalDateTime;
import java.util.Optional;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.StatisticasDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;

public interface StatisticasServico {
    
    StatisticasDTO calculaStatistica();

    StatisticasDTO calculaStatisticasPorPerioso();
    StatisticasDTO removeStatistica(LocalDateTime dataInicial, LocalDateTime dataFinal);


    Optional<Transacao> findLastTransacao();
}
