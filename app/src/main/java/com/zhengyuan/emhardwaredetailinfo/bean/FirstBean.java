package com.zhengyuan.emhardwaredetailinfo.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class FirstBean {

    /**
     * 第一级Item显示的文字
     */
    private String title;

    /**
     * 第一级标题对应的第二级内容
     */
    private List<SecondBean> operation;

    public FirstBean() {
    }

    public FirstBean(List<SecondBean> operation, String title) {
        this.operation = operation;
        this.title = title;
    }

    public List<SecondBean> getOperation() {
        return operation;
    }

    public void setOperation(List<SecondBean> operation) {
        this.operation = operation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
