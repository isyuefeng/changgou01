package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController extends AbstractCoreController<Para> {

    private ParaService paraService;

    @Autowired
    public ParaController(ParaService paraService) {
        super(paraService, Para.class);
        this.paraService = paraService;
    }
    /**
     * 根据分类ID查询参数列表
     * @param id
     * @return
     */
    @GetMapping("/categroy/{id}")
    public Result<List<Para>> getByCategoryId(@PathVariable Integer id) {
        List<Para> paraList = paraService.getByCategoryId(id);
        return new Result<List<Para>>(true, StatusCode.OK, "成功！");
    }
}
