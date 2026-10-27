package f.b0.i;

import f.q;
import f.u;
import f.w;
import f.y;
import f.z;
import g.o;
import g.p;
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
public final class d implements f.b0.g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f5151a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteString f5152b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f5153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ByteString f5154d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f5155e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f5156f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ByteString f5157g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ByteString f5158h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final List<ByteString> f5159i;
    public static final List<ByteString> j;
    public final u k;
    public final f.b0.f.f l;
    public final e m;
    public g n;

    /* JADX INFO: compiled from: Http2Codec.java */
    public class a extends g.f {
        public a(p pVar) {
            super(pVar);
        }

        @Override // g.f, g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            d dVar = d.this;
            dVar.l.p(false, dVar);
            super.close();
        }
    }

    static {
        ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8("connection");
        f5151a = byteStringEncodeUtf8;
        ByteString byteStringEncodeUtf82 = ByteString.encodeUtf8("host");
        f5152b = byteStringEncodeUtf82;
        ByteString byteStringEncodeUtf83 = ByteString.encodeUtf8("keep-alive");
        f5153c = byteStringEncodeUtf83;
        ByteString byteStringEncodeUtf84 = ByteString.encodeUtf8("proxy-connection");
        f5154d = byteStringEncodeUtf84;
        ByteString byteStringEncodeUtf85 = ByteString.encodeUtf8("transfer-encoding");
        f5155e = byteStringEncodeUtf85;
        ByteString byteStringEncodeUtf86 = ByteString.encodeUtf8("te");
        f5156f = byteStringEncodeUtf86;
        ByteString byteStringEncodeUtf87 = ByteString.encodeUtf8("encoding");
        f5157g = byteStringEncodeUtf87;
        ByteString byteStringEncodeUtf88 = ByteString.encodeUtf8("upgrade");
        f5158h = byteStringEncodeUtf88;
        f5159i = f.b0.c.o(byteStringEncodeUtf8, byteStringEncodeUtf82, byteStringEncodeUtf83, byteStringEncodeUtf84, byteStringEncodeUtf86, byteStringEncodeUtf85, byteStringEncodeUtf87, byteStringEncodeUtf88, f.b0.i.a.f5121c, f.b0.i.a.f5122d, f.b0.i.a.f5123e, f.b0.i.a.f5124f);
        j = f.b0.c.o(byteStringEncodeUtf8, byteStringEncodeUtf82, byteStringEncodeUtf83, byteStringEncodeUtf84, byteStringEncodeUtf86, byteStringEncodeUtf85, byteStringEncodeUtf87, byteStringEncodeUtf88);
    }

    public d(u uVar, f.b0.f.f fVar, e eVar) {
        this.k = uVar;
        this.l = fVar;
        this.m = eVar;
    }

    public static List<f.b0.i.a> g(w wVar) {
        q qVarD = wVar.d();
        ArrayList arrayList = new ArrayList(qVarD.f() + 4);
        arrayList.add(new f.b0.i.a(f.b0.i.a.f5121c, wVar.f()));
        arrayList.add(new f.b0.i.a(f.b0.i.a.f5122d, f.b0.g.i.c(wVar.h())));
        String strC = wVar.c("Host");
        if (strC != null) {
            arrayList.add(new f.b0.i.a(f.b0.i.a.f5124f, strC));
        }
        arrayList.add(new f.b0.i.a(f.b0.i.a.f5123e, wVar.h().C()));
        int iF = qVarD.f();
        for (int i2 = 0; i2 < iF; i2++) {
            ByteString byteStringEncodeUtf8 = ByteString.encodeUtf8(qVarD.c(i2).toLowerCase(Locale.US));
            if (!f5159i.contains(byteStringEncodeUtf8)) {
                arrayList.add(new f.b0.i.a(byteStringEncodeUtf8, qVarD.g(i2)));
            }
        }
        return arrayList;
    }

    public static y.a h(List<f.b0.i.a> list) throws IOException {
        q.a aVar = new q.a();
        int size = list.size();
        f.b0.g.k kVarA = null;
        for (int i2 = 0; i2 < size; i2++) {
            f.b0.i.a aVar2 = list.get(i2);
            if (aVar2 != null) {
                ByteString byteString = aVar2.f5125g;
                String strUtf8 = aVar2.f5126h.utf8();
                if (byteString.equals(f.b0.i.a.f5120b)) {
                    kVarA = f.b0.g.k.a("HTTP/1.1 " + strUtf8);
                } else if (!j.contains(byteString)) {
                    f.b0.a.f5010a.b(aVar, byteString.utf8(), strUtf8);
                }
            } else if (kVarA != null && kVarA.f5094b == 100) {
                aVar = new q.a();
                kVarA = null;
            }
        }
        if (kVarA != null) {
            return new y.a().m(Protocol.HTTP_2).g(kVarA.f5094b).j(kVarA.f5095c).i(aVar.d());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // f.b0.g.c
    public void a() throws IOException {
        this.n.h().close();
    }

    @Override // f.b0.g.c
    public void b(w wVar) throws IOException {
        if (this.n != null) {
            return;
        }
        g gVarS = this.m.S(g(wVar), wVar.a() != null);
        this.n = gVarS;
        g.q qVarL = gVarS.l();
        long jT = this.k.t();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        qVarL.g(jT, timeUnit);
        this.n.s().g(this.k.B(), timeUnit);
    }

    @Override // f.b0.g.c
    public z c(y yVar) throws IOException {
        return new f.b0.g.h(yVar.S(), g.j.b(new a(this.n.i())));
    }

    @Override // f.b0.g.c
    public void cancel() {
        g gVar = this.n;
        if (gVar != null) {
            gVar.f(ErrorCode.CANCEL);
        }
    }

    @Override // f.b0.g.c
    public void d() throws IOException {
        this.m.flush();
    }

    @Override // f.b0.g.c
    public o e(w wVar, long j2) {
        return this.n.h();
    }

    @Override // f.b0.g.c
    public y.a f(boolean z) throws IOException {
        y.a aVarH = h(this.n.q());
        if (z && f.b0.a.f5010a.d(aVarH) == 100) {
            return null;
        }
        return aVarH;
    }
}
