package defpackage;

import defpackage.fd0;
import defpackage.o93;
import org.chromium.chrome.browser.download.DownloadInfo;
import org.chromium.chrome.browser.download.f;

/* JADX INFO: compiled from: SystemDownloadNotifier.java */
/* JADX INFO: loaded from: classes.dex */
public final class dy2 implements hd0 {
    public fd0 a;

    /* JADX INFO: compiled from: SystemDownloadNotifier.java */
    public static final class a {
        public final int a;
        public final DownloadInfo b;
        public final int c;
        public long d;
        public boolean e;
        public boolean f;
        public int g;

        public a(int i, DownloadInfo downloadInfo, int i2) {
            this.a = i;
            this.b = downloadInfo;
            this.c = i2;
        }
    }

    public final void a(a aVar) {
        o93 o93Var = o93.a.a;
        ny nyVar = aVar.b.z;
        z8 z8Var = new z8(this, 10, aVar);
        zz1 zz1Var = new zz1(nyVar, aVar.c, z8Var);
        o93Var.getClass();
        zz1 zz1VarB = o93Var.b(nyVar);
        if (zz1VarB != null) {
            zz1Var.d = zz1VarB.d;
        }
        if (o93Var.c) {
            o93Var.a.add(zz1Var);
            return;
        }
        o93Var.c = true;
        z8Var.run();
        o93Var.b.postDelayed(new f52(24, o93Var), 350L);
    }

    public final fd0 b() {
        if (this.a == null) {
            this.a = fd0.a.a;
        }
        return this.a;
    }

    public final void c(ny nyVar) {
        o93.a.a.b(nyVar);
        fd0 fd0VarB = b();
        f fVarA = fd0VarB.d.a(nyVar);
        if (fVarA == null) {
            return;
        }
        fd0VarB.d(nyVar, fVarA.a);
    }

    public final void d(DownloadInfo downloadInfo) {
        a(new a(3, downloadInfo, 0));
    }

    public final void e(DownloadInfo downloadInfo, long j, boolean z, boolean z2) {
        a aVar = new a(2, downloadInfo, 0);
        aVar.d = j;
        aVar.e = z;
        a(aVar);
    }
}
