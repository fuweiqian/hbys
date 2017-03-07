package com.wxj.hbys.bean.Response;

import com.wxj.hbys.bean.ShopBannerBean;
import com.wxj.hbys.bean.ShopMallHotBean;
import com.wxj.hbys.bean.ShopMallStoreBean;

import java.util.List;

/**
 * Created by wuxiaojun on 2017/3/7.
 */

public class ShopMallMainResponse extends BaseResponse<ShopMallMainResponse.ShopMallMainBean> {

    public class ShopMallMainBean{

        public List<ShopMallStoreBean> rec_store_list; // 推荐店铺
        public List<ShopMallHotBean>  hot_goods_list; // 热门商品
        public List<ShopBannerBean> adv_list; // 中间的推荐商品
        public List<ShopBannerBean> up_banner; // 顶部banner
        public List<ShopBannerBean> mid_banner; // 中间banner

    }

}
