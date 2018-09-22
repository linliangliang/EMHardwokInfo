package com.zhengyuan.emhardwaredetailinfo.bean;

import android.widget.Checkable;

/**
 * Created by Administrator on 2018/9/18.
 */

public class ThirdBean implements Checkable {

        /**
         * 三级的Item的文字。
         */
        private String title;

        /**
         * 是否选中。
         */
        private boolean isChecked;
        /**
         * 在List中的位置
         */
        private int index;

        public ThirdBean() {
        }

        public ThirdBean(boolean checked, String title, int index) {
            isChecked = checked;
            this.title = title;
            this.index = index;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
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

        @Override
        public String toString() {
            return Integer.toString(index);
        }
    }
