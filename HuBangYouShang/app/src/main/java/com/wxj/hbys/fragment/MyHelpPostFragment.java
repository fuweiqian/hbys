package com.wxj.hbys.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.base.recyclerview.LRecyclerView;
import com.base.recyclerview.LRecyclerViewAdapter;
import com.base.recyclerview.OnLoadMoreListener;
import com.base.recyclerview.OnRefreshListener;
import com.idotools.utils.ToastUtils;
import com.wxj.hbys.App;
import com.wxj.hbys.R;
import com.wxj.hbys.adapter.MyHelpPostAdapter;
import com.wxj.hbys.bean.Response.MyHelpPostResponse;
import com.wxj.hbys.network.PersonalNetwork;
import com.wxj.hbys.network.base.BaseSubscriber;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuxiaojun on 2017/2/8.
 */

public class MyHelpPostFragment extends BaseFragment {

    private int numSize = 15;

    @BindView(R.id.id_recycler_view)
    LRecyclerView lRecyclerview;
    private MyHelpPostAdapter mHelpPostAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_help_post;
    }

    @Override
    protected void init() {
        initRecyclerView();
        initNetwork();
    }

    private void initNetwork() {
        PersonalNetwork
                .getMyHelpPostResponseApi()
                .getMyHelpPostResponse("post", App.APP_CLIENT_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MyHelpPostResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        lRecyclerview.refreshComplete(numSize);
                        ToastUtils.show(mContext, R.string.string_error);
                    }

                    @Override
                    public void onNext(MyHelpPostResponse response) {
                        lRecyclerview.refreshComplete(numSize);
                        if (response.code == 200) {
                            if (response.data != null) {
                                mHelpPostAdapter.addAll(response.data);
                            }
                            lRecyclerview.setPullRefreshEnabled(false);
                        } else {
                            ToastUtils.show(mContext, response.msg);
                        }
                    }
                });
    }

    private void initRecyclerView() {
        lRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mHelpPostAdapter = new MyHelpPostAdapter(mContext);
        LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(mHelpPostAdapter);
        lRecyclerview.setAdapter(mLRecyclerViewAdapter);
        initRefreshListener();
        initLoadMoreListener();
        lRecyclerview.refresh();
    }

    private void initLoadMoreListener() {
        lRecyclerview.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }

    private void initRefreshListener() {
        lRecyclerview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }


}
