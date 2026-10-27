package org.chromium.content.browser;

import android.content.Context;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.LocationProviderFactory;

/* JADX INFO: loaded from: classes3.dex */
public class LocationProviderAdapter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private LocationProviderFactory.LocationProvider mImpl;

    private static native void nativeNewErrorAvailable(String str);

    private static native void nativeNewLocationAvailable(double d, double d2, double d3, boolean z, double d4, boolean z2, double d5, boolean z3, double d6, boolean z4, double d7);

    private LocationProviderAdapter(Context context) {
        this.mImpl = LocationProviderFactory.get(context);
    }

    static LocationProviderAdapter create(Context context) {
        return new LocationProviderAdapter(context);
    }

    public boolean start(final boolean z) {
        ThreadUtils.runOnUiThread(new FutureTask(new Runnable() { // from class: org.chromium.content.browser.LocationProviderAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                LocationProviderAdapter.this.mImpl.start(z);
            }
        }, null));
        return true;
    }

    public void stop() {
        ThreadUtils.runOnUiThread(new FutureTask(new Runnable() { // from class: org.chromium.content.browser.LocationProviderAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                LocationProviderAdapter.this.mImpl.stop();
            }
        }, null));
    }

    public boolean isRunning() {
        return this.mImpl.isRunning();
    }

    public static void newLocationAvailable(double d, double d2, double d3, boolean z, double d4, boolean z2, double d5, boolean z3, double d6, boolean z4, double d7) {
        nativeNewLocationAvailable(d, d2, d3, z, d4, z2, d5, z3, d6, z4, d7);
    }

    public static void newErrorAvailable(String str) {
        nativeNewErrorAvailable(str);
    }
}
