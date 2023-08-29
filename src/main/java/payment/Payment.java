package payment;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class Payment {

    String orderTitle;
    BigDecimal total;

    public void pay() {

    }
}
