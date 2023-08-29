package product.option.group;

import lombok.Getter;
import product.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * 단일 옵션을 선택하는 옵션 그룹
 */
@Getter
public class SingleOptionGroup implements OptionGroup {
    private final String groupName;
    private Option option;
    private boolean selected;

    public SingleOptionGroup(String groupName, Option option) {
        this.groupName = groupName;
        this.option = option;
    }

    @Override
    public BigDecimal getTotalCost() {
        if (selected) {
            return option.getCost();
        }

        return BigDecimal.ZERO;
    }

    public void modifyOption(Option option) {
        this.option = option;
    }

    @Override
    public void selectOption(Long optionId) {
        if (optionId == null){
            throw new IllegalArgumentException("cannot select option");
        }

        if (!option.getId().equals(optionId)) {
            throw new IllegalArgumentException("not matched option id");
        }

        selected = true;
    }
}
