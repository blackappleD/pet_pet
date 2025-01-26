package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Entity(name = ReportPO.TABLE_NAME)
@Getter @Setter
@FieldNameConstants
public class ReportPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_report";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("生成用户")
    private UserPO user;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("关联宠物")
    private PetPO pet;

    @Column(nullable = false, length = 20)
    @Comment("报告类型")
    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Lob
    @Column(nullable = false)
    @Comment("报告内容（JSON格式）")
    private String content;

    @Column(length = 200)
    @Comment("报告文件URL")
    private String fileUrl;

    public enum ReportType {
        HEALTH_ANALYSIS, 
        FINANCIAL_SUMMARY, 
        SOCIAL_ACTIVITY
    }
} 