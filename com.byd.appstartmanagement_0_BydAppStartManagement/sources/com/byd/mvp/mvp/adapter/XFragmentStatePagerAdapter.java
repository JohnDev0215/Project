package com.byd.mvp.mvp.adapter;

import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentStatePagerAdapter;
import b.a.d.b.a;
import b.a.d.d.g.b;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragmentStatePagerAdapter<T extends XFragment> extends FragmentStatePagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ConcurrentHashMap<Integer, T> f665b;

    public T a(int i) {
        Class<? extends XFragment> clsB = b(i);
        if (clsB == null) {
            return null;
        }
        try {
            return (T) clsB.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            BydLogHelper.logE(e.getMessage());
            return null;
        }
    }

    public abstract Class<? extends XFragment> b(int i);

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    @NonNull
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public XFragment getItem(int i) {
        return d(i);
    }

    public synchronized T d(int i) {
        T t = this.f665b.get(Integer.valueOf(i));
        for (Integer num : this.f665b.keySet()) {
            if (Math.abs(num.intValue() - i) > 1 && this.f665b.containsKey(num)) {
                this.f665b.remove(num);
            }
        }
        if (t != null) {
            return t;
        }
        Class<? extends XFragment> clsB = b(i);
        if (clsB == null) {
            return null;
        }
        T t2 = (T) b.b(clsB.getName());
        if (t2 == null) {
            t2 = (T) a(i);
        } else {
            b.c(clsB.getName());
        }
        this.f665b.put(Integer.valueOf(i), t2);
        return t2;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        ConcurrentHashMap<Integer, T> concurrentHashMap = this.f665b;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i));
        }
        super.destroyItem(viewGroup, i, obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f664a;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void restoreState(@Nullable Parcelable parcelable, @Nullable ClassLoader classLoader) {
        try {
            super.restoreState(parcelable, classLoader);
        } catch (RuntimeException e) {
            a.c("XFragmentStatePagerAdapter", "e:" + e.getMessage());
        }
    }
}
