package me.everything.android.ui.overscroll.adapters;

import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class ViewPagerOverScrollDecorAdapter implements ViewPager.OnPageChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f800b;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.f799a = i;
        this.f800b = f;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }
}
