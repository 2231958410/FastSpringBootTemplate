package com.springtemplate.common.redis;

import com.springtemplate.common.VO.RedisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Qiu Ping
 * @description Redis操作实现类
 * @date 2019/8/14
 */
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	RedisTemplate redisTemplate;

	@Value("${loginCode.expiration}")
	private Long expiration;

	@Override
	public Page<RedisVO> findByKey(String key, Pageable pageable){
		return null;
	}


	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void flushdb() {
		redisTemplate.getConnectionFactory().getConnection().flushDb();
	}

	@Override
	public String getCodeVal(String key) {
		try {
			String value = redisTemplate.opsForValue().get(key).toString();
			return value;
		}catch (Exception e){
			return "";
		}
	}

	@Override
	public void saveCode(String key, Object val) {
		redisTemplate.opsForValue().set(key,val);
		//缓存失效时间
		redisTemplate.expire(key,expiration, TimeUnit.MINUTES);
	}
}
