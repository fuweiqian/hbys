package com.help.reward.bean.Response;

import java.util.List;

/**
 * "code": 200,
 * "msg": "操作成功",
 * "data": [
 * {
 * "id": "1",
 * "u_id": "1",
 * "u_name": "获赏者No.1",
 * "board_id": "1",
 * "board_name": "创新创业",
 * "title": "不要这么容易就想放弃",
 * "create_time": "1479804948",
 * "comment": "0",
 * "admiration": "2",
 * "status": "正常"
 * }
 * <p>
 * Created by wuxiaojun on 2017/3/2.
 */

public class MyRewardCommentResponse extends BaseResponse<List<MyRewardCommentResponse>> {

    public String id;
    public String u_id;
    public String u_name;
    public String board_id;
    public String board_name;
    public String title;
    public String create_time;
    public String comment;
    public String admiration;
    public String status;

}
