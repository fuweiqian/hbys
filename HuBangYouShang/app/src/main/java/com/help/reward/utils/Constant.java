package com.help.reward.utils;

/**
 * Created by wuxiaojun on 2017/2/23.
 */

public class Constant {
    // 210.72.13.135
    public static final String BASE_URL = "http://210.72.13.135/";

    // 登录
    public static final String URL_LOGIN = "mobile/index.php?act=login";
    // 注册获取验证码
    public static final String URL_GET_CODE = "mobile/index.php?act=shop&op=index";
    // 注册
    public static final String URL_REGISTER = "mobile/index.php?act=connect&op=sms_register";

    // 商城首页
    public static final String URL_SHOP_MALL_MAIN = "mobile/index.php?act=shop&op=index";
    // 商品详情信息
    public static final String URL_SHOP_MALL_INFO = "mobile/index.php";

    public static final String PLATFORM_CLIENT = "android"; // 平台是安卓

    public static final String URL_MESSAGE = "mobile/index.php?act=member_message";

    public static final String URL_HELP = "mobile/index.php?act=index";

    public static final String URL_AREA = "mobile/index.php?act=area&op=post_area_list";
    public static final String URL_SUBREWARD = "mobile/index.php?act=get_reward&op=release";
    //发布求助帖
    public static final String URL_SUBSEEKHELP = "mobile/index.php?act=seek_help&op=release_post";
    public static final String URL_UPLOADIMAGE ="mobile/index.php?act=upload_file&op=upload_img";
}
