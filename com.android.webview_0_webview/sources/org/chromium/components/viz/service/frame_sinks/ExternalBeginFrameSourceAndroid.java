package org.chromium.components.viz.service.frame_sinks;

import defpackage.C1684vy;
import defpackage.EN;
import defpackage.FN;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ExternalBeginFrameSourceAndroid {
    public final long a;
    public boolean b;
    public final FN c;
    public final EN d = new C1684vy(this);

    public ExternalBeginFrameSourceAndroid(long j, float f) {
        this.a = j;
        this.c = new FN(this.d, f);
    }

    private void setEnabled(boolean z) {
        if (this.b == z) {
            return;
        }
        this.b = z;
        if (z) {
            this.c.a();
        }
    }

    private void updateRefreshRate(float f) {
        this.c.b(f);
    }
}
