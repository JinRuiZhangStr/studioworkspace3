package com.example.smartrefreshlayout_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private SmartRefreshLayout refreshLayout;
    private List<String> strList;
    private ArrayAdapter adapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = findViewById(R.id.lv);
        refreshLayout = findViewById(R.id.refreshLayout);
        fab = findViewById(R.id.fab);

        //设置刷新样式
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置加载样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(this));
        initData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        strList = new ArrayList<>();
        for (int i =1;i<50;i++){
            strList.add("item_条目_"+i);
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, strList);
        lv.setAdapter(adapter);
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //模拟刷新成功加载数据
                refreshData();
                refreshlayout.finishRefresh(2000);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //模拟成功加载数据
                loadmoreData();
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    private void loadmoreData() {
        for (int i =0;i<3;i++){
            strList.add("loadmore_条目_"+i);
        }
        adapter.notifyDataSetChanged();
    }

    private void refreshData() {
        for (int i =0;i<3;i++){
            strList.add(0,"refresh_条目_"+i);
        }
        adapter.notifyDataSetChanged();
    }

}
