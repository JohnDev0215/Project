package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import r5.C2297a;
import r5.C2298b;
import x.AbstractC2641j;

/* JADX INFO: renamed from: m5.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1835l extends j5.y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1834k f19229b = new C1834k(new C1835l(j5.x.f17030m), 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j5.x f19230a;

    public C1835l(j5.x xVar) {
        this.f19230a = xVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        int iQ = c2297a.Q();
        int iC = AbstractC2641j.c(iQ);
        if (iC == 5 || iC == 6) {
            return this.f19230a.a(c2297a);
        }
        if (iC == 8) {
            c2297a.M();
            return null;
        }
        throw new j5.p("Expecting number, got: " + AbstractC1347d.v(iQ) + "; at path " + c2297a.q(false));
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        c2298b.K((Number) obj);
    }
}
