package com.xs.cms.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @ClassName: User 
 * @Description: 用户信息
 * @author: 你爹
 * @date: 2020年3月3日 下午6:45:54
 */
public class User implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
    private String 	username;
	private String password;
	private String repassword;//确认密码
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	private Integer role;
	
	
	private  String nickname;// 昵称
	private Date birthday;//
	private String gender;
	private String locked;//0: 正常，1：禁用
    private Date created;//注册时间
	private  Date updated;//修改时间
	
	
	
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public User() {}
	public User(Integer id,String username) {
     this.id=id;
     this.username=username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public User(Integer id, String username, String password, String repassword, Integer role, String nickname,
			Date birthday, String gender, String locked, Date created, Date updated) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.repassword = repassword;
		this.role = role;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.created = created;
		this.updated = updated;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", repassword=" + repassword
				+ ", role=" + role + ", nickname=" + nickname + ", birthday=" + birthday + ", gender=" + gender
				+ ", locked=" + locked + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
}
