package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 11:23
 * @description 标题
 * @package com.changgou.goods.controller
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询所有的品牌列表
     *
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> all = brandService.findAll();
        return new Result<>(true,StatusCode.OK,"查询所有品牌成功",all);
    }


    /**
     * //添加品牌
     *
     * @param brand 请求体对象 用来接收页面传递过来的品牌的数据JSON
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功了");
    }

    /**
     * 根据ID 获取品牌的数据
     *
     * @param id
     * @return 品牌的数据的封装的对象
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable(name = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * @param id    要更新的品牌的ID 页面传递过来
     * @param brand 要更新后的数据的请求体对象  brand来接收
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable(name = "id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);//将ID 设置到Brand中
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 根据ID 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(name = "id") Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 条件搜索
     *
     * @param brand 用来接收搜索的条件
     * @return
     */
    @PostMapping("/search")
    public Result<List<Brand>> search(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.search(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件搜索查询成功", brandList);
    }

    /**
     * 无条件的 分页查询
     *
     * @param page 当前的页码
     * @param size 每页显示的行
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable(name = "page") Integer page,
                                              @PathVariable(name = "size") Integer size) {
        PageInfo<Brand> pageinfo = brandService.findByPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "条件搜索查询成功", pageinfo);

    }

    /**
     * 有条件的分页查询
     * @param page 当前的页码
     * @param size 每页显示的行
     * @param brand 页面传递过来的搜索的条件的封装的对象 JSON
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findByPage(@PathVariable(name = "page") Integer page,
                                              @PathVariable(name = "size") Integer size,
                                              @RequestBody Brand brand) {
        PageInfo<Brand> pageinfo = brandService.findByPage(page, size,brand);
        int i=1/0;

        /*if(true){
            throw new ItemNotFOUNDException("商品找不到");
        }*/
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "有条件分页查询成功", pageinfo);

    }


}
