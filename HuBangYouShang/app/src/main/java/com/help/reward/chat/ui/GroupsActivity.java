/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.help.reward.chat.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.recyclerview.LRecyclerView;
import com.base.recyclerview.LRecyclerViewAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.help.reward.R;
import com.help.reward.chat.Constant;
import com.help.reward.chat.adapter.GroupAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupsActivity extends EaseBaseActivity {

	public static final String TAG = "GroupsActivity";
	protected List<EMGroup> grouplist;
	private GroupAdapter groupAdapter;
	private InputMethodManager inputMethodManager;
	public static GroupsActivity instance;
	private View progressBar;
	private SwipeRefreshLayout swipeRefreshLayout;

	@BindView(R.id.list)
	LRecyclerView groupListView;

	@BindView(R.id.iv_benefit_back)
	ImageView mIvBack;

	@BindView(R.id.et_benefit_search)
	EditText mEtSearch;
	
	Handler handler = new Handler(){
	    public void handleMessage(android.os.Message msg) {
	        swipeRefreshLayout.setRefreshing(false);
	        switch (msg.what) {
            case 0:
                refresh();
                break;
            case 1:
                Toast.makeText(GroupsActivity.this, R.string.Failed_to_get_group_chat_information, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
            }
	    }
	};

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_benefit);
		ButterKnife.bind(this);
		instance = this;
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		grouplist = EMClient.getInstance().groupManager().getAllGroups();
		groupListView.setLayoutManager(new LinearLayoutManager(this));
		groupAdapter = new GroupAdapter(this);
		LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(groupAdapter);
		groupListView.setAdapter(mLRecyclerViewAdapter);

		groupListView.setPullRefreshEnabled(false);
		groupListView.setLoadMoreEnabled(false);

		groupAdapter.setDataList(grouplist);


		groupListView.setOnRefreshListener(new com.base.recyclerview.OnRefreshListener() {
			@Override
			public void onRefresh() {
				groupListView.refreshComplete(Integer.MAX_VALUE);
				grouplist = EMClient.getInstance().groupManager().getAllGroups();
				groupAdapter.setDataList(grouplist);
			}
		});

		mIvBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				GroupsActivity.this.finish();
			}
		});

//		groupListView = (ListView) findViewById(R.id.list);
//		//show group list
//        groupAdapter = new GroupAdapter(this, 1, grouplist);
//        groupListView.setAdapter(groupAdapter);

//		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
//		swipeRefreshLayout.setColorSchemeResources(R.color.holo_blue_bright, R.color.holo_green_light,
//		                R.color.holo_orange_light, R.color.holo_red_light);
        //pull down to refresh
//		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//
//			@Override
//			public void onRefresh() {
//				new Thread(){
//					@Override
//					public void run(){
//						try {
//							EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
//							handler.sendEmptyMessage(0);
//						} catch (HyphenateException e) {
//							e.printStackTrace();
//							handler.sendEmptyMessage(1);
//						}
//					}
//				}.start();
//			}
//		});

//		groupListView.setOnItemClickListener(new OnItemClickListener() {

//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				if (position == 1) {
//					// create a new group
//					startActivityForResult(new Intent(GroupsActivity.this, NewGroupActivity.class), 0);
//				} else if (position == 2) {
//					// join a public group
//					startActivityForResult(new Intent(GroupsActivity.this, PublicGroupsActivity.class), 0);
//				} else {
//					// enter group chat
//					Intent intent = new Intent(GroupsActivity.this, ChatActivity.class);
//					// it is group chat
//					intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
//					intent.putExtra("userId", groupAdapter.getItem(position - 3).getGroupId());
//					startActivityForResult(intent, 0);
//				}
//			}

//		});

		groupListView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});

		groupAdapter.setListener(new com.base.recyclerview.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(GroupsActivity.this, ChatActivity.class);
//					// it is group chat
					intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
					intent.putExtra("userId", groupAdapter.getDataList().get(position).getGroupId());
					startActivityForResult(intent, 0);
			}
		});

	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        refresh();
        super.onResume();
    }

    private void refresh() {
        //grouplist = EMClient.getInstance().groupManager().getAllGroups();
        //groupAdapter = new GroupAdapter(this, 1, grouplist);
        //groupListView.setAdapter(groupAdapter);
        //groupAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    public void back(View view) {
        finish();
    }
}
