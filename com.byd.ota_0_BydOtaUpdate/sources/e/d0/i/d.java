package e.d0.i;

import e.a0;
import e.b0;
import e.s;
import e.w;
import e.y;
import f.p;
import f.q;
import f.r;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;

/* JADX INFO: compiled from: Http2Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements e.d0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f3459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteString f3460b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f3461c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ByteString f3462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f3463e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f3464f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ByteString f3465g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ByteString f3466h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final List<ByteString> f3467i;
    public static final List<ByteString> j;
    public final w k;
    public final e.d0.f.f l;
    public final e m;
    public g n;

    /* JADX INFO: compiled from: Http2Codec.java */
    public class a extends f.g {
        public a(q qVar) {
            super(qVar);
        }

        @Override // f.g, f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            d dVar = d.this;
            dVar.l.p(false, dVar);
            super.close();
        }
    }

    static {
        ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8("connection");
        f3459a = byteStringEncodeUtf8;
        ByteString byteStringEncodeUtf82 = ByteString.encodeUtf8("host");
        f3460b = byteStringEncodeUtf82;
        ByteString byteStringEncodeUtf83 = ByteString.encodeUtf8("keep-alive");
        f3461c = byteStringEncodeUtf83;
        ByteString byteStringEncodeUtf84 = ByteString.encodeUtf8("proxy-connection");
        f3462d = byteStringEncodeUtf84;
        ByteString byteStringEncodeUtf85 = ByteString.encodeUtf8("transfer-encoding");
        f3463e = byteStringEncodeUtf85;
        ByteString byteStringEncodeUtf86 = ByteString.encodeUtf8("te");
        f3464f = byteStringEncodeUtf86;
        ByteString byteStringEncodeUtf87 = ByteString.encodeUtf8("encoding");
        f3465g = byteStringEncodeUtf87;
        ByteString byteStringEncodeUtf88 = ByteString.encodeUtf8("upgrade");
        f3466h = byteStringEncodeUtf88;
        f3467i = e.d0.c.o(byteStringEncodeUtf8, byteStringEncodeUtf82, byteStringEncodeUtf83, byteStringEncodeUtf84, byteStringEncodeUtf86, byteStringEncodeUtf85, byteStringEncodeUtf87, byteStringEncodeUtf88, e.d0.i.a.f3429c, e.d0.i.a.f3430d, e.d0.i.a.f3431e, e.d0.i.a.f3432f);
        j = e.d0.c.o(byteStringEncodeUtf8, byteStringEncodeUtf82, byteStringEncodeUtf83, byteStringEncodeUtf84, byteStringEncodeUtf86, byteStringEncodeUtf85, byteStringEncodeUtf87, byteStringEncodeUtf88);
    }

    public d(w wVar, e.d0.f.f fVar, e eVar) {
        this.k = wVar;
        this.l = fVar;
        this.m = eVar;
    }

    public static List<e.d0.i.a> g(y yVar) {
        s sVarD = yVar.d();
        ArrayList arrayList = new ArrayList(sVarD.f() + 4);
        arrayList.add(new e.d0.i.a(e.d0.i.a.f3429c, yVar.f()));
        arrayList.add(new e.d0.i.a(e.d0.i.a.f3430d, e.d0.g.i.c(yVar.h())));
        String strC = yVar.c("Host");
        if (strC != null) {
            arrayList.add(new e.d0.i.a(e.d0.i.a.f3432f, strC));
        }
        arrayList.add(new e.d0.i.a(e.d0.i.a.f3431e, yVar.h().D()));
        int iF = sVarD.f();
        for (int i2 = 0; i2 < iF; i2++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(sVarD.c(i2).toLowerCase(Locale.US));
            if (!f3467i.contains(byteStringEncodeUtf8)) {
                arrayList.add(new e.d0.i.a(byteStringEncodeUtf8, sVarD.g(i2)));
            }
        }
        return arrayList;
    }

    public static a0.a h(List<e.d0.i.a> list) throws IOException {
        s.a aVar = new s.a();
        int size = list.size();
        e.d0.g.k kVarA = null;
        for (int i2 = 0; i2 < size; i2++) {
            e.d0.i.a aVar2 = list.get(i2);
            if (aVar2 != null) {
                ByteString byteString = aVar2.f3433g;
                String strUtf8 = aVar2.f3434h.utf8();
                if (byteString.equals(e.d0.i.a.f3428b)) {
                    kVarA = e.d0.g.k.a("HTTP/1.1 " + strUtf8);
                } else if (!j.contains(byteString)) {
                    e.d0.a.f3334a.b(aVar, byteString.utf8(), strUtf8);
                }
            } else if (kVarA != null && kVarA.f3414b == 100) {
                aVar = new s.a();
                kVarA = null;
            }
        }
        if (kVarA != null) {
            return new a0.a().m(Protocol.HTTP_2).g(kVarA.f3414b).j(kVarA.f3415c).i(aVar.d());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // e.d0.g.c
    public void a() throws IOException {
        this.n.h().close();
    }

    @Override // e.d0.g.c
    public void b(y yVar) throws IOException {
        if (this.n != null) {
            return;
        }
        g gVarJ = this.m.J(g(yVar), yVar.a() != null);
        this.n = gVarJ;
        r rVarL = gVarJ.l();
        long jZ = this.k.z();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        rVarL.g(jZ, timeUnit);
        this.n.s().g(this.k.F(), timeUnit);
    }

    @Override // e.d0.g.c
    public b0 c(a0 a0Var) throws IOException {
        return new e.d0.g.h(a0Var.J(), f.k.b(new a(this.n.i())));
    }

    @Override // e.d0.g.c
    public void cancel() {
        g gVar = this.n;
        if (gVar != null) {
            gVar.f(ErrorCode.CANCEL);
        }
    }

    @Override // e.d0.g.c
    public void d() throws IOException {
        this.m.flush();
    }

    @Override // e.d0.g.c
    public p e(y yVar, long j2) {
        return this.n.h();
    }

    @Override // e.d0.g.c
    public a0.a f(boolean z) throws IOException {
        a0.a aVarH = h(this.n.q());
        if (z && e.d0.a.f3334a.d(aVarH) == 100) {
            return null;
        }
        return aVarH;
    }
}
