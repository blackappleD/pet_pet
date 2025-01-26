package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = ExpensePO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class ExpensePO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_expense";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("关联宠物")
    private PetPO pet;

    @Column(nullable = false, length = 20)
    @Comment("消费类型")
    private String category;

    @Column(nullable = false)
    @Comment("金额")
    private BigDecimal amount;

    @Column
    @Comment("消费时间")
    private LocalDateTime expenseTime;

    @Column(length = 200)
    @Comment("备注")
    private String remark;

    @Column(length = 200)
    @Comment("凭证图片URL")
    private String receiptUrl;
} 