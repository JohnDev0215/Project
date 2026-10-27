package b.b.g.k;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BYDBodyworkListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends AbsBYDAutoBodyworkListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f814b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f815c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f816d = Integer.MIN_VALUE;

    public final boolean a(int i, int i2) {
        return i2 == i;
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onElecSlideDoorStateChanged(int i) {
        if (a(i, this.f815c)) {
            return;
        }
        this.f815c = i;
        super.onElecSlideDoorStateChanged(i);
        if (b.b.g.r.e.c(40017)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(40017, i));
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onFuelElecLowPowerChanged(int i) {
        super.onFuelElecLowPowerChanged(i);
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onHasMessageChanged(int i, int i2) {
        super.onHasMessageChanged(i, i2);
        BydLogHelper.logD("onHasMessageChanged id = " + i + "  ---  state = " + i2);
        if (i != 18) {
            return;
        }
        if (this.f814b == i2) {
            BydLogHelper.logW(AbsBYDAutoBodyworkListener.TAG, "onHasMessageChanged id =  0x0123 not change! ---  pre_HAS_0x012300_value = " + i2);
            return;
        }
        this.f814b = i2;
        EventBus.getDefault().post(new b.b.a.c.a(250000, i2));
        BydLogHelper.logW(AbsBYDAutoBodyworkListener.TAG, "onHasMessageChanged id =  0x0123  ---  state = " + i2);
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onPowerLevelChanged(int i) {
        if (a(i, this.f816d)) {
            return;
        }
        this.f816d = i;
        super.onPowerLevelChanged(i);
        BydLogHelper.logD("message : onPowerLevelChanged  level " + i);
        b.b.a.c.a.j(new b.b.a.c.a(250015, i));
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onWindowStateChanged(int i, int i2) {
        super.onWindowStateChanged(i, i2);
    }
}
