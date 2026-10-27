package c.a.g.h;

import android.text.TextUtils;
import c.a.b.d.l;
import d.a.i;
import e.b0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import okhttp3.internal.http2.StreamResetException;
import retrofit2.HttpException;

/* JADX INFO: compiled from: TranslateFileDownloadObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements i<b0> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2695c = "f";
    public b E3 = new b();
    public String F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2696d;
    public String s;
    public c.a.g.h.b x;
    public d.a.m.b y;

    /* JADX INFO: compiled from: TranslateFileDownloadObserver.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(f.f2695c, "observer callback timeout!!!");
            f.this.e();
            f.this.x.d(1010, "observer callback timeout");
        }
    }

    public f(String str, c.a.g.h.b bVar) {
        this.F3 = str;
        this.x = bVar;
        l.d().c(this.E3, 60000L);
    }

    public final boolean d() throws Throwable {
        String str = f2695c;
        c.a.b.d.e.c(str, "TranslateFileDownloadObserver checkSHA256");
        if (TextUtils.isEmpty(this.F3)) {
            return false;
        }
        String strA = c.a.f.a.c.a(c.a.b.b.a.o + "translate.json.enc.zip");
        boolean zEquals = strA.equals(this.F3);
        if (zEquals) {
            c.a.b.d.e.c(str, "sha256 is ok");
            return zEquals;
        }
        c.a.b.d.e.b(str, "sha256 is fail! currentSHA256 = " + strA + ", encryptSHA256 = " + this.F3);
        return zEquals;
    }

    public final synchronized void e() {
        d.a.m.b bVar = this.y;
        if (bVar != null && !bVar.isDisposed()) {
            this.y.dispose();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    public final boolean f(b0 b0Var) throws Throwable {
        InputStream inputStreamC;
        FileOutputStream fileOutputStream;
        byte[] bArrJ;
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver downloadCommon");
        long j = 0;
        boolean z = false;
        if (b0Var.v() <= 0) {
            this.f2696d = 1002;
            this.s = "responseBody.contentLength() <= 0";
            return false;
        }
        long jV = b0Var.v();
        this.x.b(0L, jV);
        ?? r6 = 0;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                String str = c.a.b.b.a.o;
                c.a.b.d.d.e(new File(str));
                File fileD = c.a.b.d.d.d(str, "translate.json.enc.zip");
                inputStreamC = b0Var.c();
                fileOutputStream = new FileOutputStream(fileD);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            bArrJ = c.a.b.d.d.j(jV);
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            this.f2696d = 1003;
            this.s = e.toString();
            e.printStackTrace();
            c.a.b.d.d.p(b0Var);
            c.a.b.d.d.p(fileOutputStream2);
            r6 = fileOutputStream2;
        } catch (Throwable th2) {
            th = th2;
            r6 = fileOutputStream;
            c.a.b.d.d.p(b0Var);
            c.a.b.d.d.p(r6);
            throw th;
        }
        while (true) {
            int i2 = inputStreamC.read(bArrJ);
            if (i2 == -1) {
                break;
            }
            fileOutputStream.write(bArrJ, 0, i2);
            j += (long) i2;
            this.x.c(j, jV);
            return z;
        }
        fileOutputStream.flush();
        c.a.b.d.d.p(b0Var);
        c.a.b.d.d.p(fileOutputStream);
        z = true;
        r6 = bArrJ;
        return z;
    }

    public final boolean g(b0 b0Var) throws Throwable {
        InputStream inputStreamC;
        RandomAccessFile randomAccessFile;
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver downloadRange");
        if (b0Var.v() <= 0) {
            this.f2696d = 1002;
            this.s = "responseBody.contentLength() <= 0";
            return false;
        }
        long jV = b0Var.v();
        String str = c.a.b.b.a.o;
        long jM = c.a.b.d.d.m(str, "translate.json.enc.zip");
        long j = jV + jM;
        this.x.b(jM, j);
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                c.a.b.d.d.e(new File(str));
                File fileD = c.a.b.d.d.d(str, "translate.json.enc.zip");
                inputStreamC = b0Var.c();
                randomAccessFile = new RandomAccessFile(fileD, "rw");
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            randomAccessFile.seek(jM);
            byte[] bArrJ = c.a.b.d.d.j(jV);
            while (true) {
                int i2 = inputStreamC.read(bArrJ);
                if (i2 == -1) {
                    c.a.b.d.d.p(randomAccessFile);
                    c.a.b.d.d.p(b0Var);
                    return true;
                }
                randomAccessFile.write(bArrJ, 0, i2);
                jM += (long) i2;
                this.x.c(jM, j);
            }
        } catch (Exception e3) {
            e = e3;
            randomAccessFile2 = randomAccessFile;
            this.f2696d = 1003;
            this.s = e.toString();
            e.printStackTrace();
            c.a.b.d.d.p(randomAccessFile2);
            c.a.b.d.d.p(b0Var);
            return false;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            c.a.b.d.d.p(randomAccessFile2);
            c.a.b.d.d.p(b0Var);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean h() {
        /*
            r1 = this;
            monitor-enter(r1)
            d.a.m.b r0 = r1.y     // Catch: java.lang.Throwable -> L11
            if (r0 == 0) goto Le
            boolean r0 = r0.isDisposed()     // Catch: java.lang.Throwable -> L11
            if (r0 == 0) goto Lc
            goto Le
        Lc:
            r0 = 0
            goto Lf
        Le:
            r0 = 1
        Lf:
            monitor-exit(r1)
            return r0
        L11:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.g.h.f.h():boolean");
    }

    @Override // d.a.i
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public void onNext(b0 b0Var) {
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver onNext");
        l.d().i(this.E3);
        if (new File(c.a.b.b.a.o, "translate.json.enc.zip").exists() ? g(b0Var) : f(b0Var)) {
            if (d()) {
                this.x.a();
                return;
            } else {
                this.x.d(1004, "md5 or sha256 is fail");
                return;
            }
        }
        if (h()) {
            this.x.d(1001, "user self disposed error");
        } else {
            this.x.d(this.f2696d, this.s);
        }
    }

    @Override // d.a.i
    public void onComplete() {
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver onComplete");
        l.d().i(this.E3);
        e();
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver onError");
        l.d().i(this.E3);
        e();
        if (th instanceof HttpException) {
            this.f2696d = ((HttpException) th).code();
        } else if (th instanceof ConnectException) {
            this.f2696d = 1005;
        } else if (th instanceof SSLHandshakeException) {
            this.f2696d = 1006;
        } else if (th instanceof UnknownHostException) {
            this.f2696d = 1007;
        } else if (th instanceof SocketTimeoutException) {
            this.f2696d = 1008;
        } else if (th instanceof StreamResetException) {
            this.f2696d = 1009;
        } else {
            this.f2696d = 1000;
        }
        this.x.d(this.f2696d, th.toString());
    }

    @Override // d.a.i
    public void onSubscribe(d.a.m.b bVar) {
        c.a.b.d.e.c(f2695c, "TranslateFileDownloadObserver onSubscribe");
        this.y = bVar;
    }
}
