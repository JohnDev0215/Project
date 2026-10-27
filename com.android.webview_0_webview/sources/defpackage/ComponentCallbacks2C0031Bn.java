package defpackage;

import J.N;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import org.chromium.android_webview.AwContents;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: Bn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ComponentCallbacks2C0031Bn implements ComponentCallbacks2 {
    public final /* synthetic */ AwContents s;

    public ComponentCallbacks2C0031Bn(AwContents awContents, C1838yn c1838yn) {
        this.s = awContents;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.s.a0();
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(final int i) {
        if (!this.s.u.getGlobalVisibleRect(AwContents.M0)) {
            AwContents.M0.setEmpty();
        }
        boolean zIsEmpty = AwContents.M0.isEmpty();
        AwContents awContents = this.s;
        final boolean z = awContents.W && awContents.X && !zIsEmpty;
        ThreadUtils.h(new Runnable(this, i, z) { // from class: An
            public final ComponentCallbacks2C0031Bn s;
            public final int t;
            public final boolean u;

            {
                this.s = this;
                this.t = i;
                this.u = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                InterfaceC0286Pr interfaceC0286Pr;
                ComponentCallbacks2C0031Bn componentCallbacks2C0031Bn = this.s;
                int i2 = this.t;
                boolean z2 = this.u;
                if (componentCallbacks2C0031Bn.s.t(0)) {
                    return;
                }
                if (i2 >= 60 && (interfaceC0286Pr = componentCallbacks2C0031Bn.s.v) != null) {
                    interfaceC0286Pr.a();
                }
                AwContents awContents2 = componentCallbacks2C0031Bn.s;
                N.MxV2T4AB(awContents2.s, awContents2, i2, z2);
            }
        });
    }
}
