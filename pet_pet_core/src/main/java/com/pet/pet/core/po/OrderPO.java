package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import java.math.BigDecimal;

@Entity(name = OrderPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class OrderPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_order";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("下单用户")
    private UserPO user;

    @Column(nullable = false, length = 30)
    @Comment("订单类型")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(nullable = false, precision = 10, scale = 2)
    @Comment("订单金额")
    private BigDecimal amount;

    @Column(length = 30)
    @Comment("支付方式")
    private String paymentMethod;

    @Column(length = 30)
    @Comment("订单状态")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(length = 200)
    @Comment("商品详情（JSON）")
    private String itemsJson;

    public enum OrderType {
        MEMBERSHIP, 
        VIRTUAL_ITEM, 
        ALBUM_PRODUCTION, // 纪念相册制作
        HEALTH_REPORT     // 健康报告
    }

    public enum Status {
        PENDING, PAID, CANCELLED, REFUNDED
    }
} 