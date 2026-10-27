package g9;

import c9.C1040a;
import c9.J;
import c9.q;
import c9.v;
import c9.w;
import j9.z;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f15046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1040a f15047b;
    public final j c;
    public final q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public E4.b f15048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v f15049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15050g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f15051h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15052i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public J f15053j;

    public f(n nVar, C1040a c1040a, j jVar, q qVar) {
        A7.m.f(nVar, "connectionPool");
        A7.m.f(jVar, "call");
        A7.m.f(qVar, "eventListener");
        this.f15046a = nVar;
        this.f15047b = c1040a;
        this.c = jVar;
        this.d = qVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:182:0x02f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Type inference failed for: r7v14, types: [java.lang.Object, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final g9.m a(int r16, int r17, int r18, boolean r19, boolean r20) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 922
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g9.f.a(int, int, int, boolean, boolean):g9.m");
    }

    public final boolean b(w wVar) {
        A7.m.f(wVar, "url");
        w wVar2 = this.f15047b.f12540h;
        return wVar.f12621e == wVar2.f12621e && A7.m.a(wVar.d, wVar2.d);
    }

    public final void c(IOException iOException) {
        A7.m.f(iOException, "e");
        this.f15053j = null;
        if ((iOException instanceof z) && ((z) iOException).l == 8) {
            this.f15050g++;
        } else if (iOException instanceof j9.a) {
            this.f15051h++;
        } else {
            this.f15052i++;
        }
    }
}
