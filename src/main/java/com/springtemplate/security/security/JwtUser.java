package com.springtemplate.security.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springtemplate.system.setting.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Qiu Ping
 * @description Jwt安全实体
 * @date 2019/8/14
 */
@Data
public class JwtUser implements UserDetails {

	private User user;

	private final Collection<GrantedAuthority> authorities;

	public JwtUser(User _user,Collection<GrantedAuthority> _authorities){
		this.user = _user;
		this.authorities = _authorities;

	}


	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isEnabled() {
		//1可用 2不可用
		return this.user.getEnabled() == 1 ? true : false;
	}

	public Collection getRoles() {
		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
	}
}
