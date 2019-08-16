package com.springtemplate.security.VO;

import com.springtemplate.system.setting.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Qiu Ping
 * @description UserVO
 * @date 2019/8/15
 */

@Data
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String avatar;

	private LocalDateTime createTime;

	private String email;

	private Long enabled;

	private String password;

	private String username;

	private LocalDateTime lastPasswordResetTime;

	private Long deptId;

	private String phone;

	private Long jobId;

	private List<Role> roles;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserVO role = (UserVO) o;
		return Objects.equals(id, role.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
