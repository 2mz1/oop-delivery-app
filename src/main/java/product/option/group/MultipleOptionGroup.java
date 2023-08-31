package product.option.group;

import exception.InvalidLimitAmountException;
import product.condition.amount.LimitAmount;
import product.option.Option;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Multiple Selectable Option Group
 * - 여러 개의 옵션을 추가할 수 있는 옵션 그룹
 */
public class MultipleOptionGroup extends AbstractOptionGroup {
    private final String name;
    private final LimitAmount limitAmount;
    private List<Long> selectedIdList;

    public MultipleOptionGroup(Long optionGroupId, String name, LimitAmount limitAmount, List<Option> options) {
        super(optionGroupId, options);

        if (!limitAmount.isSatisfied(options.size())) {
            throw new InvalidLimitAmountException("cannot add option by limit condition");
        }

        this.name = name;
        this.limitAmount = limitAmount;
        this.selectedIdList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    @Override
    public BigDecimal getTotalCost()  {
        if (selectedIdList.isEmpty()) return BigDecimal.ZERO;

        return options.stream()
                .filter(o -> selectedIdList.contains(o.getId()))
                .map(Option::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public void selectOne(Long optionId) {
        selectedIdList.add(optionId);
    }

    @Override
    public void deselectOne(Long optionId) {
        selectedIdList.remove(optionId);
    }
}
