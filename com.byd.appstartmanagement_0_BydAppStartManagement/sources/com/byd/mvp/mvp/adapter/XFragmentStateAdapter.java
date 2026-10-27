package com.byd.mvp.mvp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import b.a.d.d.g.b;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XFragmentStateAdapter<T extends XFragment> extends FragmentStateAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f663b;

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NonNull
    public Fragment createFragment(int i) {
        return f(i);
    }

    public T d(int i) {
        Class<? extends XFragment> clsE = e(i);
        if (clsE == null) {
            return null;
        }
        try {
            T t = (T) clsE.newInstance();
            b.c(clsE.getName());
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            BydLogHelper.logE(e.getMessage());
            return null;
        }
    }

    public abstract Class<? extends XFragment> e(int i);

    public synchronized T f(int i) {
        return (T) d(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f663b;
    }
}
