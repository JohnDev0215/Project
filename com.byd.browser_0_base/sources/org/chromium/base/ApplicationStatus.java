package org.chromium.base;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Window;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationStatus {
    public static final Map<Activity, b> a = Collections.synchronizedMap(new HashMap());

    @SuppressLint({"SupportAnnotationUsage"})
    public static int b = 0;

    @SuppressLint({"StaticFieldLeak"})
    public static Activity c;
    public static d d;
    public static org.chromium.base.b<c> e;
    public static org.chromium.base.b<d> f;
    public static org.chromium.base.b<g> g;
    public static org.chromium.base.b<e> h;

    public class a implements Runnable {

        /* JADX INFO: renamed from: org.chromium.base.ApplicationStatus$a$a, reason: collision with other inner class name */
        public class C0069a implements d {
            @Override // org.chromium.base.ApplicationStatus.d
            public final void v(int i) {
                N.MiAkQ_SU(i);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (ApplicationStatus.d != null) {
                return;
            }
            C0069a c0069a = new C0069a();
            ApplicationStatus.d = c0069a;
            ApplicationStatus.e(c0069a);
        }
    }

    public static class b {
        public int a = 6;
        public final org.chromium.base.b<c> b = new org.chromium.base.b<>();
    }

    public interface c {
        void d(Activity activity, int i);
    }

    public interface d {
        void v(int i);
    }

    public interface e {
        void a(int i, boolean z);
    }

    public static class f implements InvocationHandler {
        public final Window.Callback a;
        public final Activity b;

        public f(Activity activity, Window.Callback callback) {
            this.a = callback;
            this.b = activity;
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            boolean zEquals = method.getName().equals("onWindowFocusChanged");
            Window.Callback callback = this.a;
            if (zEquals && objArr.length == 1) {
                Object obj2 = objArr[0];
                if (obj2 instanceof Boolean) {
                    boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                    callback.onWindowFocusChanged(zBooleanValue);
                    org.chromium.base.b<g> bVar = ApplicationStatus.g;
                    if (bVar == null) {
                        return null;
                    }
                    Iterator<g> it = bVar.iterator();
                    while (true) {
                        b.a aVar = (b.a) it;
                        if (!aVar.hasNext()) {
                            return null;
                        }
                        ((g) aVar.next()).e(this.b, zBooleanValue);
                    }
                }
            }
            try {
                return method.invoke(callback, objArr);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof AbstractMethodError) {
                    throw e.getCause();
                }
                throw e;
            }
        }
    }

    public interface g {
        void e(Activity activity, boolean z);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0035 A[Catch: all -> 0x00f3, TryCatch #0 {, blocks: (B:12:0x0022, B:13:0x002a, B:15:0x0035, B:17:0x003c, B:18:0x003f, B:19:0x0049, B:21:0x0050, B:35:0x0071, B:36:0x0073), top: B:69:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0050 A[Catch: all -> 0x00f3, TryCatch #0 {, blocks: (B:12:0x0022, B:13:0x002a, B:15:0x0035, B:17:0x003c, B:18:0x003f, B:19:0x0049, B:21:0x0050, B:35:0x0071, B:36:0x0073), top: B:69:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0069 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.app.Activity r14, int r15) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ApplicationStatus.a(android.app.Activity, int):void");
    }

    public static ArrayList b() {
        ArrayList arrayList;
        Map<Activity, b> map = a;
        synchronized (map) {
            arrayList = new ArrayList(map.keySet());
        }
        return arrayList;
    }

    public static int c(Activity activity) {
        b bVar;
        if (activity == null || (bVar = a.get(activity)) == null) {
            return 6;
        }
        return bVar.a;
    }

    public static boolean d(int i) {
        int i2;
        for (Map.Entry<Activity, b> entry : a.entrySet()) {
            if (entry.getKey().getTaskId() == i && ((i2 = entry.getValue().a) == 3 || i2 == 4)) {
                return true;
            }
        }
        return false;
    }

    public static void e(d dVar) {
        if (f == null) {
            f = new org.chromium.base.b<>();
        }
        f.a(dVar);
    }

    @SuppressLint({"NewApi"})
    public static void f(c cVar, Activity activity) {
        a.get(activity).b.a(cVar);
    }

    public static void g(c cVar) {
        if (e == null) {
            e = new org.chromium.base.b<>();
        }
        e.a(cVar);
    }

    @CalledByNative
    public static int getStateForApplication() {
        int i;
        synchronized (a) {
            i = b;
        }
        return i;
    }

    public static void h(g gVar) {
        if (g == null) {
            g = new org.chromium.base.b<>();
        }
        g.a(gVar);
    }

    @CalledByNative
    public static boolean hasVisibleActivities() {
        int stateForApplication = getStateForApplication();
        return stateForApplication == 1 || stateForApplication == 2;
    }

    public static void i(c cVar) {
        org.chromium.base.b<c> bVar = e;
        if (bVar != null) {
            bVar.c(cVar);
        }
        Map<Activity, b> map = a;
        synchronized (map) {
            Iterator<b> it = map.values().iterator();
            while (it.hasNext()) {
                it.next().b.c(cVar);
            }
        }
    }

    @CalledByNative
    public static void registerThreadSafeNativeApplicationStateListener() {
        ThreadUtils.f(new a());
    }
}
