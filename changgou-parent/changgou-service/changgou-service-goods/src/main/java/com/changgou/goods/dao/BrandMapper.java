package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * extends Mapper<Brand>        mapper 使用通用的mapper的接口  泛型指定要操作的POJO对象
 *
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 11:24
 * @description 标题
 * @package com.changgou.goods.dao
 */



public interface BrandMapper extends Mapper<Brand> {


}
