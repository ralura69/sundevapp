package jp.sundevapp.service;

import java.util.List;

import jp.sundevapp.dto.UserDto;

public interface UserService {

	public List<UserDto> search(UserDto dto);
	public UserDto findOne(String id);
	public UserDto register(UserDto dto);
	public void delete(String id);

}
