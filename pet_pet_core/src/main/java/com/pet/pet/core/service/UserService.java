package com.pet.pet.core.service;

import cn.hutool.crypto.digest.BCrypt;
import com.pet.pet.core.auth.TokenInfo;
import com.pet.pet.core.dto.LoginReqDTO;
import com.pet.pet.core.dto.UserResDTO;
import com.pet.pet.core.exception.ApiMarketException;
import com.pet.pet.core.mapper.UserMapper;
import com.pet.pet.core.po.UserPO;
import com.pet.pet.core.repo.UserRepo;
import com.pet.pet.core.util.AuthUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chentong
 * @version 1.0
 * @description: description
 * @date 2024/12/20 9:27
 */
@Service
public class UserService {

	@Resource
	private UserRepo userRepo;

	@Resource
	private UserMapper userMapper;

	public UserResDTO get(String id) {
		return userMapper.po2dto(findById(id));
	}

	public UserPO findById(String id) {
		Optional<UserPO> byId = userRepo.findById(id);
		if (byId.isEmpty()) {
			throw ApiMarketException.UserException.notFound();
		}

		return byId.get();
	}

	public TokenInfo login(LoginReqDTO dto) {
		Optional<UserPO> userOpt = userRepo.findByUserAccount(dto.getUserAccount());
		if (userOpt.isEmpty()) {
			userOpt = userRepo.findByEmail(dto.getUserAccount());
		}

		if (userOpt.isEmpty()) {
			throw ApiMarketException.UserException.notFound(dto.getUserAccount());
		}

		UserPO user = userOpt.get();

		if (!BCrypt.checkpw(dto.getPassword(), user.getPassword())) {
			throw ApiMarketException.UserException.passwordError();
		}
		return AuthUtil.login(user.getId());
	}

	public void logout() {
		AuthUtil.logout();
	}

	public List<UserPO> findAllUser() {
		return userRepo.findAll();
	}

	public static void main(String[] args) {
		System.out.println(BCrypt.hashpw("aChen1874"));
	}
}
