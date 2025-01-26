package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Entity(name = PostPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class PostPO extends BasePO.CommonPO<Long> {
	public static final String TABLE_NAME = "pp_post";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@Comment("发布用户")
	private UserPO author;

	@ManyToOne
	@Comment("关联宠物")
	private PetPO pet;

	@Column(nullable = false, length = 500)
	@Comment("动态内容")
	private String content;

	@Column
	@Comment("经度")
	private Double longitude;

	@Column
	@Comment("纬度")
	private Double latitude;

	@ElementCollection
	@CollectionTable(name = "pp_post_media", joinColumns = @JoinColumn(name = "post_id"))
	@Comment("媒体文件URL列表")
	private List<String> mediaUrls = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@Comment("评论列表")
	private List<CommentPO> comments = new ArrayList<>();
} 