package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import java.time.Year;

@Entity(name = HealthReportPO.TABLE_NAME)
@Getter @Setter
@FieldNameConstants
public class HealthReportPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_health_report";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("关联宠物")
    private PetPO pet;

    @Column(nullable = false)
    @Comment("报告年份")
    private Year reportYear;

    @Column(length = 20)
    @Comment("报告类型")
    @Enumerated(EnumType.STRING)
    private ReportType type;

    @Lob
    @Column(nullable = false)
    @Comment("分析内容（JSON格式）")
    private String analysisData;

    @Column(length = 500)
    @Comment("兽医建议")
    private String suggestions;

    public enum ReportType {
        VACCINATION_ANALYSIS, DIET_SUGGESTION, BEHAVIOR_ANALYSIS
    }
} 