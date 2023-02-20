package top.missz.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.missz.entity.CmnMd5;

@Mapper
public interface CmnMd5Mapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CmnMd5 record);

    CmnMd5 selectByPrimaryKey(Long id);

    int updateBatch(List<CmnMd5> list);

    int batchInsert(@Param("list") List<CmnMd5> list);

    List<CmnMd5> selectNotGenerate(@Param("lastId") Long lastId, @Param("limit") int limit);

    List<CmnMd5> getListByLength(@Param("lastId") Long lastId,@Param("length") int length);
}