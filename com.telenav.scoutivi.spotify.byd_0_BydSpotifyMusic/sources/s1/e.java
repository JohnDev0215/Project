package s1;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import k1.a;
import s1.a;

/* JADX INFO: compiled from: DiskLruCacheWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final File f9472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f9473c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private k1.a f9475e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c f9474d = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f9471a = new j();

    @Deprecated
    protected e(File file, long j7) {
        this.f9472b = file;
        this.f9473c = j7;
    }

    public static a c(File file, long j7) {
        return new e(file, j7);
    }

    private synchronized k1.a d() {
        if (this.f9475e == null) {
            this.f9475e = k1.a.h0(this.f9472b, 1, 1, this.f9473c);
        }
        return this.f9475e;
    }

    @Override // s1.a
    public void a(n1.f fVar, a.b bVar) {
        k1.a aVarD;
        String strB = this.f9471a.b(fVar);
        this.f9474d.a(strB);
        try {
            if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
                Log.v("DiskLruCacheWrapper", "Put: Obtained: " + strB + " for for Key: " + fVar);
            }
            try {
                aVarD = d();
            } catch (IOException e7) {
                if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                    Log.w("DiskLruCacheWrapper", "Unable to put to disk cache", e7);
                }
            }
            if (aVarD.f0(strB) != null) {
                return;
            }
            a.c cVarC0 = aVarD.c0(strB);
            if (cVarC0 == null) {
                throw new IllegalStateException("Had two simultaneous puts for: " + strB);
            }
            try {
                if (bVar.a(cVarC0.f(0))) {
                    cVarC0.e();
                }
                cVarC0.b();
            } catch (Throwable th) {
                cVarC0.b();
                throw th;
            }
        } finally {
            this.f9474d.b(strB);
        }
    }

    @Override // s1.a
    public File b(n1.f fVar) {
        String strB = this.f9471a.b(fVar);
        if (Log.isLoggable("DiskLruCacheWrapper", 2)) {
            Log.v("DiskLruCacheWrapper", "Get: Obtained: " + strB + " for for Key: " + fVar);
        }
        try {
            a.e eVarF0 = d().f0(strB);
            if (eVarF0 != null) {
                return eVarF0.a(0);
            }
            return null;
        } catch (IOException e7) {
            if (!Log.isLoggable("DiskLruCacheWrapper", 5)) {
                return null;
            }
            Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e7);
            return null;
        }
    }
}
