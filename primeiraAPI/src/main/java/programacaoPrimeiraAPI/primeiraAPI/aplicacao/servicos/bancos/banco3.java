package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio.TransacaoRepositorio;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

@Service
public class banco3 implements TransacaoServico{


    private final TransacaoRepositorio repositorio;

    public banco3 (TransacaoRepositorio repositorio){
        this.repositorio = repositorio;
    }
    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        Transacao transacao = new Transacao(dto);
        repositorio.adicionaTransacao(transacao);
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
        return repositorio.listaTransacaos();
    }

    @Override
    public void deletarTodasTransacoes() {
         repositorio.deletaTransacao();
    }

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
        if (!"BD3@789".equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }
        repositorio.limparDataHora(inicio, fim);
    }
    
}
