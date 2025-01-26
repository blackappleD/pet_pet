package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Entity(name = VirtualItemPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class VirtualItemPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_virtual_item";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Comment("物品名称")
    private String name;

    @Column(nullable = false, length = 20)
    @Comment("物品类型")
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(nullable = false)
    @Comment("价格（积分）")
    private Integer price;

    @Column(length = 200)
    @Comment("预览图URL")
    private String previewUrl;

    @Column
    @Comment("有效天数")
    private Integer validityDays;

    public enum ItemType {
        AVATAR_FRAME, PET_DRESS, CHAT_BUBBLE
    }
} 