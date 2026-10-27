package android.car.content.pm;

import android.app.PendingIntent;
import android.car.XCar;
import android.car.XCarManagerBase;
import android.content.ComponentName;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public final class XCarPackageManager extends XCarManagerBase {
    private static final String TAG = "CarPackageManager";

    public XCarPackageManager(XCar xCar, IBinder iBinder) {
        super(xCar);
    }

    public boolean isActivityBackedBySafeActivity(ComponentName componentName) {
        return true;
    }

    public boolean isActivityDistractionOptimized(String str, String str2) {
        return false;
    }

    public boolean isPendingIntentDistractionOptimized(PendingIntent pendingIntent) {
        return false;
    }

    public boolean isServiceDistractionOptimized(String str, String str2) {
        return true;
    }

    @Override // android.car.XCarManagerBase
    public void onCarDisconnected() {
    }

    public void setEnableActivityBlocking(boolean z) {
    }
}
