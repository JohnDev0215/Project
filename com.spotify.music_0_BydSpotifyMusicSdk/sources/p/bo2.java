package p;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class bo2 extends wn2 {
    public int H;
    public ArrayList F = new ArrayList();
    public boolean G = true;
    public boolean I = false;
    public int J = 0;

    @Override // p.wn2
    public final void A(View view) {
        for (int i = 0; i < this.F.size(); i++) {
            ((wn2) this.F.get(i)).A(view);
        }
        this.n.remove(view);
    }

    @Override // p.wn2
    public final void B(ViewGroup viewGroup) {
        super.B(viewGroup);
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).B(viewGroup);
        }
    }

    @Override // p.wn2
    public final void C() {
        if (this.F.isEmpty()) {
            L();
            p();
            return;
        }
        ao2 ao2Var = new ao2();
        ao2Var.a = this;
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            ((wn2) it.next()).a(ao2Var);
        }
        this.H = this.F.size();
        if (this.G) {
            Iterator it2 = this.F.iterator();
            while (it2.hasNext()) {
                ((wn2) it2.next()).C();
            }
            return;
        }
        for (int i = 1; i < this.F.size(); i++) {
            ((wn2) this.F.get(i - 1)).a(new wl0(this, 2, (wn2) this.F.get(i)));
        }
        wn2 wn2Var = (wn2) this.F.get(0);
        if (wn2Var != null) {
            wn2Var.C();
        }
    }

    @Override // p.wn2
    public final void D(long j) {
        ArrayList arrayList;
        this.k = j;
        if (j < 0 || (arrayList = this.F) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).D(j);
        }
    }

    @Override // p.wn2
    public final void F(q30 q30Var) {
        this.A = q30Var;
        this.J |= 8;
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).F(q30Var);
        }
    }

    @Override // p.wn2
    public final void G(TimeInterpolator timeInterpolator) {
        this.J |= 1;
        ArrayList arrayList = this.F;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((wn2) this.F.get(i)).G(timeInterpolator);
            }
        }
        this.l = timeInterpolator;
    }

    @Override // p.wn2
    public final void H(zb0 zb0Var) {
        super.H(zb0Var);
        this.J |= 4;
        if (this.F != null) {
            for (int i = 0; i < this.F.size(); i++) {
                ((wn2) this.F.get(i)).H(zb0Var);
            }
        }
    }

    @Override // p.wn2
    public final void I() {
        this.J |= 2;
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).I();
        }
    }

    @Override // p.wn2
    public final void K(long j) {
        this.e = j;
    }

    @Override // p.wn2
    public final String M(String str) {
        String strM = super.M(str);
        for (int i = 0; i < this.F.size(); i++) {
            StringBuilder sbF = hf2.f(strM, "\n");
            sbF.append(((wn2) this.F.get(i)).M(str + "  "));
            strM = sbF.toString();
        }
        return strM;
    }

    public final void N(wn2 wn2Var) {
        this.F.add(wn2Var);
        wn2Var.q = this;
        long j = this.k;
        if (j >= 0) {
            wn2Var.D(j);
        }
        if ((this.J & 1) != 0) {
            wn2Var.G(this.l);
        }
        if ((this.J & 2) != 0) {
            wn2Var.I();
        }
        if ((this.J & 4) != 0) {
            wn2Var.H(this.B);
        }
        if ((this.J & 8) != 0) {
            wn2Var.F(this.A);
        }
    }

    @Override // p.wn2
    public final void a(vn2 vn2Var) {
        super.a(vn2Var);
    }

    @Override // p.wn2
    public final void d(View view) {
        for (int i = 0; i < this.F.size(); i++) {
            ((wn2) this.F.get(i)).d(view);
        }
        this.n.add(view);
    }

    @Override // p.wn2
    public final void f(do2 do2Var) {
        if (w(do2Var.b)) {
            for (wn2 wn2Var : this.F) {
                if (wn2Var.w(do2Var.b)) {
                    wn2Var.f(do2Var);
                    do2Var.c.add(wn2Var);
                }
            }
        }
    }

    @Override // p.wn2
    public final void h(do2 do2Var) {
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).h(do2Var);
        }
    }

    @Override // p.wn2
    public final void j(do2 do2Var) {
        if (w(do2Var.b)) {
            for (wn2 wn2Var : this.F) {
                if (wn2Var.w(do2Var.b)) {
                    wn2Var.j(do2Var);
                    do2Var.c.add(wn2Var);
                }
            }
        }
    }

    @Override // p.wn2
    /* JADX INFO: renamed from: m */
    public final wn2 clone() {
        bo2 bo2Var = (bo2) super.clone();
        bo2Var.F = new ArrayList();
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            wn2 wn2VarClone = ((wn2) this.F.get(i)).clone();
            bo2Var.F.add(wn2VarClone);
            wn2VarClone.q = bo2Var;
        }
        return bo2Var;
    }

    @Override // p.wn2
    public final void o(ViewGroup viewGroup, gh2 gh2Var, gh2 gh2Var2, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.e;
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            wn2 wn2Var = (wn2) this.F.get(i);
            if (j > 0 && (this.G || i == 0)) {
                long j2 = wn2Var.e;
                if (j2 > 0) {
                    wn2Var.K(j2 + j);
                } else {
                    wn2Var.K(j);
                }
            }
            wn2Var.o(viewGroup, gh2Var, gh2Var2, arrayList, arrayList2);
        }
    }

    @Override // p.wn2
    public final void y(View view) {
        super.y(view);
        int size = this.F.size();
        for (int i = 0; i < size; i++) {
            ((wn2) this.F.get(i)).y(view);
        }
    }

    @Override // p.wn2
    public final void z(vn2 vn2Var) {
        super.z(vn2Var);
    }
}
