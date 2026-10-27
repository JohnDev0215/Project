package com.byd.mvp.mvp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import b.b.m.e.i.b;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragmentStateAdapter<T extends XFragment> extends FragmentStateAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3729a;

    public XFragmentStateAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, int i) {
        super(fragmentManager, lifecycle);
        this.f3729a = 0;
        this.f3729a = i;
    }

    public void a() {
        BydLogHelper.logV("XFragmentStateAdapter----clear");
    }

    public T b(int i) {
        Class<? extends XFragment> clsD = d(i);
        if (clsD == null) {
            return null;
        }
        try {
            T t = (T) clsD.newInstance();
            b.d(clsD.getName());
            return t;
        } catch (IllegalAccessException | InstantiationException e2) {
            BydLogHelper.logE(e2.getMessage());
            return null;
        }
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NonNull
    public Fragment createFragment(int i) {
        return e(i);
    }

    public abstract Class<? extends XFragment> d(int i);

    public synchronized T e(int i) {
        return (T) b(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3729a;
    }
}
