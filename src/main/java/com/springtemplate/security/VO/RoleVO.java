package com.springtemplate.security.VO;

import com.springtemplate.system.setting.entity.Menu;
import com.springtemplate.system.setting.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * @author Qiu Ping
 * @description RoleVO
 * @date 2019/8/14
 */
@Data
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime createTime;

	private String name;

	private String remark;

	private String dataScope;

	private Integer level;

	private Set<Permission> permissions;

	private Set<Menu> menus;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RoleVO role = (RoleVO) o;
		return Objects.equals(id, role.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}
