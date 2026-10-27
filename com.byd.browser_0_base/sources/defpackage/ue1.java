package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: compiled from: MemoryPressureMonitor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ue1 implements ComponentCallbacks2 {
    public final /* synthetic */ ve1 e;

    public ue1(ve1 ve1Var) {
        this.e = ve1Var;
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.e.a(2);
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        Integer num = (i >= 80 || i == 15) ? 2 : i >= 40 ? 1 : null;
        if (num != null) {
            this.e.a(num.intValue());
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }
}
