package com.springtemplate.system.setting.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Qiu Ping
 * @description 菜单DTO
 * @date 2019/8/16
 */
@Data
public class MenuDTO {

	private Long id;

	private String name;

	private Long sort;

	private String path;

	private String component;

	private Long pid;

	private Boolean iFrame;

	private String icon;

	private List<MenuDTO> children;

	private Timestamp createTime;
}