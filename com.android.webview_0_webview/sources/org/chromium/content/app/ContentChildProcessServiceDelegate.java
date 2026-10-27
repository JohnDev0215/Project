package org.chromium.content.app;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.Surface;
import defpackage.AbstractBinderC1700wD;
import defpackage.Ay;
import defpackage.C1569tt;
import defpackage.C1794xy;
import defpackage.Cu;
import defpackage.InterfaceC1755xD;
import defpackage.Ts;
import java.util.List;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.UnguessableToken;
import org.chromium.base.library_loader.Linker;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentChildProcessServiceDelegate implements Cu {
    public C1794xy a;
    public InterfaceC1755xD b;
    public int c;
    public long d;
    public SparseArray e;

    public ContentChildProcessServiceDelegate() {
        if (BuildInfo.b()) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new Ay());
    }

    private void forwardSurfaceForSurfaceRequest(UnguessableToken unguessableToken, Surface surface) {
        InterfaceC1755xD interfaceC1755xD = this.b;
        try {
            if (interfaceC1755xD == null) {
                Ts.a("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            } else {
                interfaceC1755xD.n(unguessableToken, surface);
            }
        } catch (RemoteException e) {
            Ts.a("ContentCPSDelegate", "Unable to call forwardSurfaceForSurfaceRequest: %s", e);
        } finally {
            surface.release();
        }
    }

    private SurfaceWrapper getViewSurface(int i) {
        InterfaceC1755xD interfaceC1755xD = this.b;
        if (interfaceC1755xD == null) {
            Ts.a("ContentCPSDelegate", "No callback interface has been provided.", new Object[0]);
            return null;
        }
        try {
            return interfaceC1755xD.t(i);
        } catch (RemoteException e) {
            Ts.a("ContentCPSDelegate", "Unable to call getViewSurface: %s", e);
            return null;
        }
    }

    private void setFileDescriptorsIdsToKeys(int[] iArr, String[] strArr) {
        this.e = new SparseArray();
        for (int i = 0; i < iArr.length; i++) {
            this.e.put(iArr[i], strArr[i]);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(2:4|(9:6|24|10|54|24|33|6f|37|38)(3:14|36|18))(1:22)|23|54|24|33|6f) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004a, code lost:
    
        if (r3 != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004c, code lost:
    
        defpackage.Ts.f("ContentCPSDelegate", "Failed to load native library with shared RELRO, retrying without", new java.lang.Object[0]);
        java.util.Objects.requireNonNull(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
    
        monitor-enter(org.chromium.base.library_loader.Linker.e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
    
        r0.a();
        r0.b = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0061, code lost:
    
        defpackage.C1569tt.l.i(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007e, code lost:
    
        throw r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r7) {
        /*
            r6 = this;
            tt r0 = defpackage.C1569tt.l
            java.util.Objects.requireNonNull(r0)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            org.chromium.base.JNIUtils.a = r0
            tt r0 = defpackage.C1569tt.l
            boolean r0 = r0.n()
            r1 = 0
            if (r0 == 0) goto L41
            org.chromium.base.library_loader.Linker r0 = org.chromium.base.library_loader.Linker.b()
            xy r2 = r6.a
            boolean r3 = r2.b
            if (r3 == 0) goto L31
            r3 = 1
            long r4 = r2.a
            java.util.Objects.requireNonNull(r0)
            java.lang.Object r2 = org.chromium.base.library_loader.Linker.e
            monitor-enter(r2)
            r0.a()     // Catch: java.lang.Throwable -> L2e
            r0.b = r1     // Catch: java.lang.Throwable -> L2e
            r0.c = r4     // Catch: java.lang.Throwable -> L2e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2e
            goto L43
        L2e:
            r7 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2e
            throw r7
        L31:
            java.util.Objects.requireNonNull(r0)
            java.lang.Object r2 = org.chromium.base.library_loader.Linker.e
            monitor-enter(r2)
            r0.a()     // Catch: java.lang.Throwable -> L3e
            r0.b = r1     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            goto L42
        L3e:
            r7 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            throw r7
        L41:
            r0 = 0
        L42:
            r3 = 0
        L43:
            tt r2 = defpackage.C1569tt.l     // Catch: defpackage.C1734wt -> L49
            r2.i(r7)     // Catch: defpackage.C1734wt -> L49
            goto L66
        L49:
            r2 = move-exception
            if (r3 == 0) goto L7e
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "ContentCPSDelegate"
            java.lang.String r4 = "Failed to load native library with shared RELRO, retrying without"
            defpackage.Ts.f(r3, r4, r2)
            java.util.Objects.requireNonNull(r0)
            java.lang.Object r3 = org.chromium.base.library_loader.Linker.e
            monitor-enter(r3)
            r0.a()     // Catch: java.lang.Throwable -> L7b
            r0.b = r1     // Catch: java.lang.Throwable -> L7b
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7b
            tt r0 = defpackage.C1569tt.l
            r0.i(r7)
        L66:
            tt r7 = defpackage.C1569tt.l
            r7.k()
            tt r7 = defpackage.C1569tt.l
            java.lang.Object r0 = r7.c
            monitor-enter(r0)
            r7.e()     // Catch: java.lang.Throwable -> L78
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L78
            J.N.M0zXFFiu(r6)
            return
        L78:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L78
            throw r7
        L7b:
            r7 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L7b
            throw r7
        L7e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.app.ContentChildProcessServiceDelegate.a(android.content.Context):void");
    }

    public void b(Bundle bundle, List list) {
        this.b = (list == null || list.isEmpty()) ? null : AbstractBinderC1700wD.c((IBinder) list.get(0));
        this.c = bundle.getInt("com.google.android.apps.chrome.extra.cpu_count");
        this.d = bundle.getLong("com.google.android.apps.chrome.extra.cpu_features");
        if (C1569tt.l.n()) {
            Objects.requireNonNull(C1569tt.l);
            Bundle bundle2 = bundle.getBundle("org.chromium.base.android.linker.shared_relros");
            if (bundle2 != null) {
                Linker linkerB = Linker.b();
                Objects.requireNonNull(linkerB);
                Object obj = Linker.e;
                synchronized (obj) {
                    linkerB.a = (Linker.LibInfo) bundle2.getParcelable("libinfo");
                    obj.notifyAll();
                }
            }
        }
    }
}
