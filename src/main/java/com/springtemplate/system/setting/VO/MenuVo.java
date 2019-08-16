package com.springtemplate.system.setting.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Qiu Ping
 * @description MenuVO
 * @date 2019/8/16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVo implements Serializable {

	private String name;

	private String path;

	private String redirect;

	private String component;

	private Boolean alwaysShow;

	private MenuMetaVo meta;

	private List<MenuVo> children;
}