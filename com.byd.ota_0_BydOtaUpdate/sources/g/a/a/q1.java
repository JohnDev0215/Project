package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class q1 extends x {
    public static final byte[] y = new byte[0];

    public q1(boolean z, int i2, e eVar) {
        super(z, i2, eVar);
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        if (this.f4166d) {
            pVar.f(160, this.f4165c, y);
            return;
        }
        q qVarM = this.x.c().m();
        if (!this.s) {
            pVar.k(qVarM.k() ? 160 : 128, this.f4165c);
            pVar.h(qVarM);
        } else {
            pVar.k(160, this.f4165c);
            pVar.i(qVarM.i());
            pVar.j(qVarM);
        }
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int iB;
        if (this.f4166d) {
            return y1.b(this.f4165c) + 1;
        }
        int i2 = this.x.c().m().i();
        if (this.s) {
            iB = y1.b(this.f4165c) + y1.a(i2);
        } else {
            i2--;
            iB = y1.b(this.f4165c);
        }
        return iB + i2;
    }

    @Override // g.a.a.q
    public boolean k() {
        if (this.f4166d || this.s) {
            return true;
        }
        return this.x.c().m().k();
    }
}
