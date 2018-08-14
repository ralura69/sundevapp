package jp.sundevapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jp.sundevapp.dto.UserDto;
import jp.sundevapp.entity.UserEntity;
import jp.sundevapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> search(UserDto dto) {

		if (!StringUtils.isEmpty(dto.getUserId())) {
			UserEntity oneEntity = userRepository.findByUserId(dto.getUserId());
			if (oneEntity != null) {
				UserDto oneDto = new UserDto();
				BeanUtils.copyProperties(oneEntity, oneDto);
				return Arrays.asList(oneDto);
			}
		}

		List<UserEntity> list = !StringUtils.isEmpty(dto.getName())
								? userRepository.findByNameLike("%" + dto.getName() + "%")
								: userRepository.findAll();

		return list.stream().map(rec -> {
								UserDto resDto = new UserDto();
								BeanUtils.copyProperties(rec, resDto);
								return resDto;
							}).collect(Collectors.toList());
	}

}
