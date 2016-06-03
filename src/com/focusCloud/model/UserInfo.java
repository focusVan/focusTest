package com.focusCloud.model;

public class UserInfo {
	private String userName;
	private String userId;
	private String roleName;
	private String password;
	private String newPassword;
	private String newPasswordAgain;
	private String phone;
	private String moblie;
	private String realName;
	private String address;
	private String email;
	private String openid;
	// 昵称
	private String nickName;
	private int wxType;//微信类型，1位店员，0为店长
	
	
	// 日期
	private String date;
	// 城市名称
	private String cityName;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	public String getMoblie() {
		return moblie;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealName() {
		return realName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setWxType(int wxType) {
		this.wxType = wxType;
	}
	public int getWxType() {
		return wxType;
	}
}
