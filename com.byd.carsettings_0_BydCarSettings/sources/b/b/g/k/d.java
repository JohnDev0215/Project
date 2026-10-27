package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydAutoGearboxListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends AbsBYDAutoGearboxListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f824b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f825c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f826d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f827e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f828f = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onBrakeFluidLevelChanged(int i) {
        super.onBrakeFluidLevelChanged(i);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onBrakePedalStateChanged(int i) {
        if (this.f828f == i) {
            return;
        }
        this.f828f = i;
        super.onBrakePedalStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(220003, i));
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onCurrentGearChanged(int i) {
        super.onCurrentGearChanged(i);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onEPBStateChanged(int i) {
        if (this.f826d == i) {
            return;
        }
        this.f826d = i;
        super.onEPBStateChanged(i);
        BydLogHelper.logD("onEPBStateChanged " + i);
        b.b.g.r.e.h(220004);
        EventBus.getDefault().post(new b.b.a.c.a(220004, i));
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxAutoModeTypeChanged(int i) {
        if (this.f824b == i) {
            return;
        }
        this.f824b = i;
        super.onGearboxAutoModeTypeChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(220000, i));
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxManualModeLevelChanged(int i) {
        if (this.f825c == i) {
            return;
        }
        this.f825c = i;
        super.onGearboxManualModeLevelChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(220001, i));
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxStateChanged(int i) {
        super.onGearboxStateChanged(i);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onParkBrakeSwitchChanged(int i) {
        if (this.f827e == i) {
            return;
        }
        this.f827e = i;
        super.onParkBrakeSwitchChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(220002, i));
    }
}
