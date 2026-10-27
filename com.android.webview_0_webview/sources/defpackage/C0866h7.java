package defpackage;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: h7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0866h7 extends Z6 {
    public int c0;
    public ArrayList a0 = new ArrayList();
    public boolean b0 = true;
    public boolean d0 = false;
    public int e0 = 0;

    @Override // defpackage.Z6
    public void A(X6 x6) {
        this.V = x6;
        this.e0 |= 8;
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((Z6) this.a0.get(i)).A(x6);
        }
    }

    @Override // defpackage.Z6
    public Z6 B(TimeInterpolator timeInterpolator) {
        this.e0 |= 1;
        ArrayList arrayList = this.a0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Z6) this.a0.get(i)).B(timeInterpolator);
            }
        }
        this.v = timeInterpolator;
        return this;
    }

    @Override // defpackage.Z6
    public void C(Q6 q6) {
        if (q6 == null) {
            this.W = Z6.Y;
        } else {
            this.W = q6;
        }
        this.e0 |= 4;
        for (int i = 0; i < this.a0.size(); i++) {
            ((Z6) this.a0.get(i)).C(q6);
        }
    }

    @Override // defpackage.Z6
    public void D(AbstractC0698e7 abstractC0698e7) {
        this.U = abstractC0698e7;
        this.e0 |= 2;
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((Z6) this.a0.get(i)).D(abstractC0698e7);
        }
    }

    @Override // defpackage.Z6
    public Z6 E(long j) {
        this.t = j;
        return this;
    }

    @Override // defpackage.Z6
    public String G(String str) {
        String strG = super.G(str);
        for (int i = 0; i < this.a0.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strG);
            sb.append("\n");
            sb.append(((Z6) this.a0.get(i)).G(str + "  "));
            strG = sb.toString();
        }
        return strG;
    }

    public C0866h7 H(Z6 z6) {
        this.a0.add(z6);
        z6.f12J = this;
        long j = this.u;
        if (j >= 0) {
            z6.z(j);
        }
        if ((this.e0 & 1) != 0) {
            z6.B(this.v);
        }
        if ((this.e0 & 2) != 0) {
            z6.D(null);
        }
        if ((this.e0 & 4) != 0) {
            z6.C(this.W);
        }
        if ((this.e0 & 8) != 0) {
            z6.A(this.V);
        }
        return this;
    }

    public Z6 I(int i) {
        if (i < 0 || i >= this.a0.size()) {
            return null;
        }
        return (Z6) this.a0.get(i);
    }

    public C0866h7 J(int i) {
        if (i == 0) {
            this.b0 = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException(Z7.a("Invalid parameter for TransitionSet ordering: ", i));
            }
            this.b0 = false;
        }
        return this;
    }

    @Override // defpackage.Z6
    public Z6 a(Y6 y6) {
        super.a(y6);
        return this;
    }

    @Override // defpackage.Z6
    public Z6 b(View view) {
        for (int i = 0; i < this.a0.size(); i++) {
            ((Z6) this.a0.get(i)).b(view);
        }
        this.x.add(view);
        return this;
    }

    @Override // defpackage.Z6
    public void d(C0977j7 c0977j7) {
        if (s(c0977j7.b)) {
            for (Z6 z6 : this.a0) {
                if (z6.s(c0977j7.b)) {
                    z6.d(c0977j7);
                    c0977j7.c.add(z6);
                }
            }
        }
    }

    @Override // defpackage.Z6
    public void f(C0977j7 c0977j7) {
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((Z6) this.a0.get(i)).f(c0977j7);
        }
    }

    @Override // defpackage.Z6
    public void g(C0977j7 c0977j7) {
        if (s(c0977j7.b)) {
            for (Z6 z6 : this.a0) {
                if (z6.s(c0977j7.b)) {
                    z6.g(c0977j7);
                    c0977j7.c.add(z6);
                }
            }
        }
    }

    @Override // defpackage.Z6
    /* JADX INFO: renamed from: j */
    public Z6 clone() {
        C0866h7 c0866h7 = (C0866h7) super.clone();
        c0866h7.a0 = new ArrayList();
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            c0866h7.H(((Z6) this.a0.get(i)).clone());
        }
        return c0866h7;
    }

    @Override // defpackage.Z6
    public void l(ViewGroup viewGroup, C1033k7 c1033k7, C1033k7 c1033k72, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.t;
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            Z6 z6 = (Z6) this.a0.get(i);
            if (j > 0 && (this.b0 || i == 0)) {
                long j2 = z6.t;
                if (j2 > 0) {
                    z6.E(j2 + j);
                } else {
                    z6.E(j);
                }
            }
            z6.l(viewGroup, c1033k7, c1033k72, arrayList, arrayList2);
        }
    }

    @Override // defpackage.Z6
    public void u(View view) {
        super.u(view);
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((Z6) this.a0.get(i)).u(view);
        }
    }

    @Override // defpackage.Z6
    public Z6 v(Y6 y6) {
        super.v(y6);
        return this;
    }

    @Override // defpackage.Z6
    public Z6 w(View view) {
        for (int i = 0; i < this.a0.size(); i++) {
            ((Z6) this.a0.get(i)).w(view);
        }
        this.x.remove(view);
        return this;
    }

    @Override // defpackage.Z6
    public void x(View view) {
        super.x(view);
        int size = this.a0.size();
        for (int i = 0; i < size; i++) {
            ((Z6) this.a0.get(i)).x(view);
        }
    }

    @Override // defpackage.Z6
    public void y() {
        if (this.a0.isEmpty()) {
            F();
            m();
            return;
        }
        C0810g7 c0810g7 = new C0810g7(this);
        Iterator it = this.a0.iterator();
        while (it.hasNext()) {
            ((Z6) it.next()).a(c0810g7);
        }
        this.c0 = this.a0.size();
        if (this.b0) {
            Iterator it2 = this.a0.iterator();
            while (it2.hasNext()) {
                ((Z6) it2.next()).y();
            }
            return;
        }
        for (int i = 1; i < this.a0.size(); i++) {
            ((Z6) this.a0.get(i - 1)).a(new C0754f7(this, (Z6) this.a0.get(i)));
        }
        Z6 z6 = (Z6) this.a0.get(0);
        if (z6 != null) {
            z6.y();
        }
    }

    @Override // defpackage.Z6
    public Z6 z(long j) {
        this.u = j;
        if (j >= 0) {
            int size = this.a0.size();
            for (int i = 0; i < size; i++) {
                ((Z6) this.a0.get(i)).z(j);
            }
        }
        return this;
    }
}
