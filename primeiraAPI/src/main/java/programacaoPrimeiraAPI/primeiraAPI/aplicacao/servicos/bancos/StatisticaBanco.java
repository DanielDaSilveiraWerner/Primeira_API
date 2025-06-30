package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.StatisticasDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio.TransacaoRepositorio;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.StatisticasServico;


@Service
public class StatisticaBanco  implements StatisticasServico{
    private final TransacaoRepositorio repositorio;

    @Autowired
    public StatisticaBanco (TransacaoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public StatisticasDTO calculaStatistica(){
        
        LocalDateTime limit = LocalDateTime.now();
        List<Transacao> ultimasTransacos = repositorio.encontraHoraDepois(limit.minusSeconds(60));

        BigDecimal sum = BigDecimal.ZERO;

        BigDecimal min = null;

        BigDecimal max = null;

        for(Transacao transacao : ultimasTransacos){
            BigDecimal valor = transacao.getValor();
            sum = sum.add(valor);

            if (min == null || valor.compareTo(min) < 0) {
                min = valor;
            }

            if (max == null || valor.compareTo(max) > 0) {
                max = valor;
            }
        }

        
        int count = ultimasTransacos.size();
        BigDecimal avg = count > 0 ? sum.divide(BigDecimal.valueOf(count), 3, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        StatisticasDTO estatistica = new StatisticasDTO();
        estatistica.setCount(count);
        estatistica.setSum(sum);
        estatistica.setAvg(avg);
        estatistica.setMin(min != null ? min : BigDecimal.ZERO);
        estatistica.setMax(max != null ? max : BigDecimal.ZERO);


        return estatistica;
    }

    @Override
    public StatisticasDTO calculaStatisticasPorPerioso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculaStatisticasPorPerioso'");
    }

    @Override
    public StatisticasDTO removeStatistica(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeStatistica'");
    }

    @Override
    public Optional<Transacao> findLastTransacao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findLastTransacao'");
    }
    }



