package com.springtemplate.common.VO;

import lombok.Data;

/**
 * @author Qiu Ping
 * @description 验证码VO
 * @date 2019/8/14
 */
@Data
public class ImgResultVo {

	public ImgResultVo(String image, String uuid) {
		this.img = image;
		this.uuid = uuid;
	}

	private String img;

	private String uuid;

}
