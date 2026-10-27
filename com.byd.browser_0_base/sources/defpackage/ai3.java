package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import org.chromium.chrome.browser.metrics.UmaSessionStats;

/* JADX INFO: compiled from: UmaSessionStats.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ai3 implements ComponentCallbacks {
    public final /* synthetic */ UmaSessionStats e;

    public ai3(UmaSessionStats umaSessionStats) {
        this.e = umaSessionStats;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.e.e = configuration.keyboard != 1;
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
