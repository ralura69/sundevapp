package jp.sundevapp.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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

	@Override
	public UserDto findOne(String userId) {
		UserEntity entity = userRepository.findByUserId(userId);
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	@Transactional
	public UserDto register(UserDto dto) {

		LocalDateTime now = LocalDateTime.now();
		boolean insFlg = StringUtils.isEmpty(dto.getUserId());
		if (insFlg) {
			dto.setUserId(createNewUserId());
			dto.setCreateDate(now);
		} else {
			UserEntity bef = userRepository.findByUserId(dto.getUserId());
			if (StringUtils.isEmpty(dto.getPassword()))
				dto.setPassword(bef.getPassword());
		}

		dto.setUpdateDate(now);

		UserEntity reg = new UserEntity();
		BeanUtils.copyProperties(dto, reg);

		userRepository.save(reg);

		UserEntity resultEntity = userRepository.findByUserId(reg.getUserId());
		UserDto rtnDto = new UserDto();
		BeanUtils.copyProperties(resultEntity, rtnDto);
		rtnDto.setInsFlg(insFlg);
		return rtnDto;
	}

	private String createNewUserId() {
		String max = userRepository.findFirstByUserIdIsNotNullOrderByUserIdDesc().getUserId();
		Integer i = Integer.parseInt(max);
		return String.format("%04d", ++i);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		userRepository.deleteByUserId(userId);
	}
}
