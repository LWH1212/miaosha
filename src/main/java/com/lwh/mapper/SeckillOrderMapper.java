package com.lwh.mapper;

import com.lwh.pojo.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SeckillOrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    SeckillOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SeckillOrder record);

    SeckillOrder selectByUserIdAndGoodsId(@Param("userId") long userId,@Param("goodsId") long goodsId);

}
