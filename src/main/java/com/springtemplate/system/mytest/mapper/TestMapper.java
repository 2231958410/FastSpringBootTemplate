package com.springtemplate.system.mytest.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.springtemplate.system.mytest.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * test类 Mapper 接口
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-12
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
