package com.jingchen.pulltorefresh.pullableview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * 概述：
 * 模块：
 *
 * @author lanjl
 * 日期： 2023/4/6
 * 修改履历：
 * Copyright: 2022 Industrial Securities Co., Ltd. All rights reserved.
 */
public class PullableRecyclerView2 extends RecyclerView implements Pullable{

    private boolean canPullUp = true; // 默认可以上拉加载更多
    private boolean canPullDown = true; // 默认可以下拉刷新

    public PullableRecyclerView2(Context context) {
        super(context);
    }

    public PullableRecyclerView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecyclerView2(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean canPullDown() {
        if(!canPullDown) {
            return false;
        }
        if (getChildCount() == 0) {
            return true;
        }else if(!canScrollVertically(-1)){
            return true;
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean canPullUp() {
        if (canPullUp){
            if (getChildCount() == 0)
                return true;
            else  if(!canScrollVertically(1)){
                System.out.println("cccccccccccccccccc");
                return true;
            }
        }
        return false;
    }

    public void setCanPullDown(boolean canPullDown){
        this.canPullDown = canPullDown;
    }

    public void setCanPullUp(boolean canPullUp){
        this.canPullUp = canPullUp;
    }
}
