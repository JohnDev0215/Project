package com.stingray.drivemodedetector;

import A7.m;
import A7.x;
import S8.AbstractC0727y;
import android.app.Activity;
import android.content.Context;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.SystemProperties;
import android.util.Log;
import h8.AbstractC1386c;
import k.AbstractC1582b;
import kotlin.Metadata;
import q7.C2195i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000I\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004*\u0001\u001d\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0016\u0010\bJ\u000f\u0010\u0017\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0017\u0010\bR\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/stingray/drivemodedetector/DriveModeDetector;", "Lcom/stingray/drivemodedetector/DriveModeDetectorBase;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lm7/q;", "initialize", "()V", "Lk/b;", "", "activityResultLauncher", "askForPermissions", "(Lk/b;)V", "Landroid/app/Activity;", "activity", "", "checkForPermissions", "(Landroid/app/Activity;)Z", "Lcom/stingray/drivemodedetector/DriveMode;", "getDriveMode", "()Lcom/stingray/drivemodedetector/DriveMode;", "registerOnDriveModeChange", "unregisterOnDriveModeChange", "Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;", "device", "Landroid/hardware/bydauto/gearbox/BYDAutoGearboxDevice;", "carType", "Ljava/lang/String;", "com/stingray/drivemodedetector/DriveModeDetector$listener$1", "listener", "Lcom/stingray/drivemodedetector/DriveModeDetector$listener$1;", "drivemodedetector_byd8155fRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DriveModeDetector extends DriveModeDetectorBase {
    private String carType;
    private BYDAutoGearboxDevice device;
    private final DriveModeDetector$listener$1 listener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.stingray.drivemodedetector.DriveModeDetector$listener$1] */
    public DriveModeDetector(Context context) {
        super(context);
        m.f(context, "context");
        String str = SystemProperties.get("persist.sys.energytype", "0");
        m.e(str, "get(...)");
        this.carType = str;
        this.listener = new AbsBYDAutoGearboxListener() { // from class: com.stingray.drivemodedetector.DriveModeDetector$listener$1
            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener, b.d
            public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearChanged(int gear) {
                a.o("=== DriveModeDetector BYD8155 onGearChanged: ", gear, "DriveModeDetector");
                DriveModeDetector driveModeDetector = this.this$0;
                driveModeDetector.onDriveModeChanged(driveModeDetector.getDriveMode());
            }

            @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
            public void onGearboxAutoModeLevelChanged(int level) {
                a.o("=== DriveModeDetector BYD8155 onGearboxAutoModeLevelChanged: ", level, "DriveModeDetector");
                DriveModeDetector driveModeDetector = this.this$0;
                driveModeDetector.onDriveModeChanged(driveModeDetector.getDriveMode());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getDriveMode$getGear(DriveModeDetector driveModeDetector) {
        String str = driveModeDetector.carType;
        if (m.a(str, "2")) {
            BYDAutoGearboxDevice bYDAutoGearboxDevice = driveModeDetector.device;
            if (bYDAutoGearboxDevice != null) {
                return bYDAutoGearboxDevice.getGearboxAutoModeLevel();
            }
            return 0;
        }
        if (m.a(str, "1")) {
            BYDAutoGearboxDevice bYDAutoGearboxDevice2 = driveModeDetector.device;
            if (bYDAutoGearboxDevice2 != null) {
                return bYDAutoGearboxDevice2.getGear();
            }
            return 0;
        }
        BYDAutoGearboxDevice bYDAutoGearboxDevice3 = driveModeDetector.device;
        if (bYDAutoGearboxDevice3 != null) {
            return bYDAutoGearboxDevice3.getGear();
        }
        return 0;
    }

    @Override // com.stingray.drivemodedetector.DriveModeDetectorBase
    public void askForPermissions(AbstractC1582b activityResultLauncher) {
        m.f(activityResultLauncher, "activityResultLauncher");
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 requestPermissions");
        try {
            activityResultLauncher.a();
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.stingray.drivemodedetector.DriveModeDetectorBase
    public boolean checkForPermissions(Activity activity) {
        m.f(activity, "activity");
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 checkPermissions");
        try {
            return AbstractC1386c.c(activity, BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) == 0;
        } catch (Exception e10) {
            e10.printStackTrace();
            return false;
        }
    }

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public DriveMode getDriveMode() throws Throwable {
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 getDriveMode");
        try {
            x xVar = new x();
            xVar.l = getDriveMode$getGear(this);
            long jCurrentTimeMillis = System.currentTimeMillis();
            x xVar2 = new x();
            while (xVar.l == 0 && xVar2.l < 100) {
                AbstractC0727y.A(C2195i.l, new DriveModeDetector$getDriveMode$time$1$1(xVar, xVar2, this, null));
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 device.gear = " + xVar.l + " (time: " + jCurrentTimeMillis2 + ")");
            return xVar.l == 1 ? DriveMode.PARKED : DriveMode.DRIVING;
        } catch (Exception e10) {
            e10.printStackTrace();
            return DriveMode.DRIVING;
        }
    }

    @Override // com.stingray.drivemodedetector.DriveModeDetectorBase
    public void initialize() {
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 init");
        try {
            this.device = BYDAutoGearboxDevice.getInstance(getContext());
            Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 carType: " + this.carType);
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.stingray.drivemodedetector.DriveModeDetectorBase
    public void registerOnDriveModeChange() {
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 registerOnDriveModeChange");
        try {
            BYDAutoGearboxDevice bYDAutoGearboxDevice = this.device;
            if (bYDAutoGearboxDevice != null) {
                bYDAutoGearboxDevice.registerListener((AbsBYDAutoGearboxListener) this.listener);
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }

    @Override // com.stingray.drivemodedetector.DriveModeDetectorBase
    public void unregisterOnDriveModeChange() {
        Log.d("DriveModeDetector", "=== DriveModeDetector BYD8155 unregisterOnDriveModeChange");
        try {
            BYDAutoGearboxDevice bYDAutoGearboxDevice = this.device;
            if (bYDAutoGearboxDevice != null) {
                bYDAutoGearboxDevice.unregisterListener((AbsBYDAutoGearboxListener) this.listener);
            }
        } catch (Exception e10) {
            e10.printStackTrace();
        }
    }
}
