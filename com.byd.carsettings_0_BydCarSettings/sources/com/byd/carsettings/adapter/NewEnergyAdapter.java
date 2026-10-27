package com.byd.carsettings.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import com.byd.carsettings.ui.fragment.newenergy.ConsumptionManageFragment;
import com.byd.carsettings.ui.fragment.newenergy.ElectricityManageFragment;
import com.byd.carsettings.ui.fragment.newenergy.EnergyManageFragment;
import com.byd.carsettings.ui.fragment.newenergy.ITACManageFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.mvp.adapter.XFragmentStateAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class NewEnergyAdapter extends XFragmentStateAdapter<XFragment> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f2607b = 3;

    public NewEnergyAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, int i) {
        super(fragmentManager, lifecycle, i);
        f2607b = i;
    }

    public static Class<? extends XFragment> f(int i) {
        return f2607b == 4 ? i != 0 ? i != 1 ? i != 2 ? ConsumptionManageFragment.class : ElectricityManageFragment.class : EnergyManageFragment.class : ITACManageFragment.class : i != 0 ? i != 1 ? ConsumptionManageFragment.class : ElectricityManageFragment.class : EnergyManageFragment.class;
    }

    @Override // com.byd.mvp.mvp.adapter.XFragmentStateAdapter
    public Class<? extends XFragment> d(int i) {
        return f(i);
    }
}
