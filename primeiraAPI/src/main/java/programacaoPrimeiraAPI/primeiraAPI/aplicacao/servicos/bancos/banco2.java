package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio.TransacaoRepositorio;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

@Service
public class banco2 implements TransacaoServico{

private final TransacaoRepositorio repositorio;

public banco2 (TransacaoRepositorio repositorio){
    this.repositorio = repositorio;
}

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        if (dto.getDataHora() == null || dto.getDataHora().getYear() != 2025) {
            return null;
        }

        Transacao transacao = new Transacao(dto);
        repositorio.adicionaTransacao(transacao);
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
       return repositorio.listaTransacaos().stream().filter
       (t -> t.getValor().compareTo(new java.math.BigDecimal("1000.00"))<= 0).collect(Collectors.toList());
    }

    @Override
    public void deletarTodasTransacoes() {
        throw new UnsupportedOperationException("Para delar todas, use: deletarTodasTransacoes com esta senha 'BD2@456'");
    }

    
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
     if (!"BD2@456".equals(senha)) {
        throw new RuntimeException("Senha incorreta");
     }
           repositorio.limparDataHora(inicio, fim);     
            }
        }
    
