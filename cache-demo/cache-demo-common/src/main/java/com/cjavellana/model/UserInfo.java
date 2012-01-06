package com.cjavellana.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.cjavellana.cache.Cacheable;

/**
 * <p>
 * The {@link UserInfo} class represents a user in the system
 * </p>
 * 
 * @author Christian
 * @since 1.0
 */
@Entity
@Table(name = "USERINFO")
public class UserInfo implements Serializable, Cacheable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 752942163772871701L;

	/**
	 * The USERINFO table's primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userinfoid", nullable = false)
	private Integer userInfoId;

	/**
	 * The user's login id
	 */
	@Column(name = "loginid", length = 24, nullable = false)
	private String loginId;

	/**
	 * The user's full name
	 */
	@Column(name = "name", length = 128, nullable = false)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthdate", nullable = false)
	private Date birthdate;

	@Column(name = "email", length = 128, nullable = true)
	private String email;

	/**
	 * This field shall not be persisted in the database
	 */
	@Transient
	private long timeToLive;

	/**
	 * @return the userInfoId
	 */
	public Integer getUserInfoId() {
		return userInfoId;
	}

	/**
	 * @param userInfoId
	 *            the userInfoId to set
	 */
	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTimeToLive(long seconds) {
		timeToLive = seconds;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getTimeToLive() {
		return timeToLive;
	}

}
