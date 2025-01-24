package com.pet.pet.core.repo;

import com.pet.pet.core.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2024/12/19 9:57
 */
public interface UserRepo extends JpaRepository<UserPO, String>, JpaSpecificationExecutor<UserPO> {

	Optional<UserPO> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<UserPO> findByUserAccount(String userAccount);

}
