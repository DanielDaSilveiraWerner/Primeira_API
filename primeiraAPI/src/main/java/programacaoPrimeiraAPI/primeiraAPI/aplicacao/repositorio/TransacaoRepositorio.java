package programacaoPrimeiraAPI.primeiraAPI.aplicacao.repositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public List<Transacao> encontraHoraDepois (LocalDateTime limit){
List<Transacao> recente = new ArrayList<>();
for (Transacao transacao : listar){
    if (transacao.getDataHora()!= null && 
    (transacao.getDataHora().isEqual(limit) || transacao.getDataHora().isAfter(limit))) {
        recente.add(transacao);
    }
}
return recente;
}

 public List<Transacao> findByDataHoraBetween(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return listar.stream()
            .filter(i -> {
                if (i.getDataHora() == null) return false;
                return (i.getDataHora().isEqual(dataInicial) || i.getDataHora().isAfter(dataInicial))
                    && (i.getDataHora().isEqual(dataFinal) || i.getDataHora().isBefore(dataFinal));
            })
            .collect(Collectors.toList());
    }


    public Optional<Transacao> findLastTransacao() {
        return listar.stream()
            .filter(i -> i.getDataHora() != null)
            .max((i1, i2) -> i1.getDataHora().compareTo(i2.getDataHora()));
    }
 
    public void limparDataHora (LocalDateTime dataInicial, LocalDateTime dataFinal){
        listar.removeIf(i ->{
            if (i.getDataHora()== null) return false;
            return (i.getDataHora().isEqual(dataInicial) || i.getDataHora().isAfter(dataInicial))
            && (i.getDataHora().isEqual(dataFinal) || i.getDataHora().isBefore(dataFinal)); 
                
        });
    }
}
