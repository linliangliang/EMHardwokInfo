package com.zhengyuan.emhardwaredetailinfo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhengyuan.emhardwaredetailinfo.R;
import com.zhengyuan.emhardwaredetailinfo.adapter.FirstSecondAdapter;
import com.zhengyuan.emhardwaredetailinfo.adapter.ThirdAdapter;
import com.zhengyuan.emhardwaredetailinfo.bean.FirstBean;
import com.zhengyuan.emhardwaredetailinfo.bean.SecondBean;
import com.zhengyuan.emhardwaredetailinfo.bean.ThirdBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * 第三级适配器
     */
    private ThirdAdapter threeListAdapter;
    /**
     * 第三级数据
     */
    private List<ThirdBean> ThirdBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 第三级
        ListView listView = (ListView) findViewById(R.id.children_ListView);
        threeListAdapter = new ThirdAdapter(this);
        listView.setAdapter(threeListAdapter);


        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.parents_ExpListView);
        FirstSecondAdapter expandCheckAdapter = new FirstSecondAdapter(this, onTwoItemClickListener);
        expandableListView.setAdapter(expandCheckAdapter);
        // 第一二级
        List<FirstBean> FirstBeans = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            List<SecondBean> SecondBeans = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                SecondBeans.add(new SecondBean(false, "第" + i + "的第" + j + "个"));
            }
            FirstBeans.add(new FirstBean(SecondBeans, "第一级：" + i));
        }

        // 这里刷新就数据，假设是从服务器拿来的数据
        expandCheckAdapter.notifyDataSetChanged(FirstBeans);
    }

    private FirstSecondAdapter.OnTwoItemClickListener onTwoItemClickListener = new FirstSecondAdapter.OnTwoItemClickListener() {
        @Override
        public void onClick(int groupId, int childId) {
            if (ThirdBeans == null){
                ThirdBeans = new ArrayList<>();
            }
            ThirdBeans.clear();

            // 这里模拟请求第三级的数据
            for (int i = 0; i < 15; i++) {
                ThirdBeans.add(new ThirdBean(false, "第" + groupId + "个的第" + childId + "的数据" + i, i));
            }
            threeListAdapter.notifyDataSetChanged(ThirdBeans, groupId, childId);
        }
    };

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == R.id.menu_sure) {
            String message = "第一级选中的是第" + threeListAdapter.getOneItemSelect() + "，第二级选中的是第" + threeListAdapter.getTwoItemSelect();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            // 拿到第三级选中的列表，这里可以这样拿，也可以直接从我们数据源中拿
            List<ThirdBean> threeSelect = threeListAdapter.getThreeSelect();
            if (threeSelect.size() > 0) {
                String messageThree = "第三级选中了" + TextUtils.join(", ", threeSelect);
                Toast.makeText(this, messageThree, Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
