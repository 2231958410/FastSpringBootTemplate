package com.springtemplate.system.setting.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Qiu Ping
 * @description MenuMetaVo
 * @date 2019/8/16
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {

	private String title;

	private String icon;


}
