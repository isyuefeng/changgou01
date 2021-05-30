package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import entity.Result;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuEsMapper skuEsMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void importSku() {
        //1.先根据商品微服务feign 查询符合条件的sku的列表数据
        //1.1 service-goods-api创建一个feign接口
        //1.2 编写一个类 加入一个注解@feignclent(name="goods")
        //1.3 编写一个方法：查询符合条件的sku的列表数据
        //1.4 在service-goods微服务中实现controller-service-dao的调用。相当于实现feign接口

        //1.5 添加依赖 ，开启feignclients
        //1.6 注入feign 调用feign
        Result<List<Sku>> result = skuFeign.findByStatus("1");
        List<Sku> skuList = result.getData();
        if (skuList != null && skuList.size() > 0) {
            //转换将List<Sku> 转换成List<SkuInfo>
            List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuList), SkuInfo.class);
            //设置规格的数据到specMap属性中
            for (SkuInfo skuInfo : skuInfoList) {
                String spec = skuInfo.getSpec();//{"电视音响效果":"环绕","电视屏幕尺寸":"20英寸","尺码":"165"}
                skuInfo.setSpecMap(JSON.parseObject(spec, Map.class));
            }
            //2.把数据添加到ES中
            skuEsMapper.saveAll(skuInfoList);
        }

    }

    /**
     * 搜索
     *
     * @param searchMap 搜索的条件 {“keywords”:"手机"}
     * @return
     */
    @Override
    public Map search(Map<String, String> searchMap) {
        //1.接收页面传递过来的关键字
        String keywords = searchMap.get("keywords");
        if (StringUtils.isEmpty(keywords)) {
            keywords = "华为";
        }
        //2.创建查询对象的 构建对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //3.设置查询条件 match 先分词 再进行查询 指定查询的字段和要查询的值

        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));

        //4.构建查询对象
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        //5.执行分页查询
        //参数1 指定要查询的对象
        //参数2 指定要查询到的结果的映射的字节码对象 就是skuinfo
        AggregatedPage<SkuInfo> skuInfos = elasticsearchTemplate.queryForPage(query, SkuInfo.class);
        //6.获取结果 总页数 总记录数 当前页记录数
        //当前的页的记录
        List<SkuInfo> content = skuInfos.getContent();
        //总页数
        int totalPages = skuInfos.getTotalPages();
        //总记录数
        long totalElements = skuInfos.getTotalElements();

        //7.封装返回
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("rows", content);
        resultMap.put("totalPages", totalPages);
        resultMap.put("total", totalElements);
        return resultMap;
    }
}
