package com.springtemplate.system.mytest.service.impl;

import com.springtemplate.system.mytest.entity.Test;
import com.springtemplate.system.mytest.mapper.TestMapper;
import com.springtemplate.system.mytest.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * test类 服务实现类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-12
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
