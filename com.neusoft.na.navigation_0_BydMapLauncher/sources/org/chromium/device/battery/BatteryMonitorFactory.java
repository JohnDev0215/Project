package org.chromium.device.battery;

import android.content.Context;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.device.battery.BatteryStatusManager;
import org.chromium.mojom.device.BatteryMonitor;
import org.chromium.mojom.device.BatteryStatus;

/* JADX INFO: loaded from: classes3.dex */
public class BatteryMonitorFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String TAG = "BatteryMonitorFactory";
    private final BatteryStatusManager.BatteryStatusCallback mCallback;
    private final BatteryStatusManager mManager;
    private final HashSet<BatteryMonitorImpl> mSubscribedMonitors = new HashSet<>();

    public BatteryMonitorFactory(Context context) {
        BatteryStatusManager.BatteryStatusCallback batteryStatusCallback = new BatteryStatusManager.BatteryStatusCallback() { // from class: org.chromium.device.battery.BatteryMonitorFactory.1
            @Override // org.chromium.device.battery.BatteryStatusManager.BatteryStatusCallback
            public void onBatteryStatusChanged(BatteryStatus batteryStatus) {
                ThreadUtils.assertOnUiThread();
                Iterator it = BatteryMonitorFactory.this.mSubscribedMonitors.iterator();
                while (it.hasNext()) {
                    ((BatteryMonitorImpl) it.next()).didChange(batteryStatus);
                }
            }
        };
        this.mCallback = batteryStatusCallback;
        this.mManager = new BatteryStatusManager(context, batteryStatusCallback);
    }

    public BatteryMonitor createMonitor() {
        ThreadUtils.assertOnUiThread();
        if (this.mSubscribedMonitors.isEmpty() && !this.mManager.start()) {
            Log.e(TAG, "BatteryStatusManager failed to start.");
        }
        BatteryMonitorImpl batteryMonitorImpl = new BatteryMonitorImpl(this);
        this.mSubscribedMonitors.add(batteryMonitorImpl);
        return batteryMonitorImpl;
    }

    void unsubscribe(BatteryMonitorImpl batteryMonitorImpl) {
        ThreadUtils.assertOnUiThread();
        this.mSubscribedMonitors.remove(batteryMonitorImpl);
        if (this.mSubscribedMonitors.isEmpty()) {
            this.mManager.stop();
        }
    }
}
