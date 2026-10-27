package org.chromium.android_webview.permission;

import J.N;
import android.net.Uri;
import defpackage.C0248Np;
import defpackage.RunnableC0430Xr;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwPermissionRequest {
    public final Uri a;
    public final long b;
    public boolean c;
    public long d;
    public C0248Np e;

    public AwPermissionRequest(long j, Uri uri, long j2) {
        this.d = j;
        this.a = uri;
        this.b = j2;
        this.e = new C0248Np(this, new RunnableC0430Xr(this.d, null));
    }

    public static AwPermissionRequest create(long j, String str, long j2) {
        if (j == 0) {
            return null;
        }
        return new AwPermissionRequest(j, Uri.parse(str), j2);
    }

    private void destroyNative() {
        this.e.b(2);
        this.e = null;
        this.d = 0L;
    }

    public void a() {
        c();
        long j = this.d;
        if (j != 0) {
            N.Mk3Msvx7(j, this, false);
            destroyNative();
        }
        this.c = true;
    }

    public void b() {
        c();
        long j = this.d;
        if (j != 0) {
            N.Mk3Msvx7(j, this, true);
            destroyNative();
        }
        this.c = true;
    }

    public final void c() {
        if (!ThreadUtils.j()) {
            throw new IllegalStateException("Either grant() or deny() should be called on UI thread");
        }
        if (this.c) {
            throw new IllegalStateException("Either grant() or deny() has been already called.");
        }
    }
}
