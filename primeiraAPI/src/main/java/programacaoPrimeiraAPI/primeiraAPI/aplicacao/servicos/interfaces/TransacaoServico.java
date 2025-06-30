package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;

public interface TransacaoServico {

 
    Transacao salvarTransacao(TransacaoDTO dto);

    List<Transacao> listarTransacoes();

    void deletarTodasTransacoes();

    void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha);
}
  
