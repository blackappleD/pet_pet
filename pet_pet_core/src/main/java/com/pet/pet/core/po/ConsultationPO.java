package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;

@Entity(name = ConsultationPO.TABLE_NAME)
@Getter @Setter
@FieldNameConstants
public class ConsultationPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_consultation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("咨询用户")
    private UserPO user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @Comment("关联宠物")
    private PetPO pet;

    @Column(nullable = false, length = 500)
    @Comment("咨询内容")
    private String content;

    @Column(length = 500)
    @Comment("回复内容")
    private String reply;

    @Column
    @Comment("回复时间")
    private LocalDateTime replyTime;

    @Column(length = 20)
    @Comment("咨询类型")
    @Enumerated(EnumType.STRING)
    private ConsultType type;

    @Column(length = 20)
    @Comment("咨询状态")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum ConsultType {
        HEALTH, BEHAVIOR, DIET
    }

    public enum Status {
        PENDING, ANSWERED, CLOSED
    }
} 