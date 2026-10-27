package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydTyreListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class r extends AbsBYDAutoTyreListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f868b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f869c = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onFeatureChanged(String str, int i) {
        super.onFeatureChanged(str, i);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onIndirectTyreSystemStateChanged(int i) {
        if (this.f868b == i) {
            return;
        }
        this.f868b = i;
        super.onIndirectTyreSystemStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(190001, i));
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreAirLeakStateChanged(int i, int i2) {
        super.onTyreAirLeakStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreBatteryStateChanged(int i) {
        super.onTyreBatteryStateChanged(i);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreBatteryValueChanged(int i, float f2) {
        super.onTyreBatteryValueChanged(i, f2);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyrePressureStateChanged(int i, int i2) {
        super.onTyrePressureStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyrePressureValueChanged(int i, int i2) {
        super.onTyrePressureValueChanged(i, i2);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreSignalStateChanged(int i, int i2) {
        super.onTyreSignalStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreSystemStateChanged(int i) {
        super.onTyreSystemStateChanged(i);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreTemperatureStateChanged(int i) {
        super.onTyreTemperatureStateChanged(i);
    }

    @Override // android.hardware.bydauto.tyre.AbsBYDAutoTyreListener
    public void onTyreTurnStateChanged(int i) {
        if (this.f869c == i) {
            return;
        }
        this.f869c = i;
        super.onTyreTurnStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(190000, i));
    }
}
