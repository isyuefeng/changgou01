package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 11:25
 * @description 标题
 * @package com.changgou.goods.service.impl
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public void add(Brand brand) {
        //添加数据到数据库中
        //brandMapper.insert(brand);
        brandMapper.insertSelective(brand);
    }

    @Override
    public Brand findById(Integer id) {
        //根据ID 获取品牌的数据  select * from tb_brand where id=?
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        //update tb_brand set name=? ,letter=?,image=? where id=?
        brandMapper.updateByPrimaryKeySelective(brand);
        // brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Integer id) {
        //delete from tb_brand where id=?
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Brand> search(Brand brand) {
        /**
         * select * from tb_brand where name like ? and letter = ?
         * select * from tb_brand where name like ?
         * select * from tb_brand where letter = ?
         * select * from tb_brand
         */
        //1.创建条件对象 指定品牌的字节码对象 标识查询的表
        Example example = createExample(brand);


        //3.执行条件查询

        List<Brand> brandList = brandMapper.selectByExample(example);
        //4.返回结果

        return brandList;
    }

    private Example createExample(Brand brand) {
        Example example = new Example(Brand.class);

        if (brand != null) {
            //2.组装条件
            Example.Criteria criteria = example.createCriteria();
            //=条件
            //参数1 指定要比较的POJO的属性名（不是列名）
            //参数2 指定要比较的值
            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());// WHERE letter='H'
            }

            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%");// WHERE letter='H' and  name like '%zhangsan%'
            }

        }
        return example;
    }

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer size) {
        //1.加入起步依赖 自动配置好了
        //2.开始分页
        PageHelper.startPage(page, size);
        //3.查询
        List<Brand> brandList = brandMapper.selectAll();
        //4.返回的结果进行封装 返回

        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);

        return pageInfo;
    }

    //有条件的分页查询
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer size, Brand brand) {

        /**
         * select * from tb_brand where name like ? and letter = ? limit 0,10
         * select * from tb_brand where name like ? limit 0,10
         * select * from tb_brand where letter = ? limit 0,10
         * select * from tb_brand limit 0,10
         */

        //1.创建条件对象
        //2.判断并组装条件
        Example example = createExample(brand);//alt + ctr +m
        //3.开始分页
        PageHelper.startPage(page, size);
        //4 执行查询
        List<Brand> brandList = brandMapper.selectByExample(example);
        //5.封装返回的分页的对象
        PageInfo<Brand> pageInfo = new PageInfo<Brand>(brandList);
        //6.返回
        return pageInfo;
    }
}
