package com.bumptech.glide.load.n.b0;

import android.util.Log;
import com.bumptech.glide.load.n.b0.a;
import com.bumptech.glide.m.a;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f2070b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f2071c;
    private com.bumptech.glide.m.a e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f2072d = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f2069a = new j();

    @Deprecated
    protected e(File file, long j) {
        this.f2070b = file;
        this.f2071c = j;
    }

    public static a c(File file, long j) {
        return new e(file, j);
    }

    private synchronized com.bumptech.glide.m.a d() {
        if (this.e == null) {
            this.e = com.bumptech.glide.m.a.s(this.f2070b, 1, 1, this.f2071c);
        }
        return this.e;
    }

    @Override // com.bumptech.glide.load.n.b0.a
    public void a(com.bumptech.glide.load.g gVar, a.b bVar) {
        com.bumptech.glide.m.a aVarD;
        String strB = this.f2069a.b(gVar);
        this.f2072d.a(strB);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strB + " for for Key: " + gVar);
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
            this.f2072d.b(strB);
        }
    }

    @Override // com.bumptech.glide.load.n.b0.a
    public File b(com.bumptech.glide.load.g gVar) {
        String strB = this.f2069a.b(gVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strB + " for for Key: " + gVar);
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
