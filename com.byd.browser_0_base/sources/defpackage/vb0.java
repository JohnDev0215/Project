package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.download.a;

/* JADX INFO: compiled from: DownloadBroadcastManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class vb0 extends n3 {
    public final /* synthetic */ Intent H;
    public final /* synthetic */ ny I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final /* synthetic */ boolean f99J;
    public final /* synthetic */ a K;

    public vb0(a aVar, Intent intent, ny nyVar, boolean z) {
        this.K = aVar;
        this.H = intent;
        this.I = nyVar;
        this.f99J = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00bb  */
    @Override // defpackage.n3, defpackage.nj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void K() {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vb0.K():void");
    }

    @Override // defpackage.nj
    public final boolean f1() {
        if (n3.N0(this.I)) {
            return !"org.chromium.chrome.browser.download.DOWNLOAD_OPEN".equals(this.H.getAction());
        }
        return false;
    }
}
