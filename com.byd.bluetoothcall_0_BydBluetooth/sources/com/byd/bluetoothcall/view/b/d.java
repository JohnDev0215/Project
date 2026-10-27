package com.byd.bluetoothcall.view.b;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends j {
    private List<Fragment> g;

    public d(androidx.fragment.app.g gVar, List<Fragment> list) {
        super(gVar);
        this.g = new ArrayList();
        this.g = list;
    }

    @Override // androidx.viewpager.widget.a
    public int e() {
        return this.g.size();
    }

    @Override // androidx.fragment.app.j, androidx.viewpager.widget.a
    public Object j(ViewGroup viewGroup, int i) {
        return super.j(viewGroup, i);
    }

    @Override // androidx.fragment.app.j
    public Fragment v(int i) {
        return this.g.get(i);
    }
}
