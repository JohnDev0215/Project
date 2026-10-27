package g6;

import A7.m;
import K5.F;
import S8.AbstractC0727y;
import S8.E;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import h6.n;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import n7.u;

/* JADX INFO: renamed from: g6.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1310h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f14994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f14995b;
    public final N c;
    public final List d;

    public C1310h(F f10) {
        m.f(f10, "songsRepository");
        this.f14994a = f10;
        u uVar = u.l;
        f0 f0VarC = AbstractC0828q.c(uVar);
        this.f14995b = f0VarC;
        this.c = new N(f0VarC);
        Z8.e eVar = E.f8084a;
        AbstractC0727y.a(X8.m.f10573a);
        this.d = uVar;
    }

    public static final InputStream a(C1310h c1310h, String str) {
        c1310h.getClass();
        try {
            URLConnection uRLConnectionOpenConnection = new URL("https://dev-spayre-karaoke.s3.amazonaws.com/".concat(str)).openConnection();
            m.d(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            return ((HttpURLConnection) uRLConnectionOpenConnection).getInputStream();
        } catch (Exception e10) {
            n.i("S3Download", "Error getting inputStream file", e10);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00a1 -> B:27:0x00a4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(s7.AbstractC2410c r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.C1310h.b(s7.c):java.lang.Object");
    }
}
