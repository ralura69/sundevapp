package jp.sundevapp.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jp.sundevapp.dto.UserDto;

public class UserSearchForm implements Serializable{

	private String userId;
	private String name;
	private List<UserDto> searchList = new ArrayList<>();

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UserDto> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<UserDto> searchList) {
		this.searchList = searchList;
	}

}
