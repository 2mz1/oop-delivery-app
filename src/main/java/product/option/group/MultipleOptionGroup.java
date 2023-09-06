package product.option.group;

import exception.AlreadySelectedOptionException;
import exception.InvalidLimitAmountException;
import product.condition.amount.LimitAmount;
import product.option.Option;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Multiple Selectable Option Group
 * - 여러 개의 옵션을 추가할 수 있는 옵션 그룹
 */
public class MultipleOptionGroup extends AbstractOptionGroup {
    private final String name;
    private final LimitAmount limitAmount;
    private final Set<Long> selectedIdList;

    public MultipleOptionGroup(Long optionGroupId, String name, LimitAmount limitAmount, List<Option> options) {
        super(optionGroupId, options);

        this.name = name;
        this.limitAmount = limitAmount;
        this.selectedIdList = new HashSet<>();

        checkLimitedAmount();
    }

    public String getName() {
        return name;
    }

    private void checkLimitedAmount() {
        if (!limitAmount.isSatisfied(options.size())) {
            throw new InvalidLimitAmountException("cannot add option by limit condition");
        }
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
        if (selectedIdList.contains(optionId))
            throw new AlreadySelectedOptionException();

        checkLimitedAmount();

        selectedIdList.add(optionId);
    }

    @Override
    public void deselectOne(Long optionId) {
        selectedIdList.remove(optionId);
    }
}
