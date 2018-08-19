package jp.sundevapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.sundevapp.dto.UserDto;
import jp.sundevapp.entity.UserEntity;
import jp.sundevapp.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto isAuth(String userId, String password) {
		UserEntity entity = userRepository.findByUserIdAndPassword(userId, password);
		if (entity == null)
			return null;
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}


}
