package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity(name = PetPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class PetPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_pet";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    @Comment("宠物名称")
    private String name;

    @Column(length = 20)
    @Comment("品种")
    private String breed;

    @Column(nullable = false)
    @Comment("性别")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Comment("出生日期")
    private LocalDate birthday;

    @Column(length = 200)
    @Comment("宠物照片URL")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("所属用户")
    private UserPO owner;

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }
} 