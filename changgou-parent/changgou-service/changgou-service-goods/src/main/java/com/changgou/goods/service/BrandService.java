package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 11:25
 * @description 标题
 * @package com.changgou.goods.service
 */
public interface BrandService {
    public List<Brand> findAll();

    void add(Brand brand);

    Brand findById(Integer id);


    void update(Brand brand);

    void delete(Integer id);

    List<Brand> search(Brand brand);

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findByPage(Integer page, Integer size);

    PageInfo<Brand> findByPage(Integer page, Integer size, Brand brand);

}
