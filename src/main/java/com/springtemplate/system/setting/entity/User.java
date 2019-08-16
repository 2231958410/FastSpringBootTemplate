package com.springtemplate.system.setting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "头像地址")
	private String avatar;

	@ApiModelProperty(value = "创建日期")
	private Date createTime;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "状态：1启用、0禁用")
	private Long enabled;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "最后修改密码的日期")
	private Date lastPasswordResetTime;

	private Long deptId;

	private String phone;

	private Long jobId;


}
