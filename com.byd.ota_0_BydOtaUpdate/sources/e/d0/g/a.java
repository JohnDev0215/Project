package e.d0.g;

import e.a0;
import e.l;
import e.m;
import e.s;
import e.t;
import e.u;
import e.y;
import e.z;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f3394a;

    public a(m mVar) {
        this.f3394a = mVar;
    }

    @Override // e.t
    public a0 a(t.a aVar) throws IOException {
        y yVarB = aVar.b();
        y.a aVarG = yVarB.g();
        z zVarA = yVarB.a();
        if (zVarA != null) {
            u uVarB = zVarA.b();
            if (uVarB != null) {
                aVarG.d("Content-Type", uVarB.toString());
            }
            long jA = zVarA.a();
            if (jA != -1) {
                aVarG.d("Content-Length", Long.toString(jA));
                aVarG.g("Transfer-Encoding");
            } else {
                aVarG.d("Transfer-Encoding", "chunked");
                aVarG.g("Content-Length");
            }
        }
        boolean z = false;
        if (yVarB.c("Host") == null) {
            aVarG.d("Host", e.d0.c.m(yVarB.h(), false));
        }
        if (yVarB.c("Connection") == null) {
            aVarG.d("Connection", "Keep-Alive");
        }
        if (yVarB.c("Accept-Encoding") == null && yVarB.c("Range") == null) {
            z = true;
            aVarG.d("Accept-Encoding", "gzip");
        }
        List<l> listA = this.f3394a.a(yVarB.h());
        if (!listA.isEmpty()) {
            aVarG.d("Cookie", b(listA));
        }
        if (yVarB.c("User-Agent") == null) {
            aVarG.d("User-Agent", e.d0.d.a());
        }
        a0 a0VarA = aVar.a(aVarG.b());
        e.e(this.f3394a, yVarB.h(), a0VarA.J());
        a0.a aVarO = a0VarA.M().o(yVarB);
        if (z && "gzip".equalsIgnoreCase(a0VarA.H("Content-Encoding")) && e.c(a0VarA)) {
            f.i iVar = new f.i(a0VarA.c().K());
            s sVarD = a0VarA.J().d().f("Content-Encoding").f("Content-Length").d();
            aVarO.i(sVarD);
            aVarO.b(new h(sVarD, f.k.b(iVar)));
        }
        return aVarO.c();
    }

    public final String b(List<l> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                sb.append("; ");
            }
            l lVar = list.get(i2);
            sb.append(lVar.c());
            sb.append('=');
            sb.append(lVar.k());
        }
        return sb.toString();
    }
}
