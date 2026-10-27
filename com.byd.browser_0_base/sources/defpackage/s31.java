package defpackage;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: JourneyLogger.java */
/* JADX INFO: loaded from: classes3.dex */
public final class s31 {
    public long a;
    public boolean b;

    public s31(WebContents webContents, boolean z) {
        this.a = N.MgtvkzAJ(this, z, webContents);
    }

    public final void a(int i) {
        if (this.b) {
            return;
        }
        this.b = true;
        N.MMB_UdCu(this.a, this, i);
    }
}
