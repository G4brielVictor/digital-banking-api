package exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

/*Retornar boolean ou null gera código propenso a NullPointerException e mascara a causa raiz da falha (se foi senha errada, limite excedido ou saldo insuficiente).
Ao utilizar exceções de domínio semânticas estendendo RuntimeException, eu consigo interromper o fluxo inválido no momento exato em que ele ocorre
 (Fail-Fast) e propagar mensagens de erro ricas e precisas para as camadas superiores (como o console ou, futuramente, um @ControllerAdvice em uma API REST*/
