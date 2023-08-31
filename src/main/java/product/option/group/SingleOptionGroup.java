package product.option.group;

import product.option.Option;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Single Selectable Option Group
 * - 단일 옵션을 선택하는 옵션 그룹
 */
public class SingleOptionGroup extends AbstractOptionGroup {
    static public final Long CHOICE_NONE = -1L;

    private final String name;
    private Long selectedId;

    public SingleOptionGroup(Long optionGroupId, String name, List<Option> options) {
        super(optionGroupId, options);
        this.name = name;
        this.selectedId = CHOICE_NONE;
    }

    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getTotalCost()  {
        if (Objects.equals(selectedId, CHOICE_NONE)) return BigDecimal.ZERO;

        return options.stream()
                .filter(o -> o.getId().equals(selectedId))
                .findFirst()
                .orElseThrow().getCost();
    }

    @Override
    public void selectOne(Long optionId) {
        this.selectedId = optionId;
    }

    @Override
    public void deselectOne(Long optionId) {
        this.selectedId = CHOICE_NONE;
    }
}
