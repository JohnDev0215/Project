package i6;

import c6.a0;
import c6.b0;
import c6.c0;
import c6.m;
import c6.n;
import c6.v;
import c6.w;
import c6.z;
import h5.p;
import java.util.List;
import kotlin.jvm.internal.l;
import p6.o;

/* JADX INFO: compiled from: BridgeInterceptor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f7044a;

    public a(n cookieJar) {
        l.e(cookieJar, "cookieJar");
        this.f7044a = cookieJar;
    }

    private final String b(List<m> list) {
        StringBuilder sb = new StringBuilder();
        int i7 = 0;
        for (Object obj : list) {
            int i8 = i7 + 1;
            if (i7 < 0) {
                p.o();
            }
            m mVar = (m) obj;
            if (i7 > 0) {
                sb.append("; ");
            }
            sb.append(mVar.e());
            sb.append('=');
            sb.append(mVar.g());
            i7 = i8;
        }
        String string = sb.toString();
        l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @Override // c6.v
    public b0 a(v.a chain) {
        c0 c0VarM;
        l.e(chain, "chain");
        z zVarA = chain.a();
        z.a aVarH = zVarA.h();
        a0 a0VarA = zVarA.a();
        if (a0VarA != null) {
            w wVarB = a0VarA.b();
            if (wVarB != null) {
                aVarH.c("Content-Type", wVarB.toString());
            }
            long jA = a0VarA.a();
            if (jA != -1) {
                aVarH.c("Content-Length", String.valueOf(jA));
                aVarH.f("Transfer-Encoding");
            } else {
                aVarH.c("Transfer-Encoding", "chunked");
                aVarH.f("Content-Length");
            }
        }
        boolean z6 = false;
        if (zVarA.d("Host") == null) {
            aVarH.c("Host", d6.b.L(zVarA.i(), false, 1, null));
        }
        if (zVarA.d("Connection") == null) {
            aVarH.c("Connection", "Keep-Alive");
        }
        if (zVarA.d("Accept-Encoding") == null && zVarA.d("Range") == null) {
            aVarH.c("Accept-Encoding", "gzip");
            z6 = true;
        }
        List<m> listB = this.f7044a.b(zVarA.i());
        if (!listB.isEmpty()) {
            aVarH.c("Cookie", b(listB));
        }
        if (zVarA.d("User-Agent") == null) {
            aVarH.c("User-Agent", "okhttp/4.9.2");
        }
        b0 b0VarB = chain.b(aVarH.b());
        e.f(this.f7044a, zVarA.i(), b0VarB.V());
        b0.a aVarR = b0VarB.c0().r(zVarA);
        if (z6 && y5.p.o("gzip", b0.U(b0VarB, "Content-Encoding", null, 2, null), true) && e.b(b0VarB) && (c0VarM = b0VarB.m()) != null) {
            p6.l lVar = new p6.l(c0VarM.A());
            aVarR.k(b0VarB.V().e().f("Content-Encoding").f("Content-Length").d());
            aVarR.b(new h(b0.U(b0VarB, "Content-Type", null, 2, null), -1L, o.b(lVar)));
        }
        return aVarR.c();
    }
}
