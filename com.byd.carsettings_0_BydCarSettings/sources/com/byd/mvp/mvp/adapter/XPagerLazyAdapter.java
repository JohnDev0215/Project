package com.byd.mvp.mvp.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import b.b.m.c.a;
import b.b.m.e.f;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XPagerLazyAdapter<T extends f> extends PagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3739a = "XPagerLazyAdapter";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile ConcurrentHashMap<Integer, T> f3741c;

    public abstract T a(int i);

    public final synchronized T b(int i) {
        T t;
        t = this.f3741c.get(Integer.valueOf(i));
        if (t == null) {
            t = (T) a(i);
        }
        if (t != null) {
            this.f3741c.put(Integer.valueOf(i), t);
        }
        return t;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        T t;
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
        if (this.f3741c == null || this.f3741c.size() <= 0 || (t = this.f3741c.get(Integer.valueOf(i))) == null) {
            return;
        }
        t.onDetach();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f3740b;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        T tB = this.f3741c.get(Integer.valueOf(i));
        a.a(f3739a, "position = " + i + ", p = " + tB);
        if (tB == null) {
            tB = b(i);
        }
        if (tB == null) {
            return null;
        }
        tB.onAttach();
        View rootView = tB.getRootView();
        ((b.b.m.d.a) rootView.getTag()).a(i);
        viewGroup.addView(rootView);
        return rootView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
