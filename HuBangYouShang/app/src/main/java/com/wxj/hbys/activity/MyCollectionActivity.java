package com.wxj.hbys.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.wxj.hbys.R;
import com.wxj.hbys.fragment.MyCollectionGoodsFragment;
import com.wxj.hbys.fragment.MyCollectionPostFragment;
import com.wxj.hbys.fragment.MyCollectionStoreFragment;
import com.wxj.hbys.fragment.MyHelpCommentFragment;
import com.wxj.hbys.fragment.MyHelpPostFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 我的收藏
 *
 * Created by wuxiaojun on 2017/2/8.
 */
public class MyCollectionActivity extends BaseActivity {

    @BindView(R.id.id_viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabStrip;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabStrip.setViewPager(viewPager);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter{

        private String[] TITLES = new String[3];

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            TITLES[0] = getResources().getString(R.string.string_post);
            TITLES[1] = getResources().getString(R.string.string_goods);
            TITLES[2] = getResources().getString(R.string.string_store);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new MyCollectionPostFragment();
            }else if(position == 1){
                return new MyCollectionGoodsFragment();
            }else{
                return new MyCollectionStoreFragment();
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }


}