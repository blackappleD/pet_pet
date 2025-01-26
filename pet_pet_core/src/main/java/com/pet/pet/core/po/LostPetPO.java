package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import java.time.LocalDateTime;

@Entity(name = LostPetPO.TABLE_NAME)
@Getter @Setter
@FieldNameConstants
public class LostPetPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_lost_pet";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("丢失宠物")
    private PetPO pet;

    @Column(nullable = false)
    @Comment("丢失时间")
    private LocalDateTime lostTime;

    @Column(nullable = false)
    @Comment("经度")
    private Double longitude;

    @Column(nullable = false)
    @Comment("纬度")
    private Double latitude;

    @Column(length = 500)
    @Comment("详细地址")
    private String address;

    @Column(length = 20)
    @Comment("悬赏金额")
    private String reward;

    @Column(length = 15)
    @Comment("联系人电话")
    private String contactPhone;

    @Column
    @Comment("找回时间")
    private LocalDateTime foundTime;
} 