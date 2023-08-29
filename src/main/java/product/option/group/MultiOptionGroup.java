package product.option.group;

import exception.InvalidLimitAmountException;
import lombok.Getter;
import product.Option;
import product.condition.amount.LimitAmount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 여러 개의 옵션을 추가할 수 있는 옵션 그룹
 */
@Getter
public class MultiOptionGroup implements OptionGroup {
    private final String groupName;
    private LimitAmount limitAmount;
    private final List<SingleOptionGroup> multiOptionGroup = new ArrayList<>();

    public MultiOptionGroup(String groupName, LimitAmount limitAmount, List<SingleOptionGroup> multiOptionGroup) {
        if (multiOptionGroup != null && !limitAmount.isSatisfied(multiOptionGroup.size())) {
            throw new InvalidLimitAmountException("cannot add option by limit condition");
        }

        this.groupName = groupName;
        this.limitAmount = limitAmount;
        this.multiOptionGroup.addAll(multiOptionGroup);
    }

    @Override
    public BigDecimal getTotalCost() {
        return multiOptionGroup.stream()
                               .filter(SingleOptionGroup::isSelected)
                               .map(SingleOptionGroup::getTotalCost)
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void selectOption(Long optionId) {
        if (optionId == null) {
            throw new IllegalArgumentException("cannot select option");
        }

        multiOptionGroup.stream()
                        .filter(option -> option.getOption().getId().equals(optionId))
                        .findFirst()
                        .ifPresent(option -> option.selectOption(optionId));
    }
}
