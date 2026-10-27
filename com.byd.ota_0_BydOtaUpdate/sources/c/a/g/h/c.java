package c.a.g.h;

import android.text.TextUtils;
import c.a.b.d.l;
import com.byd.repository.bean.EcuVersionInfo;
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

/* JADX INFO: compiled from: FileDownloadObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements i<b0> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2690c = "c";
    public d.a.m.b E3;
    public b F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2691d;
    public String s;
    public EcuVersionInfo x;
    public c.a.g.h.b y;

    /* JADX INFO: compiled from: FileDownloadObserver.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a.b.d.e.b(c.f2690c, "observer callback timeout!!!");
            c.this.e();
            c.this.y.d(1010, "observer callback timeout");
        }
    }

    public c(EcuVersionInfo ecuVersionInfo, c.a.g.h.b bVar) {
        c.a.b.d.e.c(f2690c, "FileDownloadObserver->ecuId = " + ecuVersionInfo.ecuId);
        this.x = ecuVersionInfo;
        this.y = bVar;
        this.F3 = new b();
        l.d().c(this.F3, 60000L);
    }

    public final boolean d() throws Throwable {
        boolean zEquals;
        boolean zEquals2;
        String str = f2690c;
        c.a.b.d.e.c(str, "checkMD5->ecuId = " + this.x.ecuId);
        if (TextUtils.isEmpty(this.x.encryptSHA256)) {
            zEquals = false;
        } else {
            String strA = c.a.f.a.c.a(this.x.fileDir + this.x.fileName);
            zEquals = strA.equals(this.x.encryptSHA256);
            if (zEquals) {
                c.a.b.d.e.c(str, "sha256 is ok");
            } else {
                c.a.b.d.e.b(str, this.x.ecuId + ": sha256 is fail! currentSHA256 = " + strA + ", encryptSHA256 = " + this.x.encryptSHA256);
            }
        }
        if (TextUtils.isEmpty(this.x.encryptMd5)) {
            zEquals2 = false;
        } else {
            EcuVersionInfo ecuVersionInfo = this.x;
            String strA2 = c.a.b.d.f.a(c.a.b.d.d.d(ecuVersionInfo.fileDir, ecuVersionInfo.fileName));
            zEquals2 = strA2.equals(this.x.encryptMd5);
            if (zEquals2) {
                c.a.b.d.e.c(str, "md5 is ok");
            } else {
                c.a.b.d.e.b(str, this.x.ecuId + ": md5 is fail! currentMD5 = " + strA2 + ", encryptMd5 = " + this.x.encryptMd5);
            }
        }
        return zEquals || zEquals2;
    }

    public final synchronized void e() {
        d.a.m.b bVar = this.E3;
        if (bVar != null && !bVar.isDisposed()) {
            this.E3.dispose();
        }
    }

    public final boolean f(b0 b0Var) throws Throwable {
        InputStream inputStreamC;
        FileOutputStream fileOutputStream;
        c.a.b.d.e.c(f2690c, "downloadCommon->ecuId = " + this.x.ecuId);
        long j = 0;
        if (b0Var.v() <= 0) {
            this.f2691d = 1002;
            this.s = "responseBody.contentLength() <= 0";
            return false;
        }
        long jV = b0Var.v();
        this.y.b(0L, jV);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                c.a.b.d.d.e(new File(this.x.fileDir));
                EcuVersionInfo ecuVersionInfo = this.x;
                File fileD = c.a.b.d.d.d(ecuVersionInfo.fileDir, ecuVersionInfo.fileName);
                inputStreamC = b0Var.c();
                fileOutputStream = new FileOutputStream(fileD);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArrJ = c.a.b.d.d.j(jV);
            while (true) {
                int i2 = inputStreamC.read(bArrJ);
                if (i2 == -1) {
                    fileOutputStream.flush();
                    c.a.b.d.d.p(b0Var);
                    c.a.b.d.d.p(fileOutputStream);
                    return true;
                }
                fileOutputStream.write(bArrJ, 0, i2);
                j += (long) i2;
                this.y.c(j, jV);
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            this.f2691d = 1003;
            this.s = e.toString();
            e.printStackTrace();
            c.a.b.d.d.p(b0Var);
            c.a.b.d.d.p(fileOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            c.a.b.d.d.p(b0Var);
            c.a.b.d.d.p(fileOutputStream2);
            throw th;
        }
    }

    public final boolean g(b0 b0Var) throws Throwable {
        InputStream inputStreamC;
        RandomAccessFile randomAccessFile;
        c.a.b.d.e.c(f2690c, "downloadRange->ecuId = " + this.x.ecuId);
        if (b0Var.v() <= 0) {
            this.f2691d = 1002;
            this.s = "responseBody.contentLength() <= 0";
            return false;
        }
        long jV = b0Var.v();
        EcuVersionInfo ecuVersionInfo = this.x;
        long jM = c.a.b.d.d.m(ecuVersionInfo.fileDir, ecuVersionInfo.fileName);
        long j = jV + jM;
        this.y.b(jM, j);
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                c.a.b.d.d.e(new File(this.x.fileDir));
                EcuVersionInfo ecuVersionInfo2 = this.x;
                File fileD = c.a.b.d.d.d(ecuVersionInfo2.fileDir, ecuVersionInfo2.fileName);
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
                this.y.c(jM, j);
            }
        } catch (Exception e3) {
            e = e3;
            randomAccessFile2 = randomAccessFile;
            this.f2691d = 1003;
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

    public void h() {
        c.a.b.d.e.c(f2690c, "interrupt->ecuId = " + this.x.ecuId);
        e();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean i() {
        /*
            r1 = this;
            monitor-enter(r1)
            d.a.m.b r0 = r1.E3     // Catch: java.lang.Throwable -> L11
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
        throw new UnsupportedOperationException("Method not decompiled: c.a.g.h.c.i():boolean");
    }

    @Override // d.a.i
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public void onNext(b0 b0Var) {
        c.a.b.d.e.c(f2690c, "onNext->ecuId = " + this.x.ecuId);
        l.d().i(this.F3);
        EcuVersionInfo ecuVersionInfo = this.x;
        if (new File(ecuVersionInfo.fileDir, ecuVersionInfo.fileName).exists() ? g(b0Var) : f(b0Var)) {
            if (d()) {
                this.y.a();
                return;
            } else {
                this.y.d(1004, "md5 or sha256 is fail");
                return;
            }
        }
        if (i()) {
            this.y.d(1001, "user self disposed error");
        } else {
            this.y.d(this.f2691d, this.s);
        }
    }

    @Override // d.a.i
    public void onComplete() {
        c.a.b.d.e.c(f2690c, "onComplete->ecuId = " + this.x.ecuId);
        l.d().i(this.F3);
        e();
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        c.a.b.d.e.c(f2690c, "onError->ecuId = " + this.x.ecuId);
        l.d().i(this.F3);
        e();
        if (th instanceof HttpException) {
            this.f2691d = ((HttpException) th).code();
        } else if (th instanceof ConnectException) {
            this.f2691d = 1005;
        } else if (th instanceof SSLHandshakeException) {
            this.f2691d = 1006;
        } else if (th instanceof UnknownHostException) {
            this.f2691d = 1007;
        } else if (th instanceof SocketTimeoutException) {
            this.f2691d = 1008;
        } else if (th instanceof StreamResetException) {
            this.f2691d = 1009;
        } else {
            this.f2691d = 1000;
        }
        this.y.d(this.f2691d, th.toString());
    }

    @Override // d.a.i
    public void onSubscribe(d.a.m.b bVar) {
        c.a.b.d.e.c(f2690c, "onSubscribe->ecuId = " + this.x.ecuId);
        this.E3 = bVar;
    }
}
