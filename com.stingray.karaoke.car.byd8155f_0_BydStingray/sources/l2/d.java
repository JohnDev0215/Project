package l2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1735b f18450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18451b;
    public final int c;
    public k2.m d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f18452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public File f18453f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public OutputStream f18454g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f18455h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f18456i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public r f18457j;

    public d(InterfaceC1735b interfaceC1735b) {
        interfaceC1735b.getClass();
        this.f18450a = interfaceC1735b;
        this.f18451b = 5242880L;
        this.c = 20480;
    }

    public final void a() {
        OutputStream outputStream = this.f18454g;
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.flush();
            AbstractC1343A.g(this.f18454g);
            this.f18454g = null;
            File file = this.f18453f;
            this.f18453f = null;
            long j3 = this.f18455h;
            t tVar = (t) this.f18450a;
            synchronized (tVar) {
                if (file.exists()) {
                    if (j3 == 0) {
                        file.delete();
                        return;
                    }
                    u uVarB = u.b(file, j3, -9223372036854775807L, tVar.c);
                    uVarB.getClass();
                    k kVarX = tVar.c.x(uVarB.l);
                    kVarX.getClass();
                    AbstractC1346c.j(kVarX.a(uVarB.f18478m, uVarB.f18479n));
                    long jA = n.a(kVarX.f18486e);
                    if (jA != -1) {
                        AbstractC1346c.j(uVarB.f18478m + uVarB.f18479n <= jA);
                    }
                    if (tVar.d == null) {
                        tVar.b(uVarB);
                        tVar.c.N();
                        tVar.notifyAll();
                        return;
                    }
                    try {
                        tVar.d.e(uVarB.f18479n, uVarB.f18481q, file.getName());
                        tVar.b(uVarB);
                        try {
                            tVar.c.N();
                            tVar.notifyAll();
                            return;
                        } catch (IOException e10) {
                            throw new C1734a(e10);
                        }
                    } catch (IOException e11) {
                        throw new C1734a(e11);
                    }
                }
            }
        } catch (Throwable th) {
            AbstractC1343A.g(this.f18454g);
            this.f18454g = null;
            File file2 = this.f18453f;
            this.f18453f = null;
            file2.delete();
            throw th;
        }
    }

    public final void b(k2.m mVar) {
        File fileC;
        long j3 = mVar.f17679g;
        long jMin = j3 == -1 ? -1L : Math.min(j3 - this.f18456i, this.f18452e);
        InterfaceC1735b interfaceC1735b = this.f18450a;
        String str = mVar.f17680h;
        int i7 = AbstractC1343A.f15195a;
        long j10 = mVar.f17678f + this.f18456i;
        t tVar = (t) interfaceC1735b;
        synchronized (tVar) {
            try {
                tVar.d();
                k kVarX = tVar.c.x(str);
                kVarX.getClass();
                AbstractC1346c.j(kVarX.a(j10, jMin));
                if (!tVar.f18496a.exists()) {
                    t.e(tVar.f18496a);
                    tVar.l();
                }
                q qVar = tVar.f18497b;
                if (jMin != -1) {
                    qVar.a(tVar, jMin);
                } else {
                    qVar.getClass();
                }
                File file = new File(tVar.f18496a, Integer.toString(tVar.f18499f.nextInt(10)));
                if (!file.exists()) {
                    t.e(file);
                }
                fileC = u.c(file, kVarX.f18484a, j10, System.currentTimeMillis());
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f18453f = fileC;
        File file2 = this.f18453f;
        io.sentry.instrumentation.file.g gVarM = h6.m.m(new FileOutputStream(file2), file2);
        if (this.c > 0) {
            r rVar = this.f18457j;
            if (rVar == null) {
                this.f18457j = new r(gVarM, this.c);
            } else {
                rVar.b(gVarM);
            }
            this.f18454g = this.f18457j;
        } else {
            this.f18454g = gVarM;
        }
        this.f18455h = 0L;
    }
}
