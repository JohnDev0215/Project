package com.byd.mvp.mvp.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import b.a.d.d.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class XPagerAdapter<T extends d> extends PagerAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f667b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile List<T> f666a = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public XPagerAdapter<T>.a f668c = new a(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ConcurrentHashMap<Class, ?> f669d = new ConcurrentHashMap<>();

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<XPagerAdapter> f670a;

        public a(XPagerAdapter xPagerAdapter) {
            this.f670a = new WeakReference<>(xPagerAdapter);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
        if (this.f666a == null || this.f666a.isEmpty()) {
            return;
        }
        this.f666a.get(i).i();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f666a.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        T t = this.f666a.get(i);
        t.h();
        View viewG = t.g();
        ((b.a.d.c.a) viewG.getTag()).a(i);
        viewGroup.addView(viewG);
        return viewG;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
