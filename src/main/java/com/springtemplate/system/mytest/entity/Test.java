package com.springtemplate.system.mytest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * test类
 * </p>
 *
 * @author QiuPing
 * @since 2019-08-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Test对象", description="test类")
public class Test implements Serializable {

private static final long serialVersionUID=1L;

	@ApiModelProperty(value = "主键")
  	@TableId(value = "id", type = IdType.AUTO)
  private Integer id;

	@ApiModelProperty(value = "数据")
private String data;

	@ApiModelProperty(value = "状态")
private String state;


		}
