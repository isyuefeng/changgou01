package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrandServiceImpl extends CoreServiceImpl<Brand> implements BrandService {
    @Autowired
    BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper) {
        super(brandMapper, Brand.class);
    }
}
