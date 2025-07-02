package programacaoPrimeiraAPI.primeiraAPI.aplicacao.controlladores;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.dialect.function.TruncFunction.DatetimeTrunc;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import programacaoPrimeiraAPI.primeiraAPI.aplicacao.dto.TransacaoDTO;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.model.Transacao;
import programacaoPrimeiraAPI.primeiraAPI.aplicacao.servicos.bancos.TransacaoServicoFactory;

@RestController
@RequestMapping("/transacao") 
public class ControlerTransacao {

    private final TransacaoServicoFactory transacaoServicoFactory;

    public ControlerTransacao(TransacaoServicoFactory transacaoServicoFactory){
        this.transacaoServicoFactory = transacaoServicoFactory;
    }

    @GetMapping
    public ResponseEntity<List <Transacao>> listarTransacoes(@RequestParam (required = false) String banco){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    } 

    @PostMapping
    public ResponseEntity<Transacao> criarTransacao(@RequestBody TransacaoDTO dto){
        try{
            Transacao transacao = transacaoServicoFactory.salvarTransacao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping
    public ResponseEntity<Void> deletarTodasAsTransacoes(@RequestParam (required = false) String banco){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @DeleteMapping("/periodo")
    public ResponseEntity<?> deletarTransacaoPorPeriodo (
        @RequestParam String banco,
        @RequestParam String senha,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial, 
        @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
          if (!"BD1@123".equals(senha)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Senha incorreta");
          }   

          try {
            transacaoServicoFactory.deletaPorPeriodoBancon(banco, dataInicial, dataFinal, senha);
            return ResponseEntity.noContent().build();
          } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
          } catch (Exception e ) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
          }
        }
}