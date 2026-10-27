package com.byd.carsettings.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import com.byd.carsettings.ui.fragment.carhealthy.CarMessageFragment;
import com.byd.carsettings.ui.fragment.carhealthy.MaintainServiceFragment;
import com.byd.carsettings.ui.fragment.carhealthy.OverhaulFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.mvp.adapter.XFragmentStateAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class CarHealthyAdapter extends XFragmentStateAdapter<XFragment> {
    public CarHealthyAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle, 3);
    }

    public static Class<? extends XFragment> f(int i) {
        return i != 0 ? i != 1 ? CarMessageFragment.class : OverhaulFragment.class : MaintainServiceFragment.class;
    }

    @Override // com.byd.mvp.mvp.adapter.XFragmentStateAdapter
    public Class<? extends XFragment> d(int i) {
        return f(i);
    }
}
