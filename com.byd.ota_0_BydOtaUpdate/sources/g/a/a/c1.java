package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class c1 extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3786d;

    public c1() {
        this.f3786d = -1;
    }

    public c1(f fVar) {
        super(fVar);
        this.f3786d = -1;
    }

    public c1(e[] eVarArr) {
        super(eVarArr);
        this.f3786d = -1;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        p pVarA = pVar.a();
        int iT = t();
        pVar.c(48);
        pVar.i(iT);
        Enumeration enumerationR = r();
        while (enumerationR.hasMoreElements()) {
            pVarA.j((e) enumerationR.nextElement());
        }
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int iT = t();
        return y1.a(iT) + 1 + iT;
    }

    public final int t() throws IOException {
        if (this.f3786d < 0) {
            int i2 = 0;
            Enumeration enumerationR = r();
            while (enumerationR.hasMoreElements()) {
                i2 += ((e) enumerationR.nextElement()).c().l().i();
            }
            this.f3786d = i2;
        }
        return this.f3786d;
    }
}
