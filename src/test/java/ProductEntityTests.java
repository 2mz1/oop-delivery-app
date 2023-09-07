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
                List.of(new SingleOptionGroup(1L, "맛 선택", flavorOptionFactory()),
                        new SingleOptionGroup(2L, "떡 선택", tteokOptionFactory()),
                        new MultipleOptionGroup(3L, "토핑 추가 선택", new MaxLimitAmount(5), toppingOptionFactory()),
                        new MultipleOptionGroup(4L, "사이드 추가", new MaxLimitAmount(3), List.of(new Option(3447L, "배떡 치즈 타코야키", new BigDecimal(3_000)))),
                        new MultipleOptionGroup(5L, "튀김 추가선택", new MaxLimitAmount(8), List.of(new Option(923L, "고구마 튀김 2개", new BigDecimal(2_500))))));

        // given
        roseTteokbokki.addOption(1L, 1444L); // 0
        roseTteokbokki.addOption(3L, 522L);  // 4_000
        roseTteokbokki.addOption(3L, 624L);  // 3_000
        roseTteokbokki.addOption(5L, 923L);  // 2_500

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
                            new SingleOptionGroup(6L, "맛 선택", flavorOptionFactory()),
                            new SingleOptionGroup(7L, "떡 선택", tteokOptionFactory()),
                            new MultipleOptionGroup(8L, "토핑 추가 선택", new MaxLimitAmount(5),
                                    List.of(
                                            new Option(23214L, "스파게티면(150g) 추가", new BigDecimal(3_000)),
                                            new Option(764L, "분모자 추가", new BigDecimal(4_000)),
                                            new Option(346L, "분모자 추가 2", new BigDecimal(4_000)),
                                            new Option(453L, "분모자 추가 3", new BigDecimal(4_000)),
                                            new Option(6546L, "치즈 추가", new BigDecimal(3_000)),
                                            new Option(373L, "계란 2개 추가", new BigDecimal(1_400)))))));
    }

    private List<Option> flavorOptionFactory() {
        return List.of(
                new Option(1444L, "보통맛", BigDecimal.ZERO),
                new Option(413L, "순한맛", BigDecimal.ZERO),
                new Option(1L, "매운맛", BigDecimal.ZERO));
    }

    private List<Option> tteokOptionFactory() {
        return List.of(
                new Option(34342L, "쌀떡", BigDecimal.ZERO),
                new Option(232L, "밀떡", BigDecimal.ZERO));
    }

    private List<Option> toppingOptionFactory() {
        return List.of(
                new Option(44L, "스파게티면(150g) 추가", new BigDecimal(3_000)),
                new Option(522L, "분모자 추가", new BigDecimal(4_000)),
                new Option(624L, "치즈 추가", new BigDecimal(3_000)),
                new Option(755L, "계란 2개 추가", new BigDecimal(1_400))
        );
    }

}
