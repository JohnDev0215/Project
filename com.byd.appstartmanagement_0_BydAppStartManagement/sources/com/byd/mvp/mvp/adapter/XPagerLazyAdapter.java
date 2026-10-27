package com.byd.mvp.mvp.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import b.a.d.b.a;
import b.a.d.d.d;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XPagerLazyAdapter<T extends d> extends PagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f672a = "XPagerLazyAdapter";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f673b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile ConcurrentHashMap<Integer, T> f674c;

    public abstract T a(int i);

    public final synchronized T b(int i) {
        T t;
        t = this.f674c.get(Integer.valueOf(i));
        if (t == null) {
            t = (T) a(i);
        }
        if (t != null) {
            this.f674c.put(Integer.valueOf(i), t);
        }
        return t;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        T t;
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
        if (this.f674c == null || this.f674c.size() <= 0 || (t = this.f674c.get(Integer.valueOf(i))) == null) {
            return;
        }
        t.i();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f673b;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        T tB = this.f674c.get(Integer.valueOf(i));
        a.a(f672a, "position = " + i + ", p = " + tB);
        if (tB == null) {
            tB = b(i);
        }
        if (tB == null) {
            return null;
        }
        tB.h();
        View viewG = tB.g();
        ((b.a.d.c.a) viewG.getTag()).a(i);
        viewGroup.addView(viewG);
        return viewG;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
