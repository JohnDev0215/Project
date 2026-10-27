package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Bt implements ComponentCallbacks2 {
    public final /* synthetic */ Ct s;

    public Bt(Ct ct) {
        this.s = ct;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.s.c(2);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        Integer numB = Ct.b(i);
        if (numB != null) {
            this.s.c(numB.intValue());
        }
    }
}
