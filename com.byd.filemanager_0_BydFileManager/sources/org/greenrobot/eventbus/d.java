package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.f;
import org.greenrobot.eventbus.g;

/* JADX INFO: compiled from: EventBusBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ExecutorService f4687a = Executors.newCachedThreadPool();
    boolean f;
    boolean h;
    boolean i;
    List<org.greenrobot.eventbus.r.b> k;
    f l;
    g m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f4688b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f4689c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4690d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4691e = true;
    boolean g = true;
    ExecutorService j = f4687a;

    d() {
    }

    static Object a() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    f b() {
        f fVar = this.l;
        return fVar != null ? fVar : f.a.a();
    }

    g c() {
        Object objA;
        g gVar = this.m;
        if (gVar != null) {
            return gVar;
        }
        if (!org.greenrobot.eventbus.q.a.c() || (objA = a()) == null) {
            return null;
        }
        return new g.a((Looper) objA);
    }
}
