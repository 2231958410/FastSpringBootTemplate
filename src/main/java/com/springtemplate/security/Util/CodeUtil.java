package com.springtemplate.security.Util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.springtemplate.common.VO.ImgResultVo;
import com.springtemplate.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Qiu Ping
 * @description 验证码工具
 * @date 2019/8/14
 */
@Component
public class CodeUtil {

	@Autowired
	private RedisService redisService;


	/**
	 * 获取验证码
	 */
	public ImgResultVo getCode() throws IOException {

		//生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		System.out.println(verifyCode);
		String uuid = IdUtil.simpleUUID();
		System.out.println(uuid);
		redisService.saveCode(uuid,verifyCode);
		// 生成图片
		int w = 111, h = 36;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
		try {
			return new ImgResultVo(Base64.encode(stream.toByteArray()),uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			stream.close();
		}
	}

}
