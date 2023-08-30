import exception.InvalidLimitAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import product.Product;
import product.condition.amount.MaxLimitAmount;
import product.option.Option;
import product.option.group.MultipleOptionGroup;
import product.option.group.SingleOptionGroup;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Product Entity Tests
 */
public class ProductEntityTests {

    @Test
    public void 로제_떡볶이_옵션_합산_성공_케이스() {
        // when
        Product roseTteokbokki = new Product(
                "로제 떡볶이",
                "메추리알 + 비엔나 + 베이컨",
                new BigDecimal(10_000),
                List.of(new SingleOptionGroup(1, "맛 선택", flavorOptionFactory()),
                        new SingleOptionGroup(2, "떡 선택", tteokOptionFactory()),
                        new MultipleOptionGroup(3, "토핑 추가 선택", new MaxLimitAmount(5), toppingOptionFactory()),
                        new MultipleOptionGroup(4, "사이드 추가", new MaxLimitAmount(3), List.of(new Option(3447, "배떡 치즈 타코야키", new BigDecimal(3_000)))),
                        new MultipleOptionGroup(5, "튀김 추가선택", new MaxLimitAmount(8), List.of(new Option(923, "고구마 튀김 2개", new BigDecimal(2_500))))));

        // given
        roseTteokbokki.addOption(1, 1444); // 0
        roseTteokbokki.addOption(3, 522);  // 4_000
        roseTteokbokki.addOption(3, 624);  // 3_000
        roseTteokbokki.addOption(5, 923);  // 2_500

        // then
        System.out.println(roseTteokbokki.getName() + "가격: " + roseTteokbokki.getTotalCost());
        Assertions.assertEquals(roseTteokbokki.getTotalCost(), new BigDecimal(10000 + 4000 + 3000 + 2500));
    }

    @Test
    public void 로제_떡볶이_옵션_최대_개수_초과_실패_케이스() {
        assertThrows(InvalidLimitAmountException.class, () ->
            new Product("로제 떡볶이",
                    "메추리알 + 비엔나 + 베이컨",
                    new BigDecimal(10_000),
                    List.of(
                            new SingleOptionGroup(6, "맛 선택", flavorOptionFactory()),
                            new SingleOptionGroup(7, "떡 선택", tteokOptionFactory()),
                            new MultipleOptionGroup(8, "토핑 추가 선택", new MaxLimitAmount(5),
                                    List.of(
                                            new Option(23214, "스파게티면(150g) 추가", new BigDecimal(3_000)),
                                            new Option(764, "분모자 추가", new BigDecimal(4_000)),
                                            new Option(346, "분모자 추가 2", new BigDecimal(4_000)),
                                            new Option(453, "분모자 추가 3", new BigDecimal(4_000)),
                                            new Option(6546, "치즈 추가", new BigDecimal(3_000)),
                                            new Option(373, "계란 2개 추가", new BigDecimal(1_400)))))));
    }

    private List<Option> flavorOptionFactory() {
        return List.of(
                new Option(1444, "보통맛", BigDecimal.ZERO),
                new Option(413, "순한맛", BigDecimal.ZERO),
                new Option(1, "매운맛", BigDecimal.ZERO));
    }

    private List<Option> tteokOptionFactory() {
        return List.of(
                new Option(34342, "쌀떡", BigDecimal.ZERO),
                new Option(232, "밀떡", BigDecimal.ZERO));
    }

    private List<Option> toppingOptionFactory() {
        return List.of(
                new Option(44, "스파게티면(150g) 추가", new BigDecimal(3_000)),
                new Option(522, "분모자 추가", new BigDecimal(4_000)),
                new Option(624, "치즈 추가", new BigDecimal(3_000)),
                new Option(755, "계란 2개 추가", new BigDecimal(1_400))
        );
    }

}
