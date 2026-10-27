package F0;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Y implements ComponentCallbacks2 {
    public final /* synthetic */ Configuration l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J0.c f2150m;

    public Y(Configuration configuration, J0.c cVar) {
        this.l = configuration;
        this.f2150m = cVar;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = this.l;
        int iUpdateFrom = configuration2.updateFrom(configuration);
        Iterator it = this.f2150m.f3046a.entrySet().iterator();
        while (it.hasNext()) {
            J0.a aVar = (J0.a) ((WeakReference) ((Map.Entry) it.next()).getValue()).get();
            if (aVar == null || Configuration.needNewResources(iUpdateFrom, aVar.f3043b)) {
                it.remove();
            }
        }
        configuration2.setTo(configuration);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        this.f2150m.f3046a.clear();
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i7) {
        this.f2150m.f3046a.clear();
    }
}
