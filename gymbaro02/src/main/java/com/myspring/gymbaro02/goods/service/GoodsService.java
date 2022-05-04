package com.myspring.gymbaro02.goods.service;

import java.util.List;
import java.util.Map;

import com.myspring.gymbaro02.goods.vo.GoodsVO;

public interface GoodsService {

	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	public Map goodsDetail(String _goods_id) throws Exception;
	
}
