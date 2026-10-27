package f.k0.i;

import f.e0;
import f.f0;
import f.g0;
import f.p;
import f.q;
import f.y;
import f.z;
import java.util.List;
import okio.GzipSource;
import okio.Okio;

/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final q f5226a;

    public a(q qVar) {
        this.f5226a = qVar;
    }

    private String b(List<p> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                sb.append("; ");
            }
            p pVar = list.get(i2);
            sb.append(pVar.c());
            sb.append('=');
            sb.append(pVar.k());
        }
        return sb.toString();
    }

    @Override // f.y
    public g0 a(y.a aVar) {
        e0 e0VarA = aVar.a();
        e0.a aVarG = e0VarA.g();
        f0 f0VarA = e0VarA.a();
        if (f0VarA != null) {
            z zVarB = f0VarA.b();
            if (zVarB != null) {
                aVarG.b("Content-Type", zVarB.toString());
            }
            long jA = f0VarA.a();
            if (jA != -1) {
                aVarG.b("Content-Length", Long.toString(jA));
                aVarG.h("Transfer-Encoding");
            } else {
                aVarG.b("Transfer-Encoding", "chunked");
                aVarG.h("Content-Length");
            }
        }
        boolean z = false;
        if (e0VarA.c("Host") == null) {
            aVarG.b("Host", f.k0.e.r(e0VarA.i(), false));
        }
        if (e0VarA.c("Connection") == null) {
            aVarG.b("Connection", "Keep-Alive");
        }
        if (e0VarA.c("Accept-Encoding") == null && e0VarA.c("Range") == null) {
            z = true;
            aVarG.b("Accept-Encoding", "gzip");
        }
        List<p> listA = this.f5226a.a(e0VarA.i());
        if (!listA.isEmpty()) {
            aVarG.b("Cookie", b(listA));
        }
        if (e0VarA.c("User-Agent") == null) {
            aVarG.b("User-Agent", f.k0.f.a());
        }
        g0 g0VarD = aVar.d(aVarG.a());
        e.e(this.f5226a, e0VarA.i(), g0VarD.S());
        g0.a aVarQ = g0VarD.V().q(e0VarA);
        if (z && "gzip".equalsIgnoreCase(g0VarD.C("Content-Encoding")) && e.c(g0VarD)) {
            GzipSource gzipSource = new GzipSource(g0VarD.a().S());
            aVarQ.j(g0VarD.S().f().g("Content-Encoding").g("Content-Length").f());
            aVarQ.b(new h(g0VarD.C("Content-Type"), -1L, Okio.c(gzipSource)));
        }
        return aVarQ.c();
    }
}
