import exception.InvalidLimitAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import product.Product;
import product.condition.amount.MaxLimitAmount;
import product.option.Option;
import product.option.group.MultiSelectOptionGroup;
import product.option.group.SingleSelectOptionGroup;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Product Entity Tests
 */
public class ProductEntityTests {

    @Test
    public void 로제_떡볶이_옵션_합산_성공_케이스() {
        Product roseTteokbokki = new Product(
                "로제 떡볶이",
                "메추리알 + 비엔나 + 베이컨",
                new BigDecimal(10_000),
                List.of(
                        new SingleSelectOptionGroup("맛 선택", new Option("보통맛", BigDecimal.ZERO)),
                        new SingleSelectOptionGroup("떡 선택", new Option("밀떡", BigDecimal.ZERO)),
                        new MultiSelectOptionGroup(
                                "토핑 추가 선택",
                                new MaxLimitAmount(5),
                                List.of(
                                        new Option("스파게티면(150g) 추가", new BigDecimal(3_000)),
                                        new Option("분모자 추가", new BigDecimal(4_000)),
                                        new Option("치즈 추가", new BigDecimal(3_000)),
                                        new Option("계란 2개 추가", new BigDecimal(1_400))
                                )
                        ),
                        new MultiSelectOptionGroup(
                                "사이드 추가",
                                new MaxLimitAmount(3),
                                List.of(
                                        new Option("배떡 치즈 타코야키", new BigDecimal(3_000))
                                )
                        ),
                        new MultiSelectOptionGroup(
                                "튀김 추가선택",
                                new MaxLimitAmount(8),
                                List.of(
                                        new Option("고구마 튀김 2개", new BigDecimal(2_500))
                                )
                        )
                )
        );

        System.out.println(roseTteokbokki.getName() + "가격: " + roseTteokbokki.getTotalCost());
        Assertions.assertEquals(roseTteokbokki.getTotalCost(), new BigDecimal(10000 + 3000 + 4000 + 3000 + 1400 + 3000 + 2500));
    }

    @Test
    public void 로제_떡볶이_옵션_최대_개수_초과_실패_케이스() {
        assertThrows(InvalidLimitAmountException.class, () ->
            new Product(
                    "로제 떡볶이",
                    "메추리알 + 비엔나 + 베이컨",
                    new BigDecimal(10_000),
                    List.of(
                            new SingleSelectOptionGroup("맛 선택", new Option("보통맛", BigDecimal.ZERO)),
                            new SingleSelectOptionGroup("떡 선택", new Option("밀떡", BigDecimal.ZERO)),
                            new MultiSelectOptionGroup(
                                    "토핑 추가 선택",
                                    new MaxLimitAmount(5),
                                    List.of(
                                            new Option("스파게티면(150g) 추가", new BigDecimal(3_000)),
                                            new Option("분모자 추가", new BigDecimal(4_000)),
                                            new Option("분모자 추가 2", new BigDecimal(4_000)),
                                            new Option("분모자 추가 3", new BigDecimal(4_000)),
                                            new Option("치즈 추가", new BigDecimal(3_000)),
                                            new Option("계란 2개 추가", new BigDecimal(1_400))
                                    )
                            )
                    )
            ));
    }
}
