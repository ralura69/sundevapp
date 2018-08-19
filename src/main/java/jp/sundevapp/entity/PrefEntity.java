package jp.sundevapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pref")
@Entity
public class PrefEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String prefId;
	private String name;

	public String getPrefId() {
		return prefId;
	}
	public void setPrefId(String prefId) {
		this.prefId = prefId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
