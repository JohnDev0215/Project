package org.chromium.base.task;

import J.N;
import defpackage.c73;
import defpackage.d73;
import defpackage.d80;
import defpackage.oi2;
import defpackage.ts;
import defpackage.y63;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class PostTask {
    public static volatile boolean c;
    public static final AtomicReferenceArray<y63> e;
    public static final Object a = new Object();
    public static ArrayList b = new ArrayList();
    public static final ts d = new ts();

    static {
        AtomicReferenceArray<y63> atomicReferenceArray = new AtomicReferenceArray<>(5);
        atomicReferenceArray.set(0, new d80());
        e = atomicReferenceArray;
    }

    public static oi2 a(d73 d73Var) {
        return e.get(d73Var.d).b(d73Var);
    }

    public static void b(d73 d73Var, Runnable runnable, long j) {
        if (!c) {
            e.get(d73Var.d).c(d73Var, runnable, j);
            return;
        }
        if (!d73Var.c) {
            if (!(d73Var.d != 0)) {
                d73 d73Var2 = new d73(d73Var);
                d73Var2.c = true;
                d73Var = d73Var2;
            }
        }
        N.MTILOhAQ(d73Var.a, d73Var.b, d73Var.c, d73Var.d, d73Var.e, runnable, j, runnable.getClass().getName());
    }

    public static void c(d73 d73Var, Runnable runnable) {
        b(d73Var, runnable, 0L);
    }

    public static void d(d73 d73Var, Runnable runnable) {
        if (e.get(d73Var.d).d(d73Var)) {
            runnable.run();
        } else {
            b(d73Var, runnable, 0L);
        }
    }

    @CalledByNative
    public static void onNativeSchedulerReady() {
        ArrayList arrayList;
        if (c) {
            return;
        }
        c = true;
        synchronized (a) {
            arrayList = b;
            b = null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((c73) it.next()).e();
        }
    }
}
