package org.chromium.base.task;

import J.N;
import defpackage.C0574bv;
import defpackage.C1187mv;
import defpackage.C1242nv;
import defpackage.InterfaceC0964iv;
import defpackage.Zu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PostTask {
    public static volatile boolean c;
    public static AtomicReferenceArray e;
    public static final Object a = new Object();
    public static List b = new ArrayList();
    public static final Executor d = new Zu();

    static {
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(5);
        atomicReferenceArray.set(0, new C0574bv());
        e = atomicReferenceArray;
    }

    public static void a(C1242nv c1242nv, Runnable runnable, long j) {
        if (!c || c1242nv.f) {
            ((InterfaceC0964iv) e.get(c1242nv.d)).b(c1242nv, runnable, j);
        } else {
            C1242nv c1242nvD = c1242nv.d();
            N.MTILOhAQ(c1242nvD.a, c1242nvD.b, c1242nvD.c, c1242nvD.d, c1242nvD.e, runnable, j);
        }
    }

    public static void b(C1242nv c1242nv, Runnable runnable) {
        if (((InterfaceC0964iv) e.get(c1242nv.d)).c(c1242nv)) {
            runnable.run();
        } else {
            a(c1242nv, runnable, 0L);
        }
    }

    @Deprecated
    public static Object c(C1242nv c1242nv, Callable callable) {
        FutureTask futureTask = new FutureTask(callable);
        b(c1242nv, futureTask);
        try {
            return futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void onNativeSchedulerReady() {
        List list;
        c = true;
        synchronized (a) {
            list = b;
            b = null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((C1187mv) it.next()).e();
        }
    }

    public static void onNativeSchedulerShutdownForTesting() {
    }
}
