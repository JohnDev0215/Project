package g.a.a;

import java.io.IOException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class o1 extends r {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3937d;

    public o1() {
        this.f3937d = -1;
    }

    public o1(e eVar) {
        super(eVar);
        this.f3937d = -1;
    }

    public o1(f fVar) {
        super(fVar);
        this.f3937d = -1;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        p pVarB = pVar.b();
        int iT = t();
        pVar.c(48);
        pVar.i(iT);
        Enumeration enumerationR = r();
        while (enumerationR.hasMoreElements()) {
            pVarB.j((e) enumerationR.nextElement());
        }
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int iT = t();
        return y1.a(iT) + 1 + iT;
    }

    public final int t() throws IOException {
        if (this.f3937d < 0) {
            int i2 = 0;
            Enumeration enumerationR = r();
            while (enumerationR.hasMoreElements()) {
                i2 += ((e) enumerationR.nextElement()).c().m().i();
            }
            this.f3937d = i2;
        }
        return this.f3937d;
    }
}
