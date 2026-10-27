package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FirstRunPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class kn0 extends FragmentStateAdapter {
    public final List<in0> q;
    public final ArrayList r;

    public kn0(FragmentActivity fragmentActivity, ArrayList arrayList) {
        super(fragmentActivity);
        this.r = new ArrayList();
        this.q = arrayList;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public final Fragment F(int i) {
        Fragment fragment;
        in0 in0Var = this.q.get(i);
        in0Var.getClass();
        try {
            fragment = (Fragment) in0Var.a.newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            fragment = null;
        }
        ArrayList arrayList = this.r;
        for (int size = arrayList.size(); size <= i; size++) {
            arrayList.add(null);
        }
        arrayList.set(i, (hn0) fragment);
        return fragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.q.size();
    }
}
