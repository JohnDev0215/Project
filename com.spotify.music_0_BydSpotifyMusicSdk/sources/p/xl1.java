package p;

import java.io.Closeable;
import java.io.IOException;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class xl1 implements Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ar1 f208p;
    public final ko c;
    public final ByteString e;
    public final ByteString k;
    public int l;
    public boolean m;
    public boolean n;
    public wl1 o;

    static {
        ByteString byteString = ByteString.k;
        f208p = qo.r(qo.p("\r\n"), qo.p("--"), qo.p(" "), qo.p("\t"));
    }

    public xl1(ko koVar, String str) {
        this.c = koVar;
        fo foVar = new fo();
        foVar.d1("--");
        foVar.d1(str);
        this.e = foVar.v(foVar.e);
        fo foVar2 = new fo();
        foVar2.d1("\r\n--");
        foVar2.d1(str);
        this.k = foVar2.v(foVar2.e);
    }

    public final long b(long j) {
        ByteString byteString = this.k;
        long jE = byteString.e();
        ko koVar = this.c;
        koVar.q0(jE);
        long jP = koVar.d().P(byteString);
        return jP == -1 ? Math.min(j, (koVar.d().e - ((long) byteString.e())) + 1) : Math.min(j, jP);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.m) {
            return;
        }
        this.m = true;
        this.o = null;
        this.c.close();
    }
}
