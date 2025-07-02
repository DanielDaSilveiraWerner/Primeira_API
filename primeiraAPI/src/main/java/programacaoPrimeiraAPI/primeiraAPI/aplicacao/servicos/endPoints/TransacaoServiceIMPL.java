package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.endPoints;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio.TransacaoRepositorio;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

@Service
public class TransacaoServiceIMPL implements TransacaoServico{

private final TransacaoRepositorio repositorio;

@Autowired
public TransacaoServiceIMPL (TransacaoRepositorio repositorio){
    this.repositorio = repositorio;
}

    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
       if (dto == null || dto.getValor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos obrigatórios ausentes");
       }

       if (dto.getValor().compareTo(BigDecimal.ZERO) < 0) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Valor não pode ser negativo.");
       }
       Transacao transacao = new Transacao();


       transacao.setDataHora(LocalDateTime.now());

       transacao.setValor(dto.getValor());

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
        throw new UnsupportedOperationException("Unimplemented method 'deletarPorPeriodo'");
    }
    
}
