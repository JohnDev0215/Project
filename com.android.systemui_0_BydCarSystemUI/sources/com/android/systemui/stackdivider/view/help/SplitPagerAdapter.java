package com.android.systemui.stackdivider.view.help;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SplitPagerAdapter extends PagerAdapter {
    private final List<SplitNavPageView> mViewList;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SplitPagerAdapter(List<SplitNavPageView> list) {
        this.mViewList = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<SplitNavPageView> list = this.mViewList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int position, Object obj) {
        Log.d("SplitPagerAdapter", "destroyItem position : " + position + " , mViewList : " + this.mViewList.size());
        viewGroup.removeView(this.mViewList.get(position));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        viewGroup.addView(this.mViewList.get(position));
        return this.mViewList.get(position);
    }
}
