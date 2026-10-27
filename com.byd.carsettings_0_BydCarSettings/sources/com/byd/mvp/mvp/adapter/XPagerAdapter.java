package com.byd.mvp.mvp.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import b.b.m.b.b;
import b.b.m.e.f;
import com.byd.mvp.callback.PagerBtnType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class XPagerAdapter<T extends f> extends PagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3732a = "XPagerAdapter";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f3734c = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile List<T> f3733b = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public XPagerAdapter<T>.a f3735d = new a(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConcurrentHashMap<Class, b> f3736e = new ConcurrentHashMap<>();

    public class a implements b.b.m.b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<XPagerAdapter> f3737a;

        public a(XPagerAdapter xPagerAdapter) {
            this.f3737a = new WeakReference<>(xPagerAdapter);
        }

        @Override // b.b.m.b.a
        public void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
            WeakReference<XPagerAdapter> weakReference = this.f3737a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f3737a.get().e(cls, pagerBtnType, obj, obj2);
        }
    }

    public void b(T t) {
        this.f3733b.add(t);
    }

    public boolean c(int i) {
        return this.f3733b == null || this.f3733b.size() <= i || this.f3733b.get(i) == null;
    }

    public void d(int i) {
        b.b.m.c.a.a(f3732a, "index = " + i + ", " + getClass().getName());
        if (c(i)) {
            return;
        }
        this.f3734c = i;
        this.f3733b.get(i).loadData();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
        if (this.f3733b == null || this.f3733b.isEmpty()) {
            return;
        }
        this.f3733b.get(i).onDetach();
    }

    public final void e(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        b bVar;
        if (this.f3736e.size() <= 0) {
            return;
        }
        int size = this.f3733b.size();
        for (int i = 0; i < size; i++) {
            if (this.f3733b.get(i).getClass().equals(cls) && (bVar = this.f3736e.get(cls)) != null) {
                bVar.a(i, pagerBtnType, obj, obj2);
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f3733b.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        T t = this.f3733b.get(i);
        t.onAttach();
        View rootView = t.getRootView();
        ((b.b.m.d.a) rootView.getTag()).a(i);
        viewGroup.addView(rootView);
        return rootView;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
