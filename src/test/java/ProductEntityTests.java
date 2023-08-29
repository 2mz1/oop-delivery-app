import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import product.Option;
import product.Product;
import product.condition.amount.MaxLimitAmount;
import product.option.group.MultiOptionGroup;
import product.option.group.SingleOptionGroup;

/**
 * Product Entity Tests
 */
public class ProductEntityTests {

    @Test
    public void 로제_떡볶이_옵션_합산_성공_케이스() throws Exception {
        // give
        final List<Option> options = createOptionsTestData();
        final Product roseTteokbokki = createProductTestData(options.toArray(new Option[0]));

        // when
//        options.forEach(option -> roseTteokbokki.getOptions().stream().filter(o -> {
//            o.
//        }));

        // then
        assertEquals(new BigDecimal(10000 + 3000 + 4000 + 3000 + 1400 + 3000 + 2500), roseTteokbokki.getTotalCost());
    }

    private static List<Option> createOptionsTestData() throws NoSuchFieldException, IllegalAccessException {
        final Option flavor = new Option("보통맛", BigDecimal.ZERO);
        setPrivateField(flavor, "id", 99L);
        final Option type = new Option("밀떡", BigDecimal.ZERO);
        setPrivateField(type, "id", 100L);
        final Option spaghetti = new Option("스파게티면(150g) 추가", new BigDecimal(3_000));
        setPrivateField(spaghetti, "id", 1L);
        final Option bunmoja = new Option("분모자 추가", new BigDecimal(4_000));
        setPrivateField(bunmoja, "id", 2L);
        final Option cheese = new Option("치즈 추가", new BigDecimal(3_000));
        setPrivateField(cheese, "id", 3L);
        final Option egg = new Option("계란 2개 추가", new BigDecimal(1_400));
        setPrivateField(egg, "id", 4L);
        final Option sweetPotato = new Option("고구마 튀김 2개", new BigDecimal(2_500));
        setPrivateField(sweetPotato, "id", 5L);
        final Option tacoYaki = new Option("배떡 치즈 타코야키", new BigDecimal(3_000));
        setPrivateField(tacoYaki, "id", 6L);

        return List.of(flavor, type, spaghetti, bunmoja, cheese, egg, sweetPotato, tacoYaki);
    }

    private static Product createProductTestData(Option... options) {
        final Product roseTteokbokki = new Product(
                "로제 떡볶이",
                "메추리알 + 비엔나 + 베이컨",
                new BigDecimal(10_000),
                List.of(
                        new SingleOptionGroup("맛 선택", options[0]),
                        new SingleOptionGroup("떡 선택", options[1]),
                        new MultiOptionGroup(
                                "토핑 추가 선택",
                                new MaxLimitAmount(4),
                                List.of(
                                        new SingleOptionGroup("토핑1", options[2]),
                                        new SingleOptionGroup("토핑2", options[3]),
                                        new SingleOptionGroup("토핑3", options[4]),
                                        new SingleOptionGroup("토핑4", options[5])
                                )
                        ),
                        new MultiOptionGroup(
                                "사이드 추가",
                                new MaxLimitAmount(Integer.MAX_VALUE),
                                List.of(new SingleOptionGroup("신상", options[6]))
                        ),
                        new MultiOptionGroup(
                                "튀김 추가 선택",
                                new MaxLimitAmount(8),
                                List.of(new SingleOptionGroup("튀김1", options[7]))
                        )
                ));
        return roseTteokbokki;
    }

    private static void setPrivateField(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);  // 필드 이름을 기반으로 필드 객체를 가져옵니다.
        field.setAccessible(true);  // private 필드에 접근할 수 있도록 합니다.
        field.set(object, value);  // 해당 객체의 필드에 값을 설정합니다.
    }
}
