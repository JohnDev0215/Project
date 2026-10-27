package com.byd.carsettings.adapter;

import com.byd.carsettings.ui.fragment.CarHealthyFragment;
import com.byd.carsettings.ui.fragment.CarSettingFragment;
import com.byd.carsettings.ui.fragment.CommonFragment;
import com.byd.carsettings.ui.fragment.DiLinkFragment;
import com.byd.carsettings.ui.fragment.DiPilotFragment;
import com.byd.carsettings.ui.fragment.NewEnergyFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.mvp.adapter.XFragmentStateAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class MainFragmentPageAdapter extends XFragmentStateAdapter<XFragment> {
    public static Class<? extends XFragment> f(int i) {
        if (i == 0) {
            return CommonFragment.class;
        }
        if (i == 1) {
            return DiLinkFragment.class;
        }
        if (i == 2) {
            return DiPilotFragment.class;
        }
        if (i == 3) {
            return NewEnergyFragment.class;
        }
        if (i == 4) {
            return CarSettingFragment.class;
        }
        if (i != 5) {
            return null;
        }
        return CarHealthyFragment.class;
    }

    @Override // com.byd.mvp.mvp.adapter.XFragmentStateAdapter
    public Class<? extends XFragment> d(int i) {
        return f(i);
    }
}
