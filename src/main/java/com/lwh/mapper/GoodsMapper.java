package com.lwh.mapper;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Goods goods);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBS(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsBo> selectAllGoods();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);

    int updateStock(long goodsId);

    List<Goods> findAll();

    int updateAddStatus(long id);

    int batchDelete(String[] ids);
    
    List<Goods> selectByIds(String[] ids);
}
