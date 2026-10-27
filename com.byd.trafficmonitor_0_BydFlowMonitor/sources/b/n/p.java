package b.n;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.SavedStateHandleController;
import androidx.savedstate.SavedStateRegistry;
import b.n.r;
import java.lang.reflect.Constructor;
import java.util.Arrays;

/* JADX INFO: compiled from: SavedStateViewModelFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class p extends r.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?>[] f2963a = {Application.class, o.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class<?>[] f2964b = {o.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Application f2965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r.b f2966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Bundle f2967e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Lifecycle f2968f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final SavedStateRegistry f2969g;

    @SuppressLint({"LambdaLast"})
    public p(Application application, b.t.c cVar, Bundle bundle) {
        this.f2969g = cVar.getSavedStateRegistry();
        this.f2968f = cVar.getLifecycle();
        this.f2967e = bundle;
        this.f2965c = application;
        this.f2966d = application != null ? r.a.c(application) : r.d.b();
    }

    public static <T> Constructor<T> d(Class<T> cls, Class<?>[] clsArr) {
        for (Object obj : cls.getConstructors()) {
            Constructor<T> constructor = (Constructor<T>) obj;
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    @Override // b.n.r.c, b.n.r.b
    public <T extends q> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) c(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // b.n.r.e
    public void b(q qVar) {
        SavedStateHandleController.h(qVar, this.f2969g, this.f2968f);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0046 A[Catch: InvocationTargetException -> 0x005a, InstantiationException -> 0x0076, IllegalAccessException -> 0x0093, TryCatch #2 {IllegalAccessException -> 0x0093, InstantiationException -> 0x0076, InvocationTargetException -> 0x005a, blocks: (B:13:0x0030, B:15:0x0034, B:17:0x0054, B:16:0x0046), top: B:28:0x0030 }] */
    @Override // b.n.r.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T extends b.n.q> T c(java.lang.String r6, java.lang.Class<T> r7) {
        /*
            r5 = this;
            java.lang.Class<b.n.a> r0 = b.n.a.class
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L13
            android.app.Application r1 = r5.f2965c
            if (r1 == 0) goto L13
            java.lang.Class<?>[] r1 = b.n.p.f2963a
            java.lang.reflect.Constructor r1 = d(r7, r1)
            goto L19
        L13:
            java.lang.Class<?>[] r1 = b.n.p.f2964b
            java.lang.reflect.Constructor r1 = d(r7, r1)
        L19:
            if (r1 != 0) goto L22
            b.n.r$b r6 = r5.f2966d
            b.n.q r6 = r6.a(r7)
            return r6
        L22:
            androidx.savedstate.SavedStateRegistry r2 = r5.f2969g
            androidx.lifecycle.Lifecycle r3 = r5.f2968f
            android.os.Bundle r4 = r5.f2967e
            androidx.lifecycle.SavedStateHandleController r6 = androidx.lifecycle.SavedStateHandleController.j(r2, r3, r6, r4)
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L46
            android.app.Application r0 = r5.f2965c     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            if (r0 == 0) goto L46
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            r4[r2] = r0     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            b.n.o r0 = r6.k()     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            r4[r3] = r0     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            java.lang.Object r0 = r1.newInstance(r4)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            b.n.q r0 = (b.n.q) r0     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            goto L54
        L46:
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            b.n.o r3 = r6.k()     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            r0[r2] = r3     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            java.lang.Object r0 = r1.newInstance(r0)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            b.n.q r0 = (b.n.q) r0     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
        L54:
            java.lang.String r1 = "androidx.lifecycle.savedstate.vm.tag"
            r0.e(r1, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L5a java.lang.InstantiationException -> L76 java.lang.IllegalAccessException -> L93
            return r0
        L5a:
            r6 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "An exception happened in constructor of "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.Throwable r6 = r6.getCause()
            r0.<init>(r7, r6)
            throw r0
        L76:
            r6 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "A "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = " cannot be instantiated."
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7, r6)
            throw r0
        L93:
            r6 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Failed to access "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.n.p.c(java.lang.String, java.lang.Class):b.n.q");
    }
}
