package a.n;

import a.n.m;
import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class q extends m {
    int N;
    private ArrayList<m> L = new ArrayList<>();
    private boolean M = true;
    boolean O = false;
    private int P = 0;

    class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ m f584a;

        a(m mVar) {
            this.f584a = mVar;
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            this.f584a.T();
            mVar.P(this);
        }
    }

    static class b extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        q f586a;

        b(q qVar) {
            this.f586a = qVar;
        }

        @Override // a.n.n, a.n.m.f
        public void c(m mVar) {
            q qVar = this.f586a;
            if (qVar.O) {
                return;
            }
            qVar.a0();
            this.f586a.O = true;
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            q qVar = this.f586a;
            int i = qVar.N - 1;
            qVar.N = i;
            if (i == 0) {
                qVar.O = false;
                qVar.p();
            }
            mVar.P(this);
        }
    }

    private void f0(m mVar) {
        this.L.add(mVar);
        mVar.v = this;
    }

    private void o0() {
        b bVar = new b(this);
        Iterator<m> it = this.L.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.N = this.L.size();
    }

    @Override // a.n.m
    public void N(View view) {
        super.N(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).N(view);
        }
    }

    @Override // a.n.m
    public void R(View view) {
        super.R(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).R(view);
        }
    }

    @Override // a.n.m
    protected void T() {
        if (this.L.isEmpty()) {
            a0();
            p();
            return;
        }
        o0();
        if (this.M) {
            Iterator<m> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().T();
            }
            return;
        }
        for (int i = 1; i < this.L.size(); i++) {
            this.L.get(i - 1).a(new a(this.L.get(i)));
        }
        m mVar = this.L.get(0);
        if (mVar != null) {
            mVar.T();
        }
    }

    @Override // a.n.m
    public void V(m.e eVar) {
        super.V(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).V(eVar);
        }
    }

    @Override // a.n.m
    public void X(g gVar) {
        super.X(gVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i = 0; i < this.L.size(); i++) {
                this.L.get(i).X(gVar);
            }
        }
    }

    @Override // a.n.m
    public void Y(p pVar) {
        super.Y(pVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).Y(pVar);
        }
    }

    @Override // a.n.m
    String b0(String str) {
        String strB0 = super.b0(str);
        for (int i = 0; i < this.L.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strB0);
            sb.append("\n");
            sb.append(this.L.get(i).b0(str + "  "));
            strB0 = sb.toString();
        }
        return strB0;
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public q a(m.f fVar) {
        return (q) super.a(fVar);
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public q b(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).b(view);
        }
        return (q) super.b(view);
    }

    public q e0(m mVar) {
        f0(mVar);
        long j = this.g;
        if (j >= 0) {
            mVar.U(j);
        }
        if ((this.P & 1) != 0) {
            mVar.W(s());
        }
        if ((this.P & 2) != 0) {
            mVar.Y(w());
        }
        if ((this.P & 4) != 0) {
            mVar.X(v());
        }
        if ((this.P & 8) != 0) {
            mVar.V(r());
        }
        return this;
    }

    @Override // a.n.m
    public void g(s sVar) {
        if (G(sVar.f591b)) {
            for (m mVar : this.L) {
                if (mVar.G(sVar.f591b)) {
                    mVar.g(sVar);
                    sVar.f592c.add(mVar);
                }
            }
        }
    }

    public m g0(int i) {
        if (i < 0 || i >= this.L.size()) {
            return null;
        }
        return this.L.get(i);
    }

    public int h0() {
        return this.L.size();
    }

    @Override // a.n.m
    void i(s sVar) {
        super.i(sVar);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).i(sVar);
        }
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public q P(m.f fVar) {
        return (q) super.P(fVar);
    }

    @Override // a.n.m
    public void j(s sVar) {
        if (G(sVar.f591b)) {
            for (m mVar : this.L) {
                if (mVar.G(sVar.f591b)) {
                    mVar.j(sVar);
                    sVar.f592c.add(mVar);
                }
            }
        }
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public q Q(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).Q(view);
        }
        return (q) super.Q(view);
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public q U(long j) {
        ArrayList<m> arrayList;
        super.U(j);
        if (this.g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).U(j);
            }
        }
        return this;
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public q W(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<m> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).W(timeInterpolator);
            }
        }
        return (q) super.W(timeInterpolator);
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: m */
    public m clone() {
        q qVar = (q) super.clone();
        qVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            qVar.f0(this.L.get(i).clone());
        }
        return qVar;
    }

    public q m0(int i) {
        if (i == 0) {
            this.M = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
            }
            this.M = false;
        }
        return this;
    }

    @Override // a.n.m
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public q Z(long j) {
        return (q) super.Z(j);
    }

    @Override // a.n.m
    protected void o(ViewGroup viewGroup, t tVar, t tVar2, ArrayList<s> arrayList, ArrayList<s> arrayList2) {
        long jY = y();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            m mVar = this.L.get(i);
            if (jY > 0 && (this.M || i == 0)) {
                long jY2 = mVar.y();
                if (jY2 > 0) {
                    mVar.Z(jY2 + jY);
                } else {
                    mVar.Z(jY);
                }
            }
            mVar.o(viewGroup, tVar, tVar2, arrayList, arrayList2);
        }
    }
}
