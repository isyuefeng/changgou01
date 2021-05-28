package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.CategoryBrandMapper;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryBrandServiceImpl extends CoreServiceImpl<CategoryBrand> implements CategoryBrandService {

    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    public CategoryBrandServiceImpl(CategoryBrandMapper categoryBrandMapper) {
        super(categoryBrandMapper, CategoryBrand.class);
        this.categoryBrandMapper = categoryBrandMapper;
    }



}
