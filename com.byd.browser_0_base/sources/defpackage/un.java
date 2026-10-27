package defpackage;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import defpackage.qn;
import defpackage.xn;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.a;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: ChildProcessLauncher.java */
/* JADX INFO: loaded from: classes.dex */
public final class un {
    public final Handler a;
    public final c b;
    public final String[] c;
    public final FileDescriptorInfo[] d;
    public final gn e;
    public final List<IBinder> f;
    public volatile qn g;

    /* JADX INFO: compiled from: ChildProcessLauncher.java */
    public class a implements qn.f {
        public a() {
        }
    }

    /* JADX INFO: compiled from: ChildProcessLauncher.java */
    public class b implements qn.c {
        public b() {
        }

        public final void a() {
            un unVar = un.this;
            int i = unVar.g.o;
            c cVar = unVar.b;
            qn qnVar = unVar.g;
            ChildProcessLauncherHelperImpl.a aVar = (ChildProcessLauncherHelperImpl.a) cVar;
            aVar.getClass();
            int i2 = qnVar.o;
            ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = ChildProcessLauncherHelperImpl.this;
            if (i2 > 0) {
                ChildProcessLauncherHelperImpl.s.put(Integer.valueOf(i2), childProcessLauncherHelperImpl);
                xn xnVar = childProcessLauncherHelperImpl.a;
                if (xnVar != null) {
                    int i3 = xnVar.f;
                    ArrayList arrayList = xnVar.g;
                    if (i3 != -1 && arrayList.size() >= i3) {
                        throw new uv("mRankings.size:" + arrayList.size() + " mMaxSize:" + i3);
                    }
                    arrayList.add(new xn.a(qnVar));
                    xnVar.b(arrayList.size() - 1);
                    re reVar = childProcessLauncherHelperImpl.b;
                    if (reVar != null) {
                        reVar.c();
                    }
                }
                if (childProcessLauncherHelperImpl.c) {
                    rn.a().c.add(qnVar);
                }
                if (childProcessLauncherHelperImpl.d && !ApplicationStatus.hasVisibleActivities()) {
                    childProcessLauncherHelperImpl.c();
                }
            }
            long j = childProcessLauncherHelperImpl.h;
            if (j != 0) {
                N.MXR$KaDS(j, qnVar.o);
            }
            childProcessLauncherHelperImpl.h = 0L;
            try {
                for (FileDescriptorInfo fileDescriptorInfo : unVar.d) {
                    fileDescriptorInfo.f.close();
                }
            } catch (IOException e) {
                Log.w("cr_ChildProcLauncher", "Failed to close FD.", e);
            }
        }
    }

    /* JADX INFO: compiled from: ChildProcessLauncher.java */
    public static abstract class c {
        public abstract qn a(gn gnVar, tn tnVar);
    }

    public un(Handler handler, ChildProcessLauncherHelperImpl.a aVar, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, gn gnVar, List list) {
        this.a = handler;
        handler.getLooper();
        Looper.myLooper();
        this.c = strArr;
        this.e = gnVar;
        this.b = aVar;
        this.d = fileDescriptorInfoArr;
        this.f = list;
    }

    public final boolean a(final qn.e eVar, final boolean z, final boolean z2) {
        Runnable runnable;
        Bundle bundle = new Bundle();
        ((ChildProcessLauncherHelperImpl.a) this.b).getClass();
        o90 o90Var = ChildProcessLauncherHelperImpl.m;
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        a.C0070a c0070a = org.chromium.base.library_loader.a.l.g;
        c0070a.b();
        c0070a.d(bundle);
        gn gnVar = this.e;
        Context context = xz.a;
        gnVar.getClass();
        this.g = gnVar.b(context, bundle, new en(gnVar, eVar));
        if (this.g != null) {
            if (!z) {
                return true;
            }
            b();
            return true;
        }
        if (!z2) {
            return false;
        }
        gn gnVar2 = this.e;
        Runnable runnable2 = new Runnable() { // from class: sn
            @Override // java.lang.Runnable
            public final void run() {
                this.e.a(eVar, z, z2);
            }
        };
        ArrayDeque arrayDeque = gnVar2.b;
        boolean zIsEmpty = arrayDeque.isEmpty();
        arrayDeque.add(runnable2);
        if (zIsEmpty && (runnable = gnVar2.a) != null) {
            runnable.run();
        }
        return false;
    }

    public final void b() {
        a aVar = new a();
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putStringArray("org.chromium.base.process_launcher.extra.command_line", this.c);
        bundle.putParcelableArray("org.chromium.base.process_launcher.extra.extraFiles", this.d);
        ((ChildProcessLauncherHelperImpl.a) this.b).getClass();
        bundle.putInt("com.google.android.apps.chrome.extra.cpu_count", N.MOiBJ1qS());
        bundle.putLong("com.google.android.apps.chrome.extra.cpu_features", N.ML0T8q1U());
        if (ChildProcessLauncherHelperImpl.z != null) {
            bundle.putAll(ChildProcessLauncherHelperImpl.z);
        } else {
            org.chromium.base.library_loader.a.l.g.e(bundle);
        }
        qn qnVar = this.g;
        List<IBinder> list = this.f;
        if (qnVar.n) {
            Log.w("cr_ChildProcessConn", "Tried to setup a connection that already disconnected.");
            bVar.a();
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("ChildProcessConnection.setupConnection", null);
        try {
            qnVar.i = bVar;
            qnVar.j = aVar;
            qnVar.h = new qn.d(list, bundle);
            if (qnVar.m) {
                qnVar.e();
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final void c(boolean z, boolean z2) {
        try {
            TraceEvent.q("ChildProcessLauncher.start", null);
            tn tnVar = new tn(this, z, z2);
            this.g = this.b.a(this.e, tnVar);
            if (this.g != null) {
                b();
            } else if (a(tnVar, z, z2) || z2) {
            }
        } finally {
            TraceEvent.C("ChildProcessLauncher.start");
        }
    }
}
