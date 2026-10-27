package org.chromium.base;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.view.ViewGroup;
import defpackage.d73;
import defpackage.gq2;
import defpackage.iv;
import defpackage.qe3;
import defpackage.wq0;
import defpackage.xz;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes.dex */
public class TraceEvent implements AutoCloseable {
    public static volatile boolean f;
    public static final AtomicBoolean g = new AtomicBoolean();
    public static final AtomicBoolean h = new AtomicBoolean();
    public static boolean i;
    public static a j;
    public final String e;

    public static class a implements MessageQueue.IdleHandler {
        public final Class<?> a;
        public final Method b;
        public final Method c;
        public final Method d;
        public final Method e;
        public final Method f;
        public final Class<?> g;
        public final Method h;
        public final AtomicBoolean i = new AtomicBoolean();
        public final AtomicBoolean j = new AtomicBoolean();
        public final AtomicBoolean k = new AtomicBoolean();
        public final long l;
        public boolean m;
        public boolean n;

        public a(long j) {
            try {
                Class<?> cls = Class.forName("android.os.Trace");
                this.a = cls;
                Class<?> cls2 = Long.TYPE;
                this.b = cls.getMethod("isTagEnabled", cls2);
                this.c = cls.getMethod("traceBegin", cls2, String.class);
                this.d = cls.getMethod("traceEnd", cls2);
                Class<?> cls3 = Integer.TYPE;
                this.e = cls.getMethod("asyncTraceBegin", cls2, String.class, cls3);
                this.f = cls.getMethod("asyncTraceEnd", cls2, String.class, cls3);
                Class<?> cls4 = Class.forName("android.os.SystemProperties");
                this.g = cls4;
                this.h = cls4.getMethod("get", String.class);
            } catch (Exception e) {
                Log.w("cr_ATrace", "Reflection error", e);
                this.b = null;
            }
            this.l = j;
            b();
        }

        public final void a() {
            this.i.set(true);
            this.k.set(false);
            if (this.j.get()) {
                ThreadUtils.e(new wq0(7, this));
            }
        }

        public final void b() {
            boolean zBooleanValue;
            String str;
            Integer numDecode;
            boolean z;
            Context context;
            String str2;
            Class<?> cls = this.g;
            Method method = this.h;
            AtomicBoolean atomicBoolean = this.k;
            boolean z2 = atomicBoolean.get();
            try {
                zBooleanValue = ((Boolean) this.b.invoke(this.a, Long.valueOf(this.l))).booleanValue();
            } catch (Exception unused) {
                zBooleanValue = false;
            }
            if (z2 == zBooleanValue) {
                return;
            }
            atomicBoolean.set(zBooleanValue);
            if (!zBooleanValue) {
                EarlyTraceEvent.b();
                N.MOgCa3d$();
                this.m = false;
                ThreadUtils.d().setMessageLogging(null);
                return;
            }
            try {
                str = (String) method.invoke(cls, "debug.atrace.app_number");
            } catch (Exception unused2) {
                str = null;
            }
            if (str == null) {
                numDecode = null;
            } else {
                try {
                    numDecode = Integer.decode(str);
                } catch (NumberFormatException unused3) {
                    numDecode = null;
                }
            }
            String strF = "";
            if (numDecode == null || numDecode.intValue() <= 0 || (context = xz.a) == null) {
                z = true;
            } else {
                String packageName = context.getPackageName();
                z = true;
                for (int i = 0; i < numDecode.intValue(); i++) {
                    try {
                        str2 = (String) method.invoke(cls, gq2.e("debug.atrace.app_", i));
                    } catch (Exception unused4) {
                        str2 = null;
                    }
                    if (str2 != null && str2.startsWith(packageName)) {
                        String strSubstring = str2.substring(packageName.length());
                        if (strSubstring.startsWith("/")) {
                            for (String str3 : strSubstring.substring(1).split(":")) {
                                if (str3.equals("-atrace")) {
                                    z = false;
                                } else {
                                    if (strF.length() > 0) {
                                        strF = strF.concat(",");
                                    }
                                    strF = gq2.f(strF, str3);
                                }
                            }
                        }
                    }
                }
            }
            this.m = false;
            if (this.i.get()) {
                if (z) {
                    N.MRN$Vid3(strF);
                } else {
                    N.MlFM5bdC(strF);
                }
            } else if (z) {
                this.m = true;
            } else {
                EarlyTraceEvent.d();
            }
            if (z) {
                return;
            }
            ThreadUtils.d().setMessageLogging(e.a);
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            b();
            return true;
        }
    }

    public static class b {
        public final String a;
        public final ArrayList<g> b = new ArrayList<>(125);

        public b(String str) {
            this.a = str;
        }
    }

    public static class c implements Printer {
        public String a;

        public void a(String str) {
            String strI;
            boolean zE = EarlyTraceEvent.e();
            if (TraceEvent.f || zE) {
                if (TraceEvent.i) {
                    strI = "Looper.dispatch: EVENT_NAME_FILTERED";
                } else {
                    StringBuilder sb = new StringBuilder("Looper.dispatch: ");
                    int iIndexOf = str.indexOf(40, 18);
                    int iIndexOf2 = iIndexOf == -1 ? -1 : str.indexOf(41, iIndexOf);
                    sb.append(iIndexOf2 != -1 ? str.substring(iIndexOf + 1, iIndexOf2) : "");
                    sb.append("(");
                    int iIndexOf3 = str.indexOf(125, 18);
                    int iIndexOf4 = iIndexOf3 == -1 ? -1 : str.indexOf(58, iIndexOf3);
                    if (iIndexOf4 == -1) {
                        iIndexOf4 = str.length();
                    }
                    strI = gq2.i(sb, iIndexOf3 != -1 ? str.substring(iIndexOf3 + 2, iIndexOf4) : "", ")");
                }
                this.a = strI;
                if (TraceEvent.f) {
                    N.M_y76mct(this.a);
                } else {
                    EarlyTraceEvent.a(this.a, true);
                }
            }
        }

        public void b(String str) {
            boolean zE = EarlyTraceEvent.e();
            if ((TraceEvent.f || zE) && this.a != null) {
                if (TraceEvent.f) {
                    N.MLJecZJ9(this.a);
                } else {
                    EarlyTraceEvent.f(this.a, true);
                }
            }
            this.a = null;
        }

        @Override // android.util.Printer
        public final void println(String str) {
            if (str.startsWith(">")) {
                a(str);
            } else {
                b(str);
            }
        }
    }

    public static final class d extends c implements MessageQueue.IdleHandler {
        public long b;
        public long c;
        public int d;
        public int e;
        public int f;
        public boolean g;

        @Override // org.chromium.base.TraceEvent.c
        public final void a(String str) {
            if (this.f == 0) {
                TraceEvent.C("Looper.queueIdle");
            }
            this.c = SystemClock.elapsedRealtime();
            c();
            super.a(str);
        }

        @Override // org.chromium.base.TraceEvent.c
        public final void b(String str) {
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.c;
            if (jElapsedRealtime > 16) {
                String str2 = "observed a task that took " + jElapsedRealtime + "ms: " + str;
                TraceEvent.v0("TraceEvent.LooperMonitor:IdleStats", str2);
                Log.println(5, "TraceEvt_LooperMonitor", str2);
            }
            super.b(str);
            c();
            this.d++;
            this.f++;
        }

        public final void c() {
            if (TraceEvent.f && !this.g) {
                this.b = SystemClock.elapsedRealtime();
                Looper.myQueue().addIdleHandler(this);
                this.g = true;
            } else {
                if (!this.g || TraceEvent.f) {
                    return;
                }
                Looper.myQueue().removeIdleHandler(this);
                this.g = false;
            }
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (this.b == 0) {
                this.b = jElapsedRealtime;
            }
            long j = jElapsedRealtime - this.b;
            this.e++;
            TraceEvent.q("Looper.queueIdle", this.f + " tasks since last idle.");
            if (j > 48) {
                String str = this.d + " tasks and " + this.e + " idles processed so far, " + this.f + " tasks bursted and " + j + "ms elapsed since last idle";
                TraceEvent.v0("TraceEvent.LooperMonitor:IdleStats", str);
                Log.println(3, "TraceEvt_LooperMonitor", str);
            }
            this.b = jElapsedRealtime;
            this.f = 0;
            return true;
        }
    }

    public static final class e {
        public static final c a;

        static {
            a = iv.e().g("enable-idle-tracing") ? new d() : new c();
        }
    }

    public static final class f implements MessageQueue.IdleHandler {
        public static boolean b;
        public static f c;
        public long a;

        public static void a(b bVar, int i, View view) {
            Object obj = ThreadUtils.a;
            int id = view.getId();
            bVar.b.add(new g(id, i, view.isShown(), view.isDirty(), view.getClass().getSimpleName(), view.getResources()));
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    a(bVar, id, viewGroup.getChildAt(i2));
                }
            }
        }

        public static void b() {
            int i = 0;
            if (!ThreadUtils.g()) {
                ThreadUtils.e(new qe3(i));
                return;
            }
            if (N.MnfJQqTB()) {
                if (c == null) {
                    c = new f();
                }
                ThreadUtils.a();
                if (b) {
                    return;
                }
                Looper.myQueue().addIdleHandler(c);
                b = true;
                return;
            }
            if (c != null) {
                ThreadUtils.a();
                if (b) {
                    Looper.myQueue().removeIdleHandler(c);
                    b = false;
                }
            }
        }

        @Override // android.os.MessageQueue.IdleHandler
        public final boolean queueIdle() {
            boolean z;
            final ArrayList arrayList;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long j = this.a;
            if (j == 0 || jElapsedRealtime - j > 1000) {
                this.a = jElapsedRealtime;
                if (TraceEvent.f && N.MnfJQqTB()) {
                    TraceEvent.q("instantAndroidViewHierarchy", null);
                    synchronized (ApplicationStatus.a) {
                        z = ApplicationStatus.b != 0;
                    }
                    if (z) {
                        arrayList = new ArrayList(2);
                        for (Activity activity : ApplicationStatus.b()) {
                            arrayList.add(new b(activity.getClass().getName()));
                            a((b) arrayList.get(arrayList.size() - 1), 0, activity.getWindow().getDecorView().getRootView());
                        }
                    } else {
                        arrayList = new ArrayList();
                    }
                    if (arrayList.isEmpty()) {
                        TraceEvent.C("instantAndroidViewHierarchy");
                    } else {
                        final long jHashCode = arrayList.hashCode();
                        PostTask.c(d73.f, new Runnable() { // from class: pe3
                            @Override // java.lang.Runnable
                            public final void run() {
                                boolean z2 = TraceEvent.f;
                                N.Ml5G_GLY(jHashCode, arrayList);
                            }
                        });
                        TraceEvent.t(jHashCode, "instantAndroidViewHierarchy");
                    }
                }
            }
            return true;
        }
    }

    public static class g {
        public final int a;
        public final int b;
        public final boolean c;
        public final boolean d;
        public final String e;
        public final Resources f;

        public g(int i, int i2, boolean z, boolean z2, String str, Resources resources) {
            this.a = i;
            this.b = i2;
            this.c = z;
            this.d = z2;
            this.e = str;
            this.f = resources;
        }
    }

    public TraceEvent(String str, String str2) {
        this.e = str;
        q(str, str2);
    }

    public static void C(String str) {
        t(0L, str);
    }

    public static void F(long j2, String str) {
        if (EarlyTraceEvent.e()) {
            EarlyTraceEvent.a aVar = new EarlyTraceEvent.a(j2, str, false);
            synchronized (EarlyTraceEvent.d) {
                if (EarlyTraceEvent.e()) {
                    EarlyTraceEvent.f.add(aVar);
                }
            }
        }
        if (f) {
            N.MffNhCLU(str, j2);
            return;
        }
        a aVar2 = j;
        if (aVar2 != null) {
            int i2 = (int) j2;
            if (aVar2.m) {
                try {
                    aVar2.f.invoke(aVar2.a, Long.valueOf(aVar2.l), str, Integer.valueOf(i2));
                } catch (Exception unused) {
                }
            }
        }
    }

    public static TraceEvent H0(String str, String str2) {
        if (EarlyTraceEvent.e() || f) {
            return new TraceEvent(str, str2);
        }
        return null;
    }

    public static void I0(long j2, String str) {
        if (EarlyTraceEvent.e()) {
            EarlyTraceEvent.a aVar = new EarlyTraceEvent.a(j2, str, true);
            synchronized (EarlyTraceEvent.d) {
                if (EarlyTraceEvent.e()) {
                    EarlyTraceEvent.f.add(aVar);
                }
            }
        }
        if (f) {
            N.MHopMqLX(str, j2);
            return;
        }
        a aVar2 = j;
        if (aVar2 != null) {
            int i2 = (int) j2;
            if (aVar2.m) {
                try {
                    aVar2.e.invoke(aVar2.a, Long.valueOf(aVar2.l), str, Integer.valueOf(i2));
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void R(String str) {
        if (f) {
            N.ML40H8ed(str, null);
        }
    }

    @CalledByNative
    public static void dumpViewHierarchy(long j2, Object obj) {
        boolean z;
        String resourceName;
        synchronized (ApplicationStatus.a) {
            z = ApplicationStatus.b != 0;
        }
        if (z) {
            for (b bVar : (ArrayList) obj) {
                long jMwX2YEhL = N.MwX2YEhL(bVar.a, j2);
                for (g gVar : bVar.b) {
                    try {
                        Resources resources = gVar.f;
                        if (resources != null) {
                            int i2 = gVar.a;
                            resourceName = (i2 == 0 || i2 == -1) ? "__no_id__" : resources.getResourceName(i2);
                        } else {
                            resourceName = "__no_resources__";
                        }
                    } catch (Resources.NotFoundException unused) {
                        resourceName = "__name_not_found__";
                    }
                    N.MmnP6i1r(gVar.a, gVar.b, gVar.c, gVar.d, gVar.e, resourceName, jMwX2YEhL);
                }
            }
        }
    }

    public static void q(String str, String str2) {
        EarlyTraceEvent.a(str, false);
        if (f) {
            N.M9XfPu17(str, str2);
            return;
        }
        a aVar = j;
        if (aVar == null || !aVar.m) {
            return;
        }
        try {
            aVar.c.invoke(aVar.a, Long.valueOf(aVar.l), str);
        } catch (Exception unused) {
        }
    }

    @CalledByNative
    public static void setEnabled(boolean z) {
        if (z) {
            EarlyTraceEvent.b();
        }
        if (f != z) {
            f = z;
            a aVar = j;
            if (aVar == null || !aVar.k.get()) {
                ThreadUtils.d().setMessageLogging(z ? e.a : null);
            }
        }
        if (h.get()) {
            f.b();
        }
    }

    @CalledByNative
    public static void setEventNameFilteringEnabled(boolean z) {
        i = z;
    }

    public static void t(long j2, String str) {
        EarlyTraceEvent.f(str, false);
        if (f) {
            N.Mw73xTww(str, null, j2);
            return;
        }
        a aVar = j;
        if (aVar == null || !aVar.m) {
            return;
        }
        try {
            aVar.d.invoke(aVar.a, Long.valueOf(aVar.l));
        } catch (Exception unused) {
        }
    }

    public static void v0(String str, String str2) {
        if (f) {
            N.ML40H8ed(str, str2);
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        C(this.e);
    }
}
