package programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;

@Repository
public class TransacaoRepositorio {
    
    private final List<Transacao> listar = new ArrayList<>();

private static long contaId = 1;

public void adicionaTransacao(Transacao transacao){
    transacao.setId(contaId++);
    listar.add(transacao);
}

public void deletaTransacao(){
    listar.clear();
}

public List<Transacao> listaTransacaos(){
    return new ArrayList<>(listar);
}

}
