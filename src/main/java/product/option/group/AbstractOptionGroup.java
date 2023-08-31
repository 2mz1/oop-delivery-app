package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * Abstract Option Group Class
 */
public abstract class AbstractOptionGroup implements OptionGroup {

    protected final Long optionGroupId;
    protected List<Option> options;

    public AbstractOptionGroup(Long optionGroupId, List<Option> options) {
        assert !options.isEmpty();
        this.optionGroupId = optionGroupId;
        this.options = options;
    }

    @Override
    public Long getId() {
        return optionGroupId;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    public abstract BigDecimal getTotalCost();
    public abstract void selectOne(Long optionId);
    public abstract void deselectOne(Long optionId);
}
