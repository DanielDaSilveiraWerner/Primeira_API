package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.endPoints;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.PeriodosDTO;
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
    public StatisticasDTO calculaStatisticasPorPerioso(PeriodosDTO periodo) {
        List<Transacao> transacoes = repositorio.findByDataHoraBetween(periodo.getDataInicial(), periodo.getDataFinal());

       BigDecimal sum = BigDecimal.ZERO;
       BigDecimal min = null;
       BigDecimal max = null;


       for(
        Transacao transacao : transacoes){
            BigDecimal valor = transacao.getValor();
            sum = sum.add(valor);

            if (min == null || min.compareTo(min) <0) {
                min = valor;
            }

            if (max == null || max.compareTo(max) > 0) {
                max = valor;
            }
        }
       
        int count = transacoes.size();

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
    public StatisticasDTO removeStatistica(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<Transacao> transacoes = repositorio.findByDataHoraBetween(dataInicial, dataFinal);

        if (transacoes.isEmpty()){
            return new StatisticasDTO();
        }
        StatisticasDTO estatisticas = new StatisticasDTO();
        estatisticas.setCount(transacoes.size());
        estatisticas.setSum(transacoes.stream().map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add));
        estatisticas.setAvg(estatisticas.getSum().divide(BigDecimal.valueOf(estatisticas.getCount()), BigDecimal.ROUND_HALF_UP));
        estatisticas.setMin(transacoes.stream().map(Transacao::getValor).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO));
        estatisticas.setMax(transacoes.stream().map(Transacao::getValor).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO));

        repositorio.limparDataHora(dataInicial, dataFinal);
        return estatisticas;
    }

    @Override
    public Optional<Transacao> findLastTransacao() {
        // TODO Auto-generated method stub

        Optional<Transacao> ultimaTransacao = repositorio.findLastTransacao();

        ultimaTransacao.ifPresentOrElse(t -> {
            System.out.println("Ultima transacao ocorreu " + t.getValor() + "às " + t.getDataHora());
        },() -> System.out.println("Nenhuma transacao ocorrida"));

        return ultimaTransacao;
    }
    }



