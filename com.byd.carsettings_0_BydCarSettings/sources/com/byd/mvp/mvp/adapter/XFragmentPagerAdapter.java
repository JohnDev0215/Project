package com.byd.mvp.mvp.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import b.b.m.c.a;
import b.b.m.e.i.b;
import com.byd.mvp.mvp.XFragment;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragmentPagerAdapter<T extends XFragment> extends FragmentPagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConcurrentHashMap<Integer, T> f3728b;

    public T a(int i) {
        Class<? extends XFragment> clsB = b(i);
        a.a("XFragmentPagerAdapter", "clazz = " + clsB.getName());
        try {
            return (T) clsB.newInstance();
        } catch (IllegalAccessException | InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public abstract Class<? extends XFragment> b(int i);

    public synchronized T c(int i) {
        T t = this.f3728b.get(Integer.valueOf(i));
        if (t != null) {
            return t;
        }
        Class<? extends XFragment> clsB = b(i);
        if (clsB == null) {
            return null;
        }
        T t2 = (T) b.c(clsB.getName());
        a.a("XFragmentPagerAdapter", "f = " + t2);
        if (t2 == null) {
            t2 = (T) a(i);
        } else {
            b.d(clsB.getName());
        }
        this.f3728b.put(Integer.valueOf(i), t2);
        return t2;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        ConcurrentHashMap<Integer, T> concurrentHashMap = this.f3728b;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i));
        }
        super.destroyItem(viewGroup, i, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f3727a;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    @NonNull
    public Fragment getItem(int i) {
        return c(i);
    }
}
