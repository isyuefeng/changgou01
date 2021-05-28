package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlbumServiceImpl extends CoreServiceImpl<Album> implements AlbumService {

    private AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumMapper albumMapper) {
        super(albumMapper, Album.class);
        this.albumMapper = albumMapper;
    }
}
