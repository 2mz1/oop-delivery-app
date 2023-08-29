package product;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Option Entity
 */
@Getter
@NoArgsConstructor
public class Option {
    private Long id;
    private String name;
    private BigDecimal cost;

    public Option(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }
}
