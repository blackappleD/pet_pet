package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity(name = MembershipPO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class MembershipPO extends BasePO.CommonPO<Long> {
	public static final String TABLE_NAME = "pp_membership";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	@Comment("关联用户")
	private UserPO user;

	@Column(nullable = false)
	@Comment("会员类型")
	@Enumerated(EnumType.STRING)
	private MembershipType type;

	@Column(nullable = false)
	@Comment("生效时间")
	private LocalDateTime startTime;

	@Column(nullable = false)
	@Comment("到期时间")
	private LocalDateTime endTime;

	@Column(nullable = false)
	@Comment("会员状态")
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;

	@Column
	@Comment("剩余云存储空间（MB）")
	private Integer remainingStorage = 10240;

	public enum MembershipType {
		BASIC, PREMIUM, VIP
	}

	public enum Status {
		ACTIVE, EXPIRED, CANCELLED
	}
} 