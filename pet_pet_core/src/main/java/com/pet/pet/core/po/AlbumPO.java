package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import java.util.ArrayList;
import java.util.List;

@Entity(name = AlbumPO.TABLE_NAME)
@Getter @Setter
@FieldNameConstants
public class AlbumPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_album";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @Comment("关联宠物")
    private PetPO pet;

    @Column(nullable = false, length = 100)
    @Comment("相册名称")
    private String name;

    @ElementCollection
    @CollectionTable(name = "pp_album_photos", joinColumns = @JoinColumn(name = "album_id"))
    @Comment("照片URL列表")
    private List<String> photoUrls = new ArrayList<>();

    @Column(length = 500)
    @Comment("相册描述")
    private String description;

    @Column(nullable = false)
    @Comment("时间轴模式")
    private Boolean isTimelineMode = true;

    @Column(length = 50)
    @Comment("模板样式")
    private String template;
} 