package f.b0.g;

import f.l;
import f.m;
import f.q;
import f.r;
import f.s;
import f.w;
import f.x;
import f.y;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: BridgeInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f5073a;

    public a(m mVar) {
        this.f5073a = mVar;
    }

    @Override // f.r
    public y a(r.a aVar) throws IOException {
        w wVarB = aVar.b();
        w.a aVarG = wVarB.g();
        x xVarA = wVarB.a();
        if (xVarA != null) {
            s sVarContentType = xVarA.contentType();
            if (sVarContentType != null) {
                aVarG.d("Content-Type", sVarContentType.toString());
            }
            long jContentLength = xVarA.contentLength();
            if (jContentLength != -1) {
                aVarG.d("Content-Length", Long.toString(jContentLength));
                aVarG.h("Transfer-Encoding");
            } else {
                aVarG.d("Transfer-Encoding", "chunked");
                aVarG.h("Content-Length");
            }
        }
        boolean z = false;
        if (wVarB.c("Host") == null) {
            aVarG.d("Host", f.b0.c.m(wVarB.h(), false));
        }
        if (wVarB.c("Connection") == null) {
            aVarG.d("Connection", "Keep-Alive");
        }
        if (wVarB.c("Accept-Encoding") == null && wVarB.c("Range") == null) {
            z = true;
            aVarG.d("Accept-Encoding", "gzip");
        }
        List<l> listA = this.f5073a.a(wVarB.h());
        if (!listA.isEmpty()) {
            aVarG.d("Cookie", b(listA));
        }
        if (wVarB.c("User-Agent") == null) {
            aVarG.d("User-Agent", f.b0.d.a());
        }
        y yVarA = aVar.a(aVarG.b());
        e.e(this.f5073a, wVarB.h(), yVarA.S());
        y.a aVarO = yVarA.U().o(wVarB);
        if (z && "gzip".equalsIgnoreCase(yVarA.Q("Content-Encoding")) && e.c(yVarA)) {
            g.h hVar = new g.h(yVarA.w().S());
            q qVarD = yVarA.S().d().f("Content-Encoding").f("Content-Length").d();
            aVarO.i(qVarD);
            aVarO.b(new h(qVarD, g.j.b(hVar)));
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
