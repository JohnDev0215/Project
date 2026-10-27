package f.k0.k;

import f.b0;
import f.c0;
import f.e0;
import f.g0;
import f.w;
import f.y;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.Sink;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: Http2ExchangeCodec.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements f.k0.i.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<String> f5365a = f.k0.e.t("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final List<String> f5366b = f.k0.e.t("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final y.a f5367c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f.k0.h.f f5368d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final f f5369e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile i f5370f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c0 f5371g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private volatile boolean f5372h;

    public g(b0 b0Var, f.k0.h.f fVar, y.a aVar, f fVar2) {
        this.f5368d = fVar;
        this.f5367c = aVar;
        this.f5369e = fVar2;
        List<c0> listW = b0Var.w();
        c0 c0Var = c0.H2_PRIOR_KNOWLEDGE;
        this.f5371g = listW.contains(c0Var) ? c0Var : c0.HTTP_2;
    }

    public static List<c> i(e0 e0Var) {
        w wVarD = e0Var.d();
        ArrayList arrayList = new ArrayList(wVarD.h() + 4);
        arrayList.add(new c(c.f5283c, e0Var.f()));
        arrayList.add(new c(c.f5284d, f.k0.i.i.c(e0Var.i())));
        String strC = e0Var.c("Host");
        if (strC != null) {
            arrayList.add(new c(c.f5286f, strC));
        }
        arrayList.add(new c(c.f5285e, e0Var.i().D()));
        int iH = wVarD.h();
        for (int i2 = 0; i2 < iH; i2++) {
            String lowerCase = wVarD.e(i2).toLowerCase(Locale.US);
            if (!f5365a.contains(lowerCase) || (lowerCase.equals("te") && wVarD.i(i2).equals("trailers"))) {
                arrayList.add(new c(lowerCase, wVarD.i(i2)));
            }
        }
        return arrayList;
    }

    public static g0.a j(w wVar, c0 c0Var) throws ProtocolException {
        w.a aVar = new w.a();
        int iH = wVar.h();
        f.k0.i.k kVarA = null;
        for (int i2 = 0; i2 < iH; i2++) {
            String strE = wVar.e(i2);
            String strI = wVar.i(i2);
            if (strE.equals(":status")) {
                kVarA = f.k0.i.k.a("HTTP/1.1 " + strI);
            } else if (!f5366b.contains(strE)) {
                f.k0.c.f5131a.b(aVar, strE, strI);
            }
        }
        if (kVarA != null) {
            return new g0.a().o(c0Var).g(kVarA.f5247b).l(kVarA.f5248c).j(aVar.f());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // f.k0.i.c
    public void a() {
        this.f5370f.h().close();
    }

    @Override // f.k0.i.c
    public f.k0.h.f b() {
        return this.f5368d;
    }

    @Override // f.k0.i.c
    public void c(e0 e0Var) throws IOException {
        if (this.f5370f != null) {
            return;
        }
        this.f5370f = this.f5369e.d0(i(e0Var), e0Var.a() != null);
        if (this.f5372h) {
            this.f5370f.f(b.CANCEL);
            throw new IOException("Canceled");
        }
        Timeout timeoutL = this.f5370f.l();
        long jF = this.f5367c.f();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeoutL.h(jF, timeUnit);
        this.f5370f.r().h(this.f5367c.c(), timeUnit);
    }

    @Override // f.k0.i.c
    public void cancel() {
        this.f5372h = true;
        if (this.f5370f != null) {
            this.f5370f.f(b.CANCEL);
        }
    }

    @Override // f.k0.i.c
    public void d() {
        this.f5369e.flush();
    }

    @Override // f.k0.i.c
    public Sink e(e0 e0Var, long j) {
        return this.f5370f.h();
    }

    @Override // f.k0.i.c
    public long f(g0 g0Var) {
        return f.k0.i.e.b(g0Var);
    }

    @Override // f.k0.i.c
    public Source g(g0 g0Var) {
        return this.f5370f.i();
    }

    @Override // f.k0.i.c
    public g0.a h(boolean z) throws ProtocolException {
        g0.a aVarJ = j(this.f5370f.p(), this.f5371g);
        if (z && f.k0.c.f5131a.d(aVarJ) == 100) {
            return null;
        }
        return aVarJ;
    }
}
