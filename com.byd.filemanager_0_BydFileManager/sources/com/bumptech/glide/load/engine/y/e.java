package com.bumptech.glide.load.engine.y;

import android.util.Log;
import com.bumptech.glide.j.a;
import com.bumptech.glide.load.engine.y.a;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: DiskLruCacheWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f3134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f3135c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.bumptech.glide.j.a f3137e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f3136d = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f3133a = new j();

    @Deprecated
    protected e(File file, long j) {
        this.f3134b = file;
        this.f3135c = j;
    }

    public static a c(File file, long j) {
        return new e(file, j);
    }

    private synchronized com.bumptech.glide.j.a d() throws IOException {
        if (this.f3137e == null) {
            this.f3137e = com.bumptech.glide.j.a.B(this.f3134b, 1, 1, this.f3135c);
        }
        return this.f3137e;
    }

    @Override // com.bumptech.glide.load.engine.y.a
    public void a(com.bumptech.glide.load.c cVar, a.b bVar) {
        com.bumptech.glide.j.a aVarD;
        String strB = this.f3133a.b(cVar);
        this.f3136d.a(strB);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strB + " for for Key: " + cVar);
            }
            try {
                aVarD = d();
            } catch (IOException e2) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e2);
                }
            }
            if (aVarD.z(strB) != null) {
                return;
            }
            a.c cVarW = aVarD.w(strB);
            if (cVarW == null) {
                throw new IllegalStateException("Had two simultaneous puts for: " + strB);
            }
            try {
                if (bVar.a(cVarW.f(0))) {
                    cVarW.e();
                }
                cVarW.b();
            } catch (Throwable th) {
                cVarW.b();
                throw th;
            }
        } finally {
            this.f3136d.b(strB);
        }
    }

    @Override // com.bumptech.glide.load.engine.y.a
    public File b(com.bumptech.glide.load.c cVar) {
        String strB = this.f3133a.b(cVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strB + " for for Key: " + cVar);
        }
        try {
            a.e eVarZ = d().z(strB);
            if (eVarZ != null) {
                return eVarZ.a(0);
            }
            return null;
        } catch (IOException e2) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e2);
            return null;
        }
    }
}
