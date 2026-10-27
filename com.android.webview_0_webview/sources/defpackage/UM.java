package defpackage;

import org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UM implements ServiceWorkerWebSettingsBoundaryInterface {
    public C0791fp a;

    public UM(C0791fp c0791fp) {
        this.a = c0791fp;
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public boolean getAllowContentAccess() {
        C0826gN.a(17);
        return this.a.a();
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public boolean getAllowFileAccess() {
        C0826gN.a(18);
        return this.a.b();
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public boolean getBlockNetworkLoads() {
        C0826gN.a(19);
        return this.a.c();
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public int getCacheMode() {
        C0826gN.a(20);
        return this.a.d();
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public void setAllowContentAccess(boolean z) {
        C0826gN.a(21);
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.b != z) {
                c0791fp.b = z;
            }
        }
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public void setAllowFileAccess(boolean z) {
        C0826gN.a(22);
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.c != z) {
                c0791fp.c = z;
            }
        }
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public void setBlockNetworkLoads(boolean z) {
        C0826gN.a(23);
        this.a.e(z);
    }

    @Override // org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface
    public void setCacheMode(int i) {
        C0826gN.a(24);
        C0791fp c0791fp = this.a;
        synchronized (c0791fp.e) {
            if (c0791fp.a != i) {
                c0791fp.a = i;
            }
        }
    }
}
