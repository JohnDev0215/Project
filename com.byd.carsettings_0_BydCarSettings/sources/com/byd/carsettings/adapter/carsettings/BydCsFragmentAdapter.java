package com.byd.carsettings.adapter.carsettings;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import com.byd.carsettings.ui.fragment.carsettings.AiChassisFragment;
import com.byd.carsettings.ui.fragment.carsettings.AirConderFragment;
import com.byd.carsettings.ui.fragment.carsettings.CarDiBarFragment;
import com.byd.carsettings.ui.fragment.carsettings.CarLightsFragment;
import com.byd.carsettings.ui.fragment.carsettings.CarSetSeatFragment;
import com.byd.carsettings.ui.fragment.carsettings.CockpitPerFragment;
import com.byd.carsettings.ui.fragment.carsettings.ComfortDrFragment;
import com.byd.carsettings.ui.fragment.carsettings.DoorLocksFragment;
import com.byd.carsettings.ui.fragment.carsettings.FreezerFragment;
import com.byd.carsettings.ui.fragment.carsettings.HudUpShowFragment;
import com.byd.carsettings.ui.fragment.carsettings.InstrumentFragment;
import com.byd.carsettings.ui.fragment.carsettings.RearViewMirrorFragment;
import com.byd.carsettings.ui.fragment.carsettings.SmtRemindFragment;
import com.byd.carsettings.ui.fragment.carsettings.TrailerModeFragment;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.mvp.adapter.XFragmentStateAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BydCsFragmentAdapter extends XFragmentStateAdapter<XFragment> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<Integer> f2647b;

    public BydCsFragmentAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, List<Integer> list) {
        super(fragmentManager, lifecycle, list.size());
        this.f2647b = list;
    }

    public static Class<? extends XFragment> f(int i) {
        switch (i) {
            case 0:
                return AiChassisFragment.class;
            case 1:
                return CarLightsFragment.class;
            case 2:
                return ComfortDrFragment.class;
            case 3:
                return CarDiBarFragment.class;
            case 4:
                return FreezerFragment.class;
            case 5:
                return HudUpShowFragment.class;
            case 6:
                return InstrumentFragment.class;
            case 7:
                return AirConderFragment.class;
            case 8:
                return CarSetSeatFragment.class;
            case 9:
                return DoorLocksFragment.class;
            case 10:
                return SmtRemindFragment.class;
            case 11:
                return TrailerModeFragment.class;
            case 12:
                return CockpitPerFragment.class;
            case 13:
                return RearViewMirrorFragment.class;
            default:
                try {
                    return Class.forName("com.byd.test.fragment.CanSignalTestFragment");
                } catch (Exception unused) {
                    return AiChassisFragment.class;
                }
        }
    }

    @Override // com.byd.mvp.mvp.adapter.XFragmentStateAdapter
    public Class<? extends XFragment> d(int i) {
        return f(this.f2647b.get(i).intValue());
    }
}
