package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.g;
import org.greenrobot.eventbus.h;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ExecutorService f3300a = Executors.newCachedThreadPool();
    boolean f;
    boolean h;
    boolean i;
    List<org.greenrobot.eventbus.s.b> k;
    g l;
    h m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f3301b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    boolean f3302c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f3303d = true;
    boolean e = true;
    boolean g = true;
    ExecutorService j = f3300a;

    d() {
    }

    static Object a() {
        try {
            return Looper.getMainLooper();
        } catch (RuntimeException unused) {
            return null;
        }
    }

    g b() {
        g gVar = this.l;
        return gVar != null ? gVar : g.a.a();
    }

    h c() {
        Object objA;
        h hVar = this.m;
        if (hVar != null) {
            return hVar;
        }
        if (!org.greenrobot.eventbus.r.a.c() || (objA = a()) == null) {
            return null;
        }
        return new h.a((Looper) objA);
    }
}
