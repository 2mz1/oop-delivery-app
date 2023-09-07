package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * OptionGroup Entity
 */
public interface OptionGroup {

    Long getId();
    List<Option> getOptions();
    BigDecimal getTotalCost();
    void selectOne(Long optionId);
    void deselectOne(Long optionId);
}
