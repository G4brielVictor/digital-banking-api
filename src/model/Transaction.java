package model;

import model.enums.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {
    /*"A classe Transaction representa um fato histórico e contábil, que uma vez ocorrido, jamais pode ser adulterado. Utilizei o modificador final
     e suprimi os setters para garantir a imutabilidade do objeto. Isso assegura a integridade e a rastreabilidade dos dados bancários em conformidade com as regras de auditoria."*/
    private final UUID id = UUID.randomUUID();
    private final TransactionType type;
    private final Double amount;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String description;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Transaction(TransactionType type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "\n\n---------------------------------------\n" +
               "ID: " + getId() +
               "\nData/hora: " + FORMATTER.format(getTimeStamp()) +
               "\nTipo: " + getType() +
               "\nValor: " + String.format("R$ %.2f", amount) +
               "\nDescriço: " + description +
               "\n---------------------------------------\n\n";
    }
}
