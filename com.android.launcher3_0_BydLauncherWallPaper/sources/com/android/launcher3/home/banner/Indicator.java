package com.android.launcher3.home.banner;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes4.dex */
public interface Indicator extends ViewPager.OnPageChangeListener {
    RelativeLayout.LayoutParams getParams();

    View getView();

    void initIndicatorCount(int pagerCount);
}
