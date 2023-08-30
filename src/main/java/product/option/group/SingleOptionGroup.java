package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;

/**
 * Single Selectable Option Group
 * - 단일 옵션을 선택하는 옵션 그룹
 */
public class SingleOptionGroup extends AbstractOptionGroup {
    int CHOICE_NOTHING = -1;

    private final String name;
    private Integer selectedId;

    public SingleOptionGroup(Integer optionGroupId, String name, List<Option> options) {
        super(optionGroupId, options);
        this.name = name;
        this.selectedId = CHOICE_NOTHING;
    }

    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getTotalCost()  {
        if (selectedId == CHOICE_NOTHING) return BigDecimal.ZERO;

        return options.stream()
                .filter(o -> o.getId().equals(selectedId))
                .findFirst()
                .orElseThrow().getCost();
    }

    @Override
    public void selectOne(Integer optionId) {
        this.selectedId = optionId;
    }

    @Override
    public void deselectOne(Integer optionId) {
        this.selectedId = CHOICE_NOTHING;
    }
}
