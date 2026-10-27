package io.sentry.android.replay;

import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Closeable {
    public final F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.protocol.t f16404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u f16405n;
    public final AtomicBoolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f16406p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public B8.k f16407q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final m7.n f16408r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f16409s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LinkedHashMap f16410t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final m7.n f16411u;

    public i(F1 f12, io.sentry.protocol.t tVar, u uVar) {
        A7.m.f(f12, "options");
        A7.m.f(tVar, "replayId");
        A7.m.f(uVar, "recorderConfig");
        this.l = f12;
        this.f16404m = tVar;
        this.f16405n = uVar;
        this.o = new AtomicBoolean(false);
        this.f16406p = new Object();
        this.f16408r = h6.r.o(new g(this, 1));
        this.f16409s = new ArrayList();
        this.f16410t = new LinkedHashMap();
        this.f16411u = h6.r.o(new g(this, 0));
    }

    public final void b(File file) {
        F1 f12 = this.l;
        try {
            if (file.delete()) {
                return;
            }
            f12.getLogger().k(EnumC1495p1.ERROR, "Failed to delete replay frame: %s", file.getAbsolutePath());
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Failed to delete replay frame: %s", file.getAbsolutePath());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f16406p) {
            try {
                B8.k kVar = this.f16407q;
                if (kVar != null) {
                    kVar.d();
                }
                this.f16407q = null;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.o.set(true);
    }

    public final File g() {
        return (File) this.f16408r.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0081 A[Catch: all -> 0x0077, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0010, B:9:0x0019, B:11:0x0023, B:19:0x0073, B:25:0x007b, B:26:0x007e, B:28:0x0081, B:30:0x008c, B:32:0x0096, B:34:0x00c8, B:39:0x00cf, B:40:0x00d2, B:29:0x0087, B:12:0x0036, B:13:0x0048, B:15:0x004e, B:23:0x0079, B:33:0x00c5, B:37:0x00cd), top: B:47:0x0001, inners: #0, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0087 A[Catch: all -> 0x0077, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0010, B:9:0x0019, B:11:0x0023, B:19:0x0073, B:25:0x007b, B:26:0x007e, B:28:0x0081, B:30:0x008c, B:32:0x0096, B:34:0x00c8, B:39:0x00cf, B:40:0x00d2, B:29:0x0087, B:12:0x0036, B:13:0x0048, B:15:0x004e, B:23:0x0079, B:33:0x00c5, B:37:0x00cd), top: B:47:0x0001, inners: #0, #2, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0096 A[Catch: all -> 0x0077, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:7:0x0010, B:9:0x0019, B:11:0x0023, B:19:0x0073, B:25:0x007b, B:26:0x007e, B:28:0x0081, B:30:0x008c, B:32:0x0096, B:34:0x00c8, B:39:0x00cf, B:40:0x00d2, B:29:0x0087, B:12:0x0036, B:13:0x0048, B:15:0x004e, B:23:0x0079, B:33:0x00c5, B:37:0x00cd), top: B:47:0x0001, inners: #0, #2, #3, #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void i(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.i.i(java.lang.String, java.lang.String):void");
    }
}
