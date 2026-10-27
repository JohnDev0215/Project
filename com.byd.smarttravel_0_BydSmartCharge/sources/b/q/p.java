package b.q;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import b.q.l;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends l {
    public int N;
    public ArrayList<l> L = new ArrayList<>();
    public boolean M = true;
    public boolean O = false;
    public int P = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    public class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ l f1506a;

        public a(l lVar) {
            this.f1506a = lVar;
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            this.f1506a.T();
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    public static class b extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public p f1508a;

        public b(p pVar) {
            this.f1508a = pVar;
        }

        @Override // b.q.m, b.q.l.f
        public void c(l lVar) {
            p pVar = this.f1508a;
            if (pVar.O) {
                return;
            }
            pVar.a0();
            this.f1508a.O = true;
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            p pVar = this.f1508a;
            int i = pVar.N - 1;
            pVar.N = i;
            if (i == 0) {
                pVar.O = false;
                pVar.p();
            }
            lVar.P(this);
        }
    }

    @Override // b.q.l
    public void N(View view) {
        super.N(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).N(view);
        }
    }

    @Override // b.q.l
    public void R(View view) {
        super.R(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).R(view);
        }
    }

    @Override // b.q.l
    public void T() {
        if (this.L.isEmpty()) {
            a0();
            p();
            return;
        }
        o0();
        if (this.M) {
            Iterator<l> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().T();
            }
            return;
        }
        for (int i = 1; i < this.L.size(); i++) {
            this.L.get(i - 1).a(new a(this.L.get(i)));
        }
        l lVar = this.L.get(0);
        if (lVar != null) {
            lVar.T();
        }
    }

    @Override // b.q.l
    public void V(l.e eVar) {
        super.V(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).V(eVar);
        }
    }

    @Override // b.q.l
    public void X(g gVar) {
        super.X(gVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i = 0; i < this.L.size(); i++) {
                this.L.get(i).X(gVar);
            }
        }
    }

    @Override // b.q.l
    public void Y(o oVar) {
        super.Y(oVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).Y(oVar);
        }
    }

    @Override // b.q.l
    public String b0(String str) {
        String strB0 = super.b0(str);
        for (int i = 0; i < this.L.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strB0);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(this.L.get(i).b0(str + "  "));
            strB0 = sb.toString();
        }
        return strB0;
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public p a(l.f fVar) {
        return (p) super.a(fVar);
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public p b(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).b(view);
        }
        return (p) super.b(view);
    }

    public p e0(l lVar) {
        f0(lVar);
        long j = this.g;
        if (j >= 0) {
            lVar.U(j);
        }
        if ((this.P & 1) != 0) {
            lVar.W(s());
        }
        if ((this.P & 2) != 0) {
            lVar.Y(w());
        }
        if ((this.P & 4) != 0) {
            lVar.X(v());
        }
        if ((this.P & 8) != 0) {
            lVar.V(r());
        }
        return this;
    }

    public final void f0(l lVar) {
        this.L.add(lVar);
        lVar.v = this;
    }

    @Override // b.q.l
    public void g(r rVar) {
        if (G(rVar.f1513b)) {
            for (l lVar : this.L) {
                if (lVar.G(rVar.f1513b)) {
                    lVar.g(rVar);
                    rVar.f1514c.add(lVar);
                }
            }
        }
    }

    public l g0(int i) {
        if (i < 0 || i >= this.L.size()) {
            return null;
        }
        return this.L.get(i);
    }

    public int h0() {
        return this.L.size();
    }

    @Override // b.q.l
    public void i(r rVar) {
        super.i(rVar);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).i(rVar);
        }
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public p P(l.f fVar) {
        return (p) super.P(fVar);
    }

    @Override // b.q.l
    public void j(r rVar) {
        if (G(rVar.f1513b)) {
            for (l lVar : this.L) {
                if (lVar.G(rVar.f1513b)) {
                    lVar.j(rVar);
                    rVar.f1514c.add(lVar);
                }
            }
        }
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public p Q(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).Q(view);
        }
        return (p) super.Q(view);
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public p U(long j) {
        ArrayList<l> arrayList;
        super.U(j);
        if (this.g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).U(j);
            }
        }
        return this;
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public p W(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<l> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).W(timeInterpolator);
            }
        }
        return (p) super.W(timeInterpolator);
    }

    @Override // b.q.l
    /* JADX INFO: renamed from: m */
    public l clone() {
        p pVar = (p) super.clone();
        pVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            pVar.f0(this.L.get(i).clone());
        }
        return pVar;
    }

    public p m0(int i) {
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

    @Override // b.q.l
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public p Z(long j) {
        return (p) super.Z(j);
    }

    @Override // b.q.l
    public void o(ViewGroup viewGroup, s sVar, s sVar2, ArrayList<r> arrayList, ArrayList<r> arrayList2) {
        long jY = y();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            l lVar = this.L.get(i);
            if (jY > 0 && (this.M || i == 0)) {
                long jY2 = lVar.y();
                if (jY2 > 0) {
                    lVar.Z(jY2 + jY);
                } else {
                    lVar.Z(jY);
                }
            }
            lVar.o(viewGroup, sVar, sVar2, arrayList, arrayList2);
        }
    }

    public final void o0() {
        b bVar = new b(this);
        Iterator<l> it = this.L.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.N = this.L.size();
    }
}
