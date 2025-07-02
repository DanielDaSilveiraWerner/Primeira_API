package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio.TransacaoRepositorio;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

@Service
public class banco1 implements TransacaoServico{

    private final TransacaoRepositorio repositorio;

    public banco1 (TransacaoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        if (dto.getValor().compareTo(new java.math.BigDecimal("5.00"))<0) {
            return null;
        }

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
      LocalDateTime limit = LocalDateTime.now().minusYears(3);
      List<Transacao> antiga = repositorio.listaTransacaos().stream().filter
      (t -> t.getDataHora().isBefore(limit)).collect(Collectors.toList());

      antiga.forEach(t -> repositorio.listaTransacaos().remove(t));
    }
    

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
      if (!"BD1@123".equals(senha)) {
        throw new RuntimeException("Senha errada");


      }


      List<Transacao> trasacoesParaDeletar = repositorio.listaTransacaos().stream().filter(t -> t.getDataHora()
      .isAfter(inicio) && t.getDataHora().isBefore(fim)).collect(Collectors.toList());

      trasacoesParaDeletar.forEach(t -> repositorio.listaTransacaos().remove(t));
    }
    
}
