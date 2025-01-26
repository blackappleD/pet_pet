package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Entity(name = PetMatchPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class PetMatchPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_pet_match";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_pet_id", nullable = false)
    @Comment("发起匹配的宠物")
    private PetPO sourcePet;

    @ManyToOne
    @JoinColumn(name = "target_pet_id", nullable = false)
    @Comment("被匹配的宠物")
    private PetPO targetPet;

    @Column(nullable = false)
    @Comment("匹配分数")
    private Double matchScore;

    @Column
    @Comment("匹配状态")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }
} 