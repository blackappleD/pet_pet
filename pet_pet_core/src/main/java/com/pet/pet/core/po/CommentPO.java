package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Entity(name = CommentPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class CommentPO extends BasePO.CommonPO<Long> {
    public static final String TABLE_NAME = "pp_comment";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @Comment("关联动态")
    private PostPO post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("评论用户")
    private UserPO author;

    @Column(nullable = false, length = 300)
    @Comment("评论内容")
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @Comment("父级评论")
    private CommentPO parentComment;
} 