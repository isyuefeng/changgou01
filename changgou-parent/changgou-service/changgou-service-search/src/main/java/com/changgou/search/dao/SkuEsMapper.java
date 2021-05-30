package com.changgou.search.dao;

import com.changgou.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
    @Query(value="{\n" +
            "  \"query\": {\n" +
            "    \"match\": {\n" +
            "      \"name\": \"?1\"\n" +
            "    }\n" +
            "  }\n" +
            "}")
    List<SkuInfo> findALLL(String keywords);
}
