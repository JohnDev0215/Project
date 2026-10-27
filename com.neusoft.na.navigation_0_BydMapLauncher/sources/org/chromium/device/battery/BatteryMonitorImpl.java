package org.chromium.device.battery;

import android.util.Log;
import org.chromium.mojo.system.MojoException;
import org.chromium.mojom.device.BatteryMonitor;
import org.chromium.mojom.device.BatteryStatus;

/* JADX INFO: loaded from: classes3.dex */
public class BatteryMonitorImpl implements BatteryMonitor {
    private static final String TAG = "BatteryMonitorImpl";
    private BatteryMonitor.QueryNextStatusResponse mCallback;
    private final BatteryMonitorFactory mFactory;
    private BatteryStatus mStatus;
    private boolean mHasStatusToReport = false;
    private boolean mSubscribed = true;

    public BatteryMonitorImpl(BatteryMonitorFactory batteryMonitorFactory) {
        this.mFactory = batteryMonitorFactory;
    }

    private void unsubscribe() {
        if (this.mSubscribed) {
            this.mFactory.unsubscribe(this);
            this.mSubscribed = false;
        }
    }

    @Override // org.chromium.mojo.bindings.Interface, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        unsubscribe();
    }

    @Override // org.chromium.mojo.bindings.ConnectionErrorHandler
    public void onConnectionError(MojoException mojoException) {
        unsubscribe();
    }

    @Override // org.chromium.mojom.device.BatteryMonitor
    public void queryNextStatus(BatteryMonitor.QueryNextStatusResponse queryNextStatusResponse) {
        if (this.mCallback != null) {
            Log.e(TAG, "Overlapped call to queryNextStatus!");
            unsubscribe();
        } else {
            this.mCallback = queryNextStatusResponse;
            if (this.mHasStatusToReport) {
                reportStatus();
            }
        }
    }

    void didChange(BatteryStatus batteryStatus) {
        this.mStatus = batteryStatus;
        this.mHasStatusToReport = true;
        if (this.mCallback != null) {
            reportStatus();
        }
    }

    void reportStatus() {
        this.mCallback.call(this.mStatus);
        this.mCallback = null;
        this.mHasStatusToReport = false;
    }
}
