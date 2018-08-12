package jp.sundevapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.sundevapp.dto.UserDto;
import jp.sundevapp.entity.UserEntity;
import jp.sundevapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository useRepository;

	@Override
	public List<UserDto> search(UserDto dto) {
		List<UserEntity> list = useRepository.findAll();
		return list.stream().map(one -> {
									UserDto target = new UserDto();
									BeanUtils.copyProperties(one, target);
									return target;
								}).collect(Collectors.toList());
	}

}
