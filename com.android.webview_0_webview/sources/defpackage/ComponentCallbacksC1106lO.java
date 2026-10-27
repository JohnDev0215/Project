package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import org.chromium.ui.gfx.ViewConfigurationHelper;

/* JADX INFO: renamed from: lO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ComponentCallbacksC1106lO implements ComponentCallbacks {
    public final /* synthetic */ ViewConfigurationHelper s;

    public ComponentCallbacksC1106lO(ViewConfigurationHelper viewConfigurationHelper) {
        this.s = viewConfigurationHelper;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        ViewConfigurationHelper.a(this.s);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }
}
