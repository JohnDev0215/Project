package org.chromium.chrome.browser;

import J.N;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class NearOomMonitor implements ComponentCallbacks2 {
    public final long e;

    public NearOomMonitor(long j) {
        this.e = j;
        xz.a.registerComponentCallbacks(this);
    }

    @CalledByNative
    public static NearOomMonitor create(long j) {
        return new NearOomMonitor(j);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        N.MvDhdpTR(this.e, this);
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
    }
}
