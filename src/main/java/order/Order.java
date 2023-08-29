package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Value;
import payment.Payment;
import product.Product;

@Value
public class Order {
    List<Product> products = new ArrayList<>();
    ReceivedWay receivedWay;

    public Payment toPayment() {
        if (products.isEmpty()) {
            throw new IllegalStateException("주문할 상품이 없습니다.");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Product product: products) {
            total = total.add(product.getTotalCost());
        }

        //TODO 결제하려는 상품의 대표 상품명 만들기

        //TODO 결제 금액 계산하기(포인트를 사용한 경우. 계산이 필요함. 신용카드와 포인트를 조합하는 경우.)

        return new Payment("결제하려는 상품의 이름들?", total);
    }
}
