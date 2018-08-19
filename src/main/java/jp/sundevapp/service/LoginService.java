package jp.sundevapp.service;

import jp.sundevapp.dto.UserDto;

public interface LoginService {

	public UserDto isAuth(String userId, String password);

}
