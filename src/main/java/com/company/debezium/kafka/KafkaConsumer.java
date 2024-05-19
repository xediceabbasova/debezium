package com.company.debezium.kafka;

import com.company.debezium.model.ProductMessageCDC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topics = "product.public.product",
            groupId = "product-group"
    )
    public void debeziumListener(ProductMessageCDC productMessageCDC) {
        if (productMessageCDC.getOp().equals("c")) {
            int tempStock = productMessageCDC.getAfter().getStock();
            log.info("Debeziumdan alinan degisiklik : Stock : {} ", tempStock);
        }

        if (productMessageCDC.getOp().equals("u")) {
            if (productMessageCDC.getAfter().getStock() == 0) {
                log.info("Debeziumdan alinan degisiklik : Stock : {} dan {} dustu lutfen stock geldiginde bildirim almak icin tiklayiniz. !",
                        productMessageCDC.getBefore().getStock(),
                        productMessageCDC.getAfter().getStock());
            }
        }
    }
}
