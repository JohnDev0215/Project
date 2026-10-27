package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydVehicleHealthListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class s extends AbsBYDAutoVehicleHealthListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f870b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f871c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f872d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f873e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f874f = Integer.MIN_VALUE;
    public int g = Integer.MIN_VALUE;
    public int h = Integer.MIN_VALUE;
    public int i = Integer.MIN_VALUE;
    public int j = Integer.MIN_VALUE;
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onBatteryHealthStatusChanged(int i) {
        if (this.l == i) {
            return;
        }
        this.l = i;
        super.onBatteryHealthStatusChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(200011, i));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onDataChanged(a.b.c cVar) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onEPBHintChanged(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        super.onEPBHintChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(200010, i));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onEPBStateChanged(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        super.onEPBStateChanged(i);
        if (b.b.g.r.e.c(200006)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(200006, i));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onError(int i, String str) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onFrontWiperCheckStateChanged(int i) {
        if (this.g == i) {
            return;
        }
        this.g = i;
        super.onFrontWiperCheckStateChanged(i);
        if (i == 3) {
            EventBus.getDefault().post(new b.b.a.c.a(200000, i));
            b.b.g.r.e.h(200000);
        } else {
            if (b.b.g.r.e.c(200000)) {
                return;
            }
            EventBus.getDefault().post(new b.b.a.c.a(200000, i));
        }
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onOilLevelChanged(int i) {
        if (this.f870b == i) {
            return;
        }
        this.f870b = i;
        super.onOilLevelChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(200005, i));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onOilLifetimeChanged(int i) {
        super.onOilLifetimeChanged(i);
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onOilMaintainanceHintChanged(int i, int i2) {
        super.onOilMaintainanceHintChanged(i, i2);
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onOilUpdateStateChanged(int i) {
        if (this.j == i) {
            return;
        }
        this.j = i;
        super.onOilUpdateStateChanged(i);
        EventBus.getDefault().post(new b.b.a.c.a(200007, i));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onRearWiperCheckStateChanged(int i) {
        if (this.h == i) {
            return;
        }
        this.h = i;
        super.onRearWiperCheckStateChanged(i);
        if (i == 3) {
            EventBus.getDefault().post(new b.b.a.c.a(200001, i));
            b.b.g.r.e.h(200001);
        } else {
            if (b.b.g.r.e.c(200001)) {
                return;
            }
            EventBus.getDefault().post(new b.b.a.c.a(200001, i));
        }
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onResetOilLifetimeChanged(int i, int i2) {
        if (this.f873e == i && this.f874f == i2) {
            return;
        }
        this.f873e = i;
        this.f874f = i2;
        super.onResetOilLifetimeChanged(i, i2);
        EventBus.getDefault().post(new b.b.a.c.a(200003, i, i2));
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onTransportModeChanged(int i) {
        super.onTransportModeChanged(i);
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onTransportModeStateHintChanged(int i) {
        super.onTransportModeStateHintChanged(i);
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onOilLifetimeChanged(int i, int i2) {
        if (this.f871c == i && this.f872d == i2) {
            return;
        }
        this.f871c = i;
        this.f872d = i2;
        super.onOilLifetimeChanged(i, i2);
        EventBus.getDefault().post(new b.b.a.c.a(200004, i, i2));
    }
}
