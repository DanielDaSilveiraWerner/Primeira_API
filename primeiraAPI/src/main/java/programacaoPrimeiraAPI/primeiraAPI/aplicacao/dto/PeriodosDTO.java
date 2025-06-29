package programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodosDTO {
    
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
