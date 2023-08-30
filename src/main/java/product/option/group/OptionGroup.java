package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * OptionGroup Entity
 */
public interface OptionGroup {

    Integer getId();
    List<Option> getOptions();
    BigDecimal getTotalCost();
    void selectOne(Integer optionId);
    void deselectOne(Integer optionId);
}
