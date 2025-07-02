 package programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.endPoints;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos.banco1;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos.banco2;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos.banco3;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.interfaces.TransacaoServico;

@Service
public class TransacaoServicoFactory implements TransacaoServico {

private final banco1 banco1servico;
private final banco2 banco2servico;
private final banco3 banco3servico;

public TransacaoServicoFactory (banco1 banco1servico, banco2 banco2servico, banco3 banco3servico){
    this.banco1servico = banco1servico; 
    this.banco2servico = banco2servico;
    this.banco3servico = banco3servico;
}

public TransacaoServico getServiceByBanco(String banco){
    if (banco == null) {
        throw new IllegalArgumentException("Banco não informado");
    }

    switch (banco.toLowerCase()) {
        case "banco1":
            return banco1servico;
        case "banco2":
            return banco2servico;
        case "banco3":
            return banco3servico;
        default:
        throw new IllegalArgumentException("Banco inválido: " + banco);
    }
}
    @Override
    public Transacao salvarTransacao(TransacaoDTO dto) {
        return getServiceByBanco (dto.getBanco()).salvarTransacao(dto);
    }

    @Override
    public List<Transacao> listarTransacoes() {
        throw new UnsupportedOperationException("Listar transações deve ser feita por banco");
    }

    @Override
    public void deletarTodasTransacoes() {
        throw new UnsupportedOperationException("Deletar todas as transações não realizado");
    }

    @Override
    public void deletarPorPeriodo(LocalDateTime inicio, LocalDateTime fim, String senha) {
        throw new UnsupportedOperationException("Use o método específico por banco");
    }

    public void deletaPorPeriodoBancon(String banco, LocalDateTime inicio, LocalDateTime fim, String senha){
        if (!"BD1@123".equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }
        getServiceByBanco(banco).deletarPorPeriodo(inicio, fim, senha);
    }
}