package io.github.euristenede.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Euristenede Santos
 */
public class Output {

    private BigDecimal amount ;
    
    private LocalDate date;

    public Output(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
