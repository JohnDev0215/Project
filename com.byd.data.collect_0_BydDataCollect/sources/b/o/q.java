package b.o;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import b.o.m;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends m {
    int N;
    private ArrayList<m> L = new ArrayList<>();
    private boolean M = true;
    boolean O = false;
    private int P = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    class a extends n {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ m f2913a;

        a(m mVar) {
            this.f2913a = mVar;
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            this.f2913a.X();
            mVar.T(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    static class b extends n {

        /* JADX INFO: renamed from: a */
        q f2915a;

        b(q qVar) {
            this.f2915a = qVar;
        }

        @Override // b.o.n, b.o.m.f
        public void c(m mVar) {
            q qVar = this.f2915a;
            if (qVar.O) {
                return;
            }
            qVar.e0();
            this.f2915a.O = true;
        }

        @Override // b.o.m.f
        public void e(m mVar) {
            q qVar = this.f2915a;
            int i2 = qVar.N - 1;
            qVar.N = i2;
            if (i2 == 0) {
                qVar.O = false;
                qVar.r();
            }
            mVar.T(this);
        }
    }

    private void j0(m mVar) {
        this.L.add(mVar);
        mVar.v = this;
    }

    private void s0() {
        b bVar = new b(this);
        Iterator<m> it = this.L.iterator();
        while (it.hasNext()) {
            it.next().b(bVar);
        }
        this.N = this.L.size();
    }

    @Override // b.o.m
    public void R(View view) {
        super.R(view);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).R(view);
        }
    }

    @Override // b.o.m
    public void V(View view) {
        super.V(view);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).V(view);
        }
    }

    @Override // b.o.m
    protected void X() {
        if (this.L.isEmpty()) {
            e0();
            r();
            return;
        }
        s0();
        if (this.M) {
            Iterator<m> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().X();
            }
            return;
        }
        for (int i2 = 1; i2 < this.L.size(); i2++) {
            this.L.get(i2 - 1).b(new a(this.L.get(i2)));
        }
        m mVar = this.L.get(0);
        if (mVar != null) {
            mVar.X();
        }
    }

    @Override // b.o.m
    public void Z(m.e eVar) {
        super.Z(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).Z(eVar);
        }
    }

    @Override // b.o.m
    public void b0(g gVar) {
        super.b0(gVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i2 = 0; i2 < this.L.size(); i2++) {
                this.L.get(i2).b0(gVar);
            }
        }
    }

    @Override // b.o.m
    public void c0(p pVar) {
        super.c0(pVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).c0(pVar);
        }
    }

    @Override // b.o.m
    protected void cancel() {
        super.cancel();
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).cancel();
        }
    }

    @Override // b.o.m
    String f0(String str) {
        String strF0 = super.f0(str);
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strF0);
            sb.append("\n");
            sb.append(this.L.get(i2).f0(str + "  "));
            strF0 = sb.toString();
        }
        return strF0;
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: g0 */
    public q b(m.f fVar) {
        return (q) super.b(fVar);
    }

    @Override // b.o.m
    public void h(s sVar) {
        if (K(sVar.f2920b)) {
            for (m mVar : this.L) {
                if (mVar.K(sVar.f2920b)) {
                    mVar.h(sVar);
                    sVar.f2921c.add(mVar);
                }
            }
        }
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: h0 */
    public q c(View view) {
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            this.L.get(i2).c(view);
        }
        return (q) super.c(view);
    }

    public q i0(m mVar) {
        j0(mVar);
        long j = this.f2895g;
        if (j >= 0) {
            mVar.Y(j);
        }
        if ((this.P & 1) != 0) {
            mVar.a0(u());
        }
        if ((this.P & 2) != 0) {
            mVar.c0(z());
        }
        if ((this.P & 4) != 0) {
            mVar.b0(x());
        }
        if ((this.P & 8) != 0) {
            mVar.Z(t());
        }
        return this;
    }

    @Override // b.o.m
    void k(s sVar) {
        super.k(sVar);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).k(sVar);
        }
    }

    public m k0(int i2) {
        if (i2 < 0 || i2 >= this.L.size()) {
            return null;
        }
        return this.L.get(i2);
    }

    @Override // b.o.m
    public void l(s sVar) {
        if (K(sVar.f2920b)) {
            for (m mVar : this.L) {
                if (mVar.K(sVar.f2920b)) {
                    mVar.l(sVar);
                    sVar.f2921c.add(mVar);
                }
            }
        }
    }

    public int l0() {
        return this.L.size();
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: m0 */
    public q T(m.f fVar) {
        return (q) super.T(fVar);
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: n0 */
    public q U(View view) {
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            this.L.get(i2).U(view);
        }
        return (q) super.U(view);
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: o */
    public m clone() {
        q qVar = (q) super.clone();
        qVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            qVar.j0(this.L.get(i2).clone());
        }
        return qVar;
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: o0 */
    public q Y(long j) {
        ArrayList<m> arrayList;
        super.Y(j);
        if (this.f2895g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.L.get(i2).Y(j);
            }
        }
        return this;
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: p0 */
    public q a0(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<m> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.L.get(i2).a0(timeInterpolator);
            }
        }
        return (q) super.a0(timeInterpolator);
    }

    @Override // b.o.m
    protected void q(ViewGroup viewGroup, t tVar, t tVar2, ArrayList<s> arrayList, ArrayList<s> arrayList2) {
        long jB = B();
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            m mVar = this.L.get(i2);
            if (jB > 0 && (this.M || i2 == 0)) {
                long jB2 = mVar.B();
                if (jB2 > 0) {
                    mVar.d0(jB2 + jB);
                } else {
                    mVar.d0(jB);
                }
            }
            mVar.q(viewGroup, tVar, tVar2, arrayList, arrayList2);
        }
    }

    public q q0(int i2) {
        if (i2 == 0) {
            this.M = true;
        } else {
            if (i2 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
            }
            this.M = false;
        }
        return this;
    }

    @Override // b.o.m
    /* JADX INFO: renamed from: r0 */
    public q d0(long j) {
        return (q) super.d0(j);
    }
}
