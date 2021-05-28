package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl extends CoreServiceImpl<Category> implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        super(categoryMapper, Category.class);
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findByParentId(Integer pid) {
        //SELECT * FROM tb_category WHERE parent_id=?
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }
}
