package b.a.a.f.i1;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b extends AbsBYDAutoFragranceListener {
    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceConcentrationChanged(int i, int i2) {
        super.onFragranceConcentrationChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceConcentrationChanged type = " + i + " -- value = " + i2);
        if (b.a.a.h.a.a(1501)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1501, i, i2));
    }

    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceInstallStateChanged(int i, int i2) {
        super.onFragranceInstallStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceInstallStateChanged type = " + i + " -- state = " + i2);
        b.a.a.h.a.f(1505);
        EventBus.getDefault().post(new b.a.a.e.a(1505, i, i2));
    }

    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceLessAllowance(int i, int i2) {
        super.onFragranceLessAllowance(i, i2);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceLessAllowance value = " + i2);
        b.a.a.h.a.f(1503);
        EventBus.getDefault().post(new b.a.a.e.a(1503, i2));
    }

    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceNameChanged(int i, int i2) {
        super.onFragranceNameChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceNameChanged type = " + i + " -- value = " + i2);
        b.a.a.h.a.f(1506);
        EventBus.getDefault().post(new b.a.a.e.a(1506, i, i2));
    }

    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceResidueChanged(int i, int i2) {
        super.onFragranceResidueChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceResidueChanged type = " + i + " -- value = " + i2);
        b.a.a.h.a.f(1502);
        EventBus.getDefault().post(new b.a.a.e.a(1502, i, i2));
    }

    @Override // android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener
    public void onFragranceTypeChanged(int i) {
        super.onFragranceTypeChanged(i);
        BydLogHelper.logW(AbsBYDAutoFragranceListener.TAG, "onFragranceTypeChanged type = " + i);
        if (b.a.a.h.a.a(1500)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1500, i));
    }
}
