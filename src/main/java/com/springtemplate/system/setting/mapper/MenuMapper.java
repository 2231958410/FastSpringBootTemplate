package com.springtemplate.system.setting.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springtemplate.system.setting.DTO.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import com.springtemplate.system.setting.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-15
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	MenuDTO getMenuDTOById(String id);

	MenuDTO getMenuDTOByIdChi(String id);

}
