package me.everything.android.ui.overscroll.adapters;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {
    protected int mLastPagerPosition;
    protected float mLastPagerScrollOffset;
    protected final ViewPager mViewPager;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mLastPagerPosition = 0;
        this.mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        this.mLastPagerPosition = viewPager.getCurrentItem();
        this.mLastPagerScrollOffset = 0.0f;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public View getView() {
        return this.mViewPager;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteStart() {
        return this.mLastPagerPosition == 0 && this.mLastPagerScrollOffset == 0.0f;
    }

    @Override // me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean isInAbsoluteEnd() {
        return this.mLastPagerPosition == this.mViewPager.getAdapter().getCount() - 1 && this.mLastPagerScrollOffset == 0.0f;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.mLastPagerPosition = i;
        this.mLastPagerScrollOffset = f;
    }
}
