package product.option;

import java.math.BigDecimal;

/**
 * Option Entity
 */
public class Option {
    private final Integer id;
    private final String name;
    private final BigDecimal cost;

    public Option(Integer id, String name, BigDecimal cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
