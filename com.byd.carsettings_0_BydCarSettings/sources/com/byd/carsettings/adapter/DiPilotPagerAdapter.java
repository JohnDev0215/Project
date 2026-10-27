package com.byd.carsettings.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import b.b.g.r.r;
import b.b.m.c.a;
import com.byd.base.hilt.Router;
import com.byd.dipilot.BaseFragment;
import com.byd.dipilot.BaseFragmentStateAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class DiPilotPagerAdapter extends BaseFragmentStateAdapter<BaseFragment> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2596c;

    public DiPilotPagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, int i) {
        super(fragmentManager, lifecycle, i);
        this.f2596c = "DiPilotPagerAdapter";
        a.d("DiPilotPagerAdapter", "pageSize =" + i);
    }

    @Override // com.byd.dipilot.BaseFragmentStateAdapter
    public Class<? extends BaseFragment> b(int i) {
        return r.c(Router.b().f().i(i));
    }
}
