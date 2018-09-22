/*
 * AUTHOR：Yolanda.
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © ZhiMore. All Rights Reserved.
 *
 */
package com.zhengyuan.emhardwaredetailinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhengyuan.emhardwaredetailinfo.R;
import com.zhengyuan.emhardwaredetailinfo.bean.ThirdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>三级目录的适配器。</p>
 * Created in 2016/3/22 18:06.
 *
 * @author Yolanda;
 */
public class ThirdAdapter extends BaseAdapter {

    private Context mContext;

    private List<ThirdBean> mThirdBeans = new ArrayList<>();
    /**
     * 一级id
     */
    private int groupId = -1;
    /**
     * 二级id
     */
    private int chilcId = -1;

    public ThirdAdapter(Context context) {
        this.mContext = context;
    }

    public void notifyDataSetChanged(List<ThirdBean> ThirdBeans, int groupId, int chilcId) {
        this.groupId = groupId;
        this.chilcId = chilcId;
        mThirdBeans.clear();
        if (ThirdBeans != null) {
            mThirdBeans.addAll(ThirdBeans);
        }
        super.notifyDataSetChanged();
    }

    /**
     * 返回第一级选中的Item的Position，当没有选中时返回-1。
     *
     * @return Position。
     */
    public int getOneItemSelect() {
        return groupId;
    }

    /**
     * 返回第二级选中的Item的Position，当没有选中时返回-1。
     *
     * @return Position。
     */
    public int getTwoItemSelect() {
        return chilcId;
    }

    /**
     * 返回第三级选中的Item集合。
     *
     * @return {@code List<Three>}。
     */
    public List<ThirdBean> getThreeSelect() {
        List<ThirdBean> ThirdBeans = new ArrayList<>();
        for (ThirdBean ThirdBean : mThirdBeans) {
            if (ThirdBean.isChecked())
                ThirdBeans.add(ThirdBean);
        }
        return ThirdBeans;
    }

    @Override
    public int getCount() {
        return mThirdBeans.size();
    }

    @Override
    public ThirdBean getItem(int position) {
        return mThirdBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThreeViewHolder threeViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_three_title, parent, false);
            threeViewHolder = new ThreeViewHolder(convertView);
            convertView.setTag(threeViewHolder);
        } else
            threeViewHolder = (ThreeViewHolder) convertView.getTag();
        threeViewHolder.setPosition(position);
        return convertView;
    }

    /**
     * 一级的holder。
     */
    class ThreeViewHolder implements View.OnClickListener {
        private TextView mTvTitle;

        private int position;

        private ThreeViewHolder(View view) {
            view.setOnClickListener(this);
            mTvTitle = (TextView) view.findViewById(R.id.tv_title_three);
        }

        /**
         * 设置Item的数据。
         *
         * @param position 第几个Item。
         */
        public void setPosition(int position) {
            this.position = position;
            ThirdBean ThirdBean = getItem(position);
            mTvTitle.setText(ThirdBean.getTitle());
            mTvTitle.setSelected(ThirdBean.isChecked());
        }

        @Override
        public void onClick(View v) {
            {//反选其他控件
                for(ThirdBean ThirdBean:mThirdBeans){
                    ThirdBean.setChecked(false);
                }
            }
            // 点击Item的时候选中或者反选当前Item，这里没有让其它item反选，说明就是多选
            getItem(position).toggle();
            notifyDataSetChanged();
        }
    }
}
