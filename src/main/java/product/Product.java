package product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import product.option.group.OptionGroup;

/**
 * Product Entity
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    private Long id;
    private String name;
    private String desc;
    private List<OptionGroup> optionGroups = new ArrayList<>();
    private BigDecimal price;

    public Product(String name, String desc, BigDecimal price, List<OptionGroup> options) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.optionGroups.addAll(options);
    }

    public BigDecimal getTotalCost() {
        BigDecimal total = price;

        for (OptionGroup optionGroup: optionGroups) {
            total = total.add(optionGroup.getTotalCost());
        }

        return total;
    }

    public String getName() {
        return name;
    }

    public void addOption(Long optionGroupId, Long optionId) {
        optionGroups.stream()
                .filter(optionGroup -> optionGroup.getId().equals(optionGroupId))
                .findFirst()
                .orElseThrow()
                .selectOne(optionId);
    }
}

