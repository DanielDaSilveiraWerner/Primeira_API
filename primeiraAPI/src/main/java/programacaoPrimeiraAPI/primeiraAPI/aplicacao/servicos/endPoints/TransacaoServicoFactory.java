 package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.endPoints;

import java.time.LocalDateTime;
import java.util.List;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

public class TransacaoServicoFactory implements TransacaoServico {

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarTransacao'");
    }

    @Override
    public List<Transacao> listarTransacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTransacoes'");
    }

    @Override
    public void deletarTodasTransacoes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarTodasTransacoes'");
    }

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarPorPeriodo'");
    }

    
}