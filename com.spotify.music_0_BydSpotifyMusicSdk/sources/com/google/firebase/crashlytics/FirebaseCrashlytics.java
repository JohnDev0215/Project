package com.google.firebase.crashlytics;

import com.google.firebase.crashlytics.internal.common.a;
import p.a30;
import p.aj2;
import p.b33;
import p.ci1;
import p.d30;
import p.e30;
import p.gh2;
import p.i30;
import p.ru0;
import p.y40;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseCrashlytics {
    public final i30 a;

    public FirebaseCrashlytics(i30 i30Var) {
        this.a = i30Var;
    }

    public static FirebaseCrashlytics getInstance() {
        ru0 ru0VarB = ru0.b();
        ru0VarB.a();
        FirebaseCrashlytics firebaseCrashlytics = (FirebaseCrashlytics) ru0VarB.d.a(FirebaseCrashlytics.class);
        if (firebaseCrashlytics != null) {
            return firebaseCrashlytics;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    public aj2 checkForUnsentReports() {
        a aVar = this.a.h;
        return !aVar.q.compareAndSet(false, true) ? ci1.t(Boolean.FALSE) : aVar.n.a;
    }

    public void deleteUnsentReports() {
        a aVar = this.a.h;
        aVar.o.b(Boolean.FALSE);
        b33 b33Var = aVar.f38p.a;
    }

    public boolean didCrashOnPreviousExecution() {
        return this.a.g;
    }

    public void log(String str) {
        i30 i30Var = this.a;
        i30Var.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - i30Var.d;
        a aVar = i30Var.h;
        aVar.getClass();
        aVar.e.x(new d30(aVar, jCurrentTimeMillis, str));
    }

    public void recordException(Throwable th) {
        if (th == null) {
            return;
        }
        a aVar = this.a.h;
        Thread threadCurrentThread = Thread.currentThread();
        aVar.getClass();
        e30 e30Var = new e30(aVar, System.currentTimeMillis(), th, threadCurrentThread);
        gh2 gh2Var = aVar.e;
        gh2Var.getClass();
        gh2Var.x(new a30(gh2Var, 0, e30Var));
    }

    public void sendUnsentReports() {
        a aVar = this.a.h;
        aVar.o.b(Boolean.TRUE);
        b33 b33Var = aVar.f38p.a;
    }

    public void setCrashlyticsCollectionEnabled(boolean z) {
        this.a.b(Boolean.valueOf(z));
    }

    public void setCustomKey(String str, boolean z) {
        this.a.c(str, Boolean.toString(z));
    }

    public void setCustomKeys(y40 y40Var) {
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        if (r5.equals(r2) != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setUserId(java.lang.String r5) {
        /*
            r4 = this;
            p.i30 r0 = r4.a
            com.google.firebase.crashlytics.internal.common.a r0 = r0.h
            p.gu0 r0 = r0.d
            r0.getClass()
            r1 = 1024(0x400, float:1.435E-42)
            java.lang.String r5 = p.o81.a(r1, r5)
            java.lang.Object r1 = r0.f
            java.util.concurrent.atomic.AtomicMarkableReference r1 = (java.util.concurrent.atomic.AtomicMarkableReference) r1
            monitor-enter(r1)
            java.lang.Object r2 = r0.f     // Catch: java.lang.Throwable -> L2b
            java.util.concurrent.atomic.AtomicMarkableReference r2 = (java.util.concurrent.atomic.AtomicMarkableReference) r2     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r2 = r2.getReference()     // Catch: java.lang.Throwable -> L2b
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L2b
            if (r5 != 0) goto L23
            if (r2 != 0) goto L2d
            goto L29
        L23:
            boolean r2 = r5.equals(r2)     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L2d
        L29:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2b
            goto L42
        L2b:
            r5 = move-exception
            goto L43
        L2d:
            java.lang.Object r2 = r0.f     // Catch: java.lang.Throwable -> L2b
            java.util.concurrent.atomic.AtomicMarkableReference r2 = (java.util.concurrent.atomic.AtomicMarkableReference) r2     // Catch: java.lang.Throwable -> L2b
            r3 = 1
            r2.set(r5, r3)     // Catch: java.lang.Throwable -> L2b
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r5 = r0.b
            p.gh2 r5 = (p.gh2) r5
            p.c31 r1 = new p.c31
            r1.<init>(r3, r0)
            r5.x(r1)
        L42:
            return
        L43:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L2b
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.FirebaseCrashlytics.setUserId(java.lang.String):void");
    }

    public void setCrashlyticsCollectionEnabled(Boolean bool) {
        this.a.b(bool);
    }

    public void setCustomKey(String str, double d) {
        this.a.c(str, Double.toString(d));
    }

    public void setCustomKey(String str, float f) {
        this.a.c(str, Float.toString(f));
    }

    public void setCustomKey(String str, int i) {
        this.a.c(str, Integer.toString(i));
    }

    public void setCustomKey(String str, long j) {
        this.a.c(str, Long.toString(j));
    }

    public void setCustomKey(String str, String str2) {
        this.a.c(str, str2);
    }
}
