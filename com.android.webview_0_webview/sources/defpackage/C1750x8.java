package defpackage;

import android.webkit.ServiceWorkerWebSettings;

/* JADX INFO: renamed from: x8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1750x8 extends ServiceWorkerWebSettings {
    public C0791fp a;

    public C1750x8(C0791fp c0791fp) {
        this.a = c0791fp;
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public boolean getAllowContentAccess() {
        return this.a.a();
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public boolean getAllowFileAccess() {
        return this.a.b();
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public synchronized boolean getBlockNetworkLoads() {
        return this.a.c();
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public int getCacheMode() {
        return this.a.d();
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public void setAllowContentAccess(boolean z) {
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.b != z) {
                c0791fp.b = z;
            }
        }
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public void setAllowFileAccess(boolean z) {
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.c != z) {
                c0791fp.c = z;
            }
        }
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public synchronized void setBlockNetworkLoads(boolean z) {
        this.a.e(z);
    }

    @Override // android.webkit.ServiceWorkerWebSettings
    public void setCacheMode(int i) {
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.a != i) {
                c0791fp.a = i;
            }
        }
    }
}
