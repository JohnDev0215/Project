package com.byd.carsettings.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import com.byd.carsettings.ui.fragment.dilink.AppManageFragment;
import com.byd.carsettings.ui.fragment.dilink.DisplayFragment;
import com.byd.carsettings.ui.fragment.dilink.LinkFragment;
import com.byd.carsettings.ui.fragment.dilink.SoundFragment;
import com.byd.carsettings.ui.fragment.dilink.SystemFragment;
import com.byd.carsettings.ui.fragment.dilink.VersionManageFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.mvp.adapter.XFragmentStateAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class DiLinkPagerAdapter extends XFragmentStateAdapter<XFragment> {
    public DiLinkPagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle, g());
    }

    public static Class<? extends XFragment> f(int i) {
        if (i == 0) {
            return LinkFragment.class;
        }
        if (i == 1) {
            return SoundFragment.class;
        }
        if (i == 2) {
            return DisplayFragment.class;
        }
        if (i == 3) {
            return VersionManageFragment.class;
        }
        if (i == 4) {
            return SystemFragment.class;
        }
        if (i != 5) {
            return null;
        }
        return AppManageFragment.class;
    }

    public static int g() {
        return 6;
    }

    @Override // com.byd.mvp.mvp.adapter.XFragmentStateAdapter
    public Class<? extends XFragment> d(int i) {
        return f(i);
    }
}
