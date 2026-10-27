package com.bumptech.glide.load.engine.y;

import android.util.Log;
import com.bumptech.glide.h.a;
import com.bumptech.glide.load.engine.y.a;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: DiskLruCacheWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f2884b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f2885c;
    private com.bumptech.glide.h.a e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f2886d = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f2883a = new j();

    @Deprecated
    protected e(File file, long j) {
        this.f2884b = file;
        this.f2885c = j;
    }

    public static a c(File file, long j) {
        return new e(file, j);
    }

    private synchronized com.bumptech.glide.h.a d() throws IOException {
        if (this.e == null) {
            this.e = com.bumptech.glide.h.a.s(this.f2884b, 1, 1, this.f2885c);
        }
        return this.e;
    }

    @Override // com.bumptech.glide.load.engine.y.a
    public void a(com.bumptech.glide.load.c cVar, a.b bVar) {
        com.bumptech.glide.h.a aVarD;
        String strB = this.f2883a.b(cVar);
        this.f2886d.a(strB);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strB + " for for Key: " + cVar);
            }
            try {
                aVarD = d();
            } catch (IOException e) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e);
                }
            }
            if (aVarD.q(strB) != null) {
                return;
            }
            a.c cVarN = aVarD.n(strB);
            if (cVarN == null) {
                throw new IllegalStateException("Had two simultaneous puts for: " + strB);
            }
            try {
                if (bVar.a(cVarN.f(0))) {
                    cVarN.e();
                }
                cVarN.b();
            } catch (Throwable th) {
                cVarN.b();
                throw th;
            }
        } finally {
            this.f2886d.b(strB);
        }
    }

    @Override // com.bumptech.glide.load.engine.y.a
    public File b(com.bumptech.glide.load.c cVar) {
        String strB = this.f2883a.b(cVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strB + " for for Key: " + cVar);
        }
        try {
            a.e eVarQ = d().q(strB);
            if (eVarQ != null) {
                return eVarQ.a(0);
            }
            return null;
        } catch (IOException e) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            return null;
        }
    }
}
