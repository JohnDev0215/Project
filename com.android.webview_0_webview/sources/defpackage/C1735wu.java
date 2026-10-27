package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: renamed from: wu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1735wu {
    public final Handler a;
    public final AbstractC1680vu b;
    public final String[] c;
    public final FileDescriptorInfo[] d;
    public final Xt e;
    public final List f;
    public C1406qu g;

    public C1735wu(Handler handler, AbstractC1680vu abstractC1680vu, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, Xt xt, List list) {
        this.a = handler;
        b();
        this.c = strArr;
        this.e = xt;
        this.b = abstractC1680vu;
        this.d = fileDescriptorInfoArr;
        this.f = list;
    }

    public final boolean a(final InterfaceC1351pu interfaceC1351pu, final boolean z, final boolean z2) {
        Runnable runnable;
        Bundle bundle = new Bundle();
        Objects.requireNonNull((Qy) this.b);
        ChildProcessLauncherHelperImpl.d(bundle);
        Xt xt = this.e;
        C1406qu c1406quC = xt.c(Is.a, bundle, new Rt(xt, interfaceC1351pu));
        this.g = c1406quC;
        if (c1406quC != null) {
            if (!z) {
                return true;
            }
            c();
            return true;
        }
        if (!z2) {
            return false;
        }
        Xt xt2 = this.e;
        Runnable runnable2 = new Runnable(this, interfaceC1351pu, z, z2) { // from class: ru
            public final C1735wu s;
            public final InterfaceC1351pu t;
            public final boolean u;
            public final boolean v;

            {
                this.s = this;
                this.t = interfaceC1351pu;
                this.u = z;
                this.v = z2;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a(this.t, this.u, this.v);
            }
        };
        boolean zIsEmpty = xt2.b.isEmpty();
        xt2.b.add(runnable2);
        if (zIsEmpty && (runnable = xt2.a) != null) {
            runnable.run();
        }
        return false;
    }

    public final boolean b() {
        return this.a.getLooper() == Looper.myLooper();
    }

    public final void c() {
        Bundle bundle;
        C1625uu c1625uu = new C1625uu(this);
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("org.chromium.base.process_launcher.extra.command_line", this.c);
        bundle2.putParcelableArray("org.chromium.base.process_launcher.extra.extraFiles", this.d);
        Objects.requireNonNull((Qy) this.b);
        bundle2.putInt("com.google.android.apps.chrome.extra.cpu_count", N.MOiBJ1qS());
        bundle2.putLong("com.google.android.apps.chrome.extra.cpu_features", N.ML0T8q1U());
        if (C1569tt.l.n()) {
            Linker linkerB = Linker.b();
            Objects.requireNonNull(linkerB);
            synchronized (Linker.e) {
                if (linkerB.d == 2) {
                    Linker.LibInfo libInfo = linkerB.a;
                    Objects.requireNonNull(libInfo);
                    bundle = new Bundle();
                    bundle.putParcelable("libinfo", libInfo);
                } else {
                    bundle = null;
                }
            }
            bundle2.putBundle("org.chromium.base.android.linker.shared_relros", bundle);
        }
        C1406qu c1406qu = this.g;
        List list = this.f;
        if (c1406qu.k) {
            Ts.f("ChildProcessConn", "Tried to setup a connection that already disconnected.", new Object[0]);
            c1625uu.a(null);
            return;
        }
        try {
            TraceEvent.x("ChildProcessConnection.setupConnection", null);
            c1406qu.g = c1625uu;
            c1406qu.f = new C1296ou(bundle2, list);
            if (c1406qu.j) {
                c1406qu.b();
            }
        } finally {
            TraceEvent.A("ChildProcessConnection.setupConnection");
        }
    }

    public boolean d(boolean z, boolean z2) {
        try {
            TraceEvent.x("ChildProcessLauncher.start", null);
            C1570tu c1570tu = new C1570tu(this, z, z2);
            C1406qu c1406quA = this.b.a(this.e, c1570tu);
            this.g = c1406quA;
            if (c1406quA != null) {
                c();
                return true;
            }
            if (a(c1570tu, z, z2) || z2) {
                return true;
            }
            return false;
        } finally {
            TraceEvent.A("ChildProcessLauncher.start");
        }
    }
}
