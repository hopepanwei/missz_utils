package top.missz.repository;

import org.apache.ibatis.annotations.Mapper;
import top.missz.entity.CmnShortUrl;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CmnShortUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CmnShortUrl record);

    CmnShortUrl selectByShorts(CmnShortUrl build);

    List<CmnShortUrl> findAll();
}