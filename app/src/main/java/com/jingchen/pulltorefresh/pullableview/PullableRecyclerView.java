package com.jingchen.pulltorefresh.pullableview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


/**
 * 概述：
 * 模块：
 *
 * @author lanjl
 * 日期： 2023/4/6
 * 修改履历：
 * Copyright: 2022 Industrial Securities Co., Ltd. All rights reserved.
 */
public class PullableRecyclerView extends RecyclerView implements Pullable{

    private boolean canPullUp = true; // 默认可以上拉加载更多
    private boolean canPullDown = true; // 默认可以下拉刷新

    public PullableRecyclerView(Context context) {
        super(context);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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
        }else if(computeVerticalScrollOffset() ==0){

        }
        else if (getChildAt(0).getTop() >= 0){
            if (getLayoutManager() instanceof LinearLayoutManager) {
                int firstVisibleItem = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    return true;
                }
            } else if (getLayoutManager() instanceof GridLayoutManager) {
                int firstVisibleItem = ((GridLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItem == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canPullUp() {
        if (canPullUp){
            if (getChildCount() == 0)
                return true;
            else {
                if (getLayoutManager() instanceof  LinearLayoutManager){
                    int firstVisibleItem = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                    int lastVisibleItem = ((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
                    boolean isFinalItem = lastVisibleItem == getLayoutManager().getItemCount() - 1;
                    View lastView = getChildAt(lastVisibleItem - firstVisibleItem);
                    // 滑到底部了
                    if (isFinalItem && lastView != null && lastView.getBottom() <= getMeasuredHeight())
                        return true;
                }else {
                    if (getLayoutManager() instanceof GridLayoutManager){
                        int lastVisibleItem = ((GridLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
                        if (lastVisibleItem == getLayoutManager().getItemCount() - 1)
                            return true;
                    }
                }
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
