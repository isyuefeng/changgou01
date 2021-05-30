package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="goods",path = "/sku")
// path = "/sku" 和 @requestMaping("/sku")效果一样
public interface SkuFeign {
    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(name="status") String status);
}