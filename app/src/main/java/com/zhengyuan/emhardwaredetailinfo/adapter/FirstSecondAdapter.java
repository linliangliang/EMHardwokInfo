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
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zhengyuan.emhardwaredetailinfo.R;
import com.zhengyuan.emhardwaredetailinfo.bean.FirstBean;
import com.zhengyuan.emhardwaredetailinfo.bean.SecondBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>一二级目录的适配器。</p>
 * Created in 2016/3/22 17:34.
 *
 * @author Yolanda;
 */
public class FirstSecondAdapter extends BaseExpandableListAdapter {

    private Context mContext;

    private OnTwoItemClickListener itemClickListener;

    private List<FirstBean> FirstBeans = new ArrayList<>();

    public FirstSecondAdapter(Context context, OnTwoItemClickListener itemClickListener) {
        this.mContext = context;
        this.itemClickListener = itemClickListener;
    }

    public void notifyDataSetChanged(List<FirstBean> FirstBeans) {
        this.FirstBeans.clear();
        if (FirstBeans != null)
            this.FirstBeans.addAll(FirstBeans);
        super.notifyDataSetChanged();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        OneViewHolder oneViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_one_title, parent, false);
            oneViewHolder = new OneViewHolder(convertView);
            convertView.setTag(oneViewHolder);
        } else
            oneViewHolder = (OneViewHolder) convertView.getTag();
        oneViewHolder.setPosition(groupPosition);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TwoViewHolder twoViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_two_title, parent, false);
            twoViewHolder = new TwoViewHolder(convertView);
            convertView.setTag(twoViewHolder);
        } else
            twoViewHolder = (TwoViewHolder) convertView.getTag();
        twoViewHolder.setPosition(groupPosition, childPosition);
        return convertView;
    }

    /**
     * 一级的holder。
     */
    class OneViewHolder {
        private TextView mTvTitle;

        private OneViewHolder(View view) {
            mTvTitle = (TextView) view.findViewById(R.id.tv_title_one);
        }

        public void setPosition(int position) {
            FirstBean FirstBean = getGroup(position);
            mTvTitle.setText(FirstBean.getTitle());
        }
    }

    /**
     * 二级的holder。
     */
    class TwoViewHolder implements View.OnClickListener {
        private TextView mTvTitle;

        private int position;

        private int childPosition;

        private TwoViewHolder(View view) {
            view.setOnClickListener(this);
            mTvTitle = (TextView) view.findViewById(R.id.tv_title_two);
        }

        public void setPosition(int position, int childPosition) {
            this.position = position;
            this.childPosition = childPosition;
            SecondBean SecondBean = getChild(position, childPosition);
            mTvTitle.setText(SecondBean.getTitle());
            mTvTitle.setSelected(SecondBean.isChecked());
        }

        @Override
        public void onClick(View v) {
            {
                // 这一段代码是选中当前点击的，反选其它选项，这就是单选
                for (FirstBean FirstBean : FirstBeans) {
                    List<SecondBean> SecondBeans = FirstBean.getOperation();
                    for (SecondBean SecondBean : SecondBeans)
                        SecondBean.setChecked(false);
                }
                getChild(position, childPosition).setChecked(true);
                notifyDataSetChanged();
            }
            // 通知外部刷新第三级
            if (itemClickListener != null)
                itemClickListener.onClick(position, childPosition);
        }
    }

    public interface OnTwoItemClickListener {
        void onClick(int groupId, int childId);
    }

    @Override
    public int getGroupCount() {
        return FirstBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return FirstBeans.get(groupPosition).getOperation().size();
    }

    @Override
    public FirstBean getGroup(int groupPosition) {
        return FirstBeans.get(groupPosition);
    }

    @Override
    public SecondBean getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).getOperation().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
