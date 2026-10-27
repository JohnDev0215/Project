package com.byd.dipilot;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import b.b.m.e.i.b;
import com.byd.dipilot.BaseFragment;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFragmentStateAdapter<T extends BaseFragment> extends FragmentStateAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3620b;

    public BaseFragmentStateAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, int i) {
        super(fragmentManager, lifecycle);
        this.f3619a = "BaseFragmentStateAdapter";
        this.f3620b = 0;
        this.f3620b = i;
    }

    public T a(int i) {
        Class<? extends BaseFragment> clsB = b(i);
        if (clsB == null) {
            return null;
        }
        try {
            T t = (T) clsB.newInstance();
            b.d(clsB.getName());
            return t;
        } catch (IllegalAccessException | InstantiationException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public abstract Class<? extends BaseFragment> b(int i);

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    @NonNull
    public Fragment createFragment(int i) {
        return d(i);
    }

    public synchronized T d(int i) {
        return (T) a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3620b;
    }
}
