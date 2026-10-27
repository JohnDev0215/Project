package F0;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: loaded from: classes.dex */
public final class Z implements ComponentCallbacks2 {
    public final /* synthetic */ J0.d l;

    public Z(J0.d dVar) {
        this.l = dVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        J0.d dVar = this.l;
        synchronized (dVar) {
            dVar.f3047a.a();
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        J0.d dVar = this.l;
        synchronized (dVar) {
            dVar.f3047a.a();
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i7) {
        J0.d dVar = this.l;
        synchronized (dVar) {
            dVar.f3047a.a();
        }
    }
}
