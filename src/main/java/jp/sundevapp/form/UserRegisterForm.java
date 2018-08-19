package jp.sundevapp.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import jp.sundevapp.entity.PrefEntity;

public class UserRegisterForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String userId;
	@NotEmpty
	@Size(min = 1, max = 20)
	private String name;
	private String birth;
	private String age;
	private String sex = "0";
	private String nativePref;
	private String createDate;
	private String updateDate;

	private boolean passwordFlg = false;
	private String newPassword;
	private String newPasswordRe;
	private final Map<String, String> sexRadio = new LinkedHashMap<String, String>() {
		{
			put("0", "男");
			put("1", "女");
			put("2", "どちらでもない");
		}
	};
	private List<PrefEntity> prefList;
	private String insFlg = "0";

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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public boolean isPasswordFlg() {
		return passwordFlg;
	}
	public void setPasswordFlg(boolean passwordFlg) {
		this.passwordFlg = passwordFlg;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordRe() {
		return newPasswordRe;
	}
	public void setNewPasswordRe(String newPasswordRe) {
		this.newPasswordRe = newPasswordRe;
	}
	public List<PrefEntity> getPrefList() {
		return prefList;
	}
	public void setPrefList(List<PrefEntity> prefList) {
		this.prefList = prefList;
	}
	public Map<String, String> getSexRadio() {
		return sexRadio;
	}
	// true:更新 false:登録
	public Boolean isEdit() {
		return !StringUtils.isEmpty(userId);
	}
	public String getInsFlg() {
		return insFlg;
	}
	public void setInsFlg(String insFlg) {
		this.insFlg = insFlg;
	}
	public String getCompleteMessage() {
		String msg = "1".equals(this.insFlg) ? "登録" : "更新";
		return msg + "しました。　[ユーザーID : " + this.userId + "]";

	}
}
