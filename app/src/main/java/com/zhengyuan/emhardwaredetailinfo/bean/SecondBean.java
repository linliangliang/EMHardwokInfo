package com.zhengyuan.emhardwaredetailinfo.bean;

import android.widget.Checkable;

/**
 * Created by Administrator on 2018/9/18.
 */

public class SecondBean implements Checkable {

    /**
     * 第二级Item显示的文字。
     */
    private String title;

    /**
     * 第二级是否被选中。
     */
    private boolean isChecked;

    public SecondBean() {
    }

    public SecondBean(boolean checked, String title) {
        isChecked = checked;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        isChecked = !isChecked;
    }
}
