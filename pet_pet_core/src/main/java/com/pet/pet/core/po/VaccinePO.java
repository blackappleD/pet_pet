package com.pet.pet.core.po;

import com.pet.pet.core.po.base.BasePO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity(name = VaccinePO.TABLE_NAME)
@Getter
@Setter
@FieldNameConstants
public class VaccinePO extends BasePO.CommonPO<Long> {
	public static final String TABLE_NAME = "pp_vaccine";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@Comment("关联宠物")
	private PetPO pet;

	@Column(nullable = false, length = 50)
	@Comment("疫苗类型")
	private String vaccineType;

	@Column(nullable = false)
	@Comment("接种时间")
	private LocalDate inoculationDate;

	@Column
	@Comment("下次接种时间")
	private LocalDate nextInoculationDate;

	@Column(length = 200)
	@Comment("接种机构")
	private String clinic;
} 