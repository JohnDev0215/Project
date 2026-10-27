package org.chromium.chrome.browser.browserservices.permissiondelegation;

import android.net.Uri;
import android.os.Bundle;
import defpackage.b80;
import defpackage.pp;
import defpackage.qf3;
import defpackage.rf3;
import defpackage.rh3;
import defpackage.s50;
import defpackage.sf3;
import defpackage.vd1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class InstalledWebappGeolocationBridge {
    public long a;
    public final GURL b;
    public final sf3 c;
    public final a d = new a();

    public class a extends vd1 {
        public a() {
        }

        @Override // defpackage.vd1
        public final void q(String str, Bundle bundle) {
            PostTask.c(rh3.c, new b80(this, 2, str, bundle));
        }
    }

    public InstalledWebappGeolocationBridge(long j, GURL gurl, sf3 sf3Var) {
        this.a = j;
        this.b = gurl;
        this.c = sf3Var;
    }

    @CalledByNative
    public static InstalledWebappGeolocationBridge create(long j, GURL gurl) {
        if (gurl == null) {
            return null;
        }
        return new InstalledWebappGeolocationBridge(j, gurl, ((s50) pp.d()).j());
    }

    @CalledByNative
    public void start(boolean z) {
        String strJ = this.b.j();
        sf3 sf3Var = this.c;
        sf3Var.getClass();
        sf3Var.a(Uri.parse(strJ), new qf3(sf3Var, z, this.d));
    }

    @CalledByNative
    public void stopAndDestroy() {
        this.a = 0L;
        String strJ = this.b.j();
        sf3 sf3Var = this.c;
        sf3Var.getClass();
        sf3Var.a(Uri.parse(strJ), new rf3());
    }
}
