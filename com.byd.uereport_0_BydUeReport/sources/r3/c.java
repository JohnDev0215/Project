package r3;

import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import com.byd.displaymode.present.IStateChangeCallback;

/* JADX INFO: compiled from: DisplayVehicleListener.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends AbsBYDAutoSettingListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7795a = 5;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int[] f7796b = new int[5];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IStateChangeCallback f7797c;

    public c(IStateChangeCallback iStateChangeCallback) {
        this.f7797c = iStateChangeCallback;
    }

    public final void a(int i8, IStateChangeCallback.Pos pos) {
        IStateChangeCallback iStateChangeCallback;
        if (i8 <= 0 || (iStateChangeCallback = this.f7797c) == null || !iStateChangeCallback.a()) {
            return;
        }
        int iOrdinal = pos.ordinal();
        if (this.f7796b[iOrdinal] != i8) {
            this.f7796b[iOrdinal] = i8;
        }
        if (i8 == 2) {
            this.f7797c.c(128);
        } else if (c(iOrdinal)) {
            this.f7797c.b(128);
        }
    }

    public int b(IStateChangeCallback.Pos pos) {
        int iOrdinal = pos.ordinal();
        if (iOrdinal < 0 || this.f7796b == null || this.f7796b.length < iOrdinal) {
            return 0;
        }
        return this.f7796b[iOrdinal];
    }

    public final boolean c(int i8) {
        if (this.f7796b == null || this.f7796b.length == 0) {
            return true;
        }
        boolean z7 = false;
        for (int i9 = 0; i9 < this.f7796b.length; i9++) {
            if (i9 != i8) {
                z7 = this.f7796b[i9] != 2;
                if (!z7) {
                    break;
                }
            }
        }
        return z7;
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onAutoExternalRearMirrorFollowUpStateChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.REAR_MIRROR_FOLLOW);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onDriverSeatAutoReturnStateChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.SEAT_WELCOME);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearMirrorFlipChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.REAR_MIRROR_FLIP);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewEcuMirrorFlipStateChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.REAR_MIRROR_FLIP);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewMirrorFlipChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.REAR_MIRROR_FLIP);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onRearViewMirrorFlipSetChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.REAR_MIRROR_FLIP);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingNotL1PStateChanged(int i8) {
        IStateChangeCallback iStateChangeCallback;
        if (i8 <= 0 || (iStateChangeCallback = this.f7797c) == null || !iStateChangeCallback.a()) {
            return;
        }
        if (i8 == 1) {
            this.f7797c.b(48);
        } else {
            this.f7797c.c(48);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatHeatingStateChanged(int i8, int i9) {
        IStateChangeCallback iStateChangeCallback;
        if (i9 <= 0 || i8 < 0 || (iStateChangeCallback = this.f7797c) == null || !iStateChangeCallback.a()) {
            return;
        }
        if (i9 == 1) {
            this.f7797c.b(32);
        } else {
            this.f7797c.c(32);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingNotL1PStateChanged(int i8) {
        IStateChangeCallback iStateChangeCallback;
        if (i8 <= 0 || (iStateChangeCallback = this.f7797c) == null || !iStateChangeCallback.a()) {
            return;
        }
        if (i8 == 1) {
            this.f7797c.b(80);
        } else {
            this.f7797c.c(80);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSeatVentilatingStateChanged(int i8, int i9) {
        IStateChangeCallback iStateChangeCallback;
        if (i9 <= 0 || i8 < 0 || (iStateChangeCallback = this.f7797c) == null || !iStateChangeCallback.a()) {
            return;
        }
        if (i9 == 1) {
            this.f7797c.b(96);
        } else {
            this.f7797c.c(96);
        }
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSmartWelcomeLightStateChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.SMART_LIGHT_WELCOME);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerPositionAutoReturnStateChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.STEEL_WELCOME);
    }

    @Override // android.hardware.bydauto.setting.AbsBYDAutoSettingListener
    public void onSteerPositionAutoReturnSwitchChanged(int i8) {
        a(i8, IStateChangeCallback.Pos.STEEL_WELCOME);
    }
}
