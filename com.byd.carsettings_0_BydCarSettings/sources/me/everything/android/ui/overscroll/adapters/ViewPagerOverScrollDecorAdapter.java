package me.everything.android.ui.overscroll.adapters;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import d.a.a.a.a.j.a;

/* JADX INFO: loaded from: classes3.dex */
public class ViewPagerOverScrollDecorAdapter implements a, ViewPager.OnPageChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewPager f3956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3958c;

    @Override // d.a.a.a.a.j.a
    public boolean a() {
        return this.f3957b == this.f3956a.getAdapter().getCount() - 1 && this.f3958c == 0.0f;
    }

    @Override // d.a.a.a.a.j.a
    public boolean b() {
        return this.f3957b == 0 && this.f3958c == 0.0f;
    }

    @Override // d.a.a.a.a.j.a
    public View c() {
        return this.f3956a;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f2, int i2) {
        this.f3957b = i;
        this.f3958c = f2;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }
}
