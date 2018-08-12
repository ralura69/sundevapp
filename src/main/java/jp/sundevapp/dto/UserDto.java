package jp.sundevapp.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto implements Serializable{

	private String userId;
	private String name;
	private LocalDate birth;
	private Integer age;
	private String sex;
	private String nativePref;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;

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
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNativePref() {
		return nativePref;
	}
	public void setNativePref(String nativePref) {
		this.nativePref = nativePref;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

}
