package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * Abstract Option Group Class
 */
public abstract class AbstractOptionGroup implements OptionGroup {

    protected final Integer optionGroupId;
    protected List<Option> options;

    public AbstractOptionGroup(Integer optionGroupId, List<Option> options) {
        assert !options.isEmpty();
        this.optionGroupId = optionGroupId;
        this.options = options;
    }

    @Override
    public Integer getId() {
        return optionGroupId;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    public abstract BigDecimal getTotalCost();
    public abstract void selectOne(Integer optionId);
    public abstract void deselectOne(Integer optionId);
}
