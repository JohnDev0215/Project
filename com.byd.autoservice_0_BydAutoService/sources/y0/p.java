package y0;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Iterator;
import y0.l;

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
        public final /* synthetic */ l f5974a;

        public a(l lVar) {
            this.f5974a = lVar;
        }

        @Override // y0.l.f
        public void e(l lVar) {
            this.f5974a.T();
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    public static class b extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public p f5976a;

        public b(p pVar) {
            this.f5976a = pVar;
        }

        @Override // y0.m, y0.l.f
        public void c(l lVar) {
            p pVar = this.f5976a;
            if (pVar.O) {
                return;
            }
            pVar.a0();
            this.f5976a.O = true;
        }

        @Override // y0.l.f
        public void e(l lVar) {
            p pVar = this.f5976a;
            int i3 = pVar.N - 1;
            pVar.N = i3;
            if (i3 == 0) {
                pVar.O = false;
                pVar.p();
            }
            lVar.P(this);
        }
    }

    @Override // y0.l
    public void N(View view) {
        super.N(view);
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.L.get(i3).N(view);
        }
    }

    @Override // y0.l
    public void R(View view) {
        super.R(view);
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.L.get(i3).R(view);
        }
    }

    @Override // y0.l
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
        for (int i3 = 1; i3 < this.L.size(); i3++) {
            this.L.get(i3 - 1).a(new a(this.L.get(i3)));
        }
        l lVar = this.L.get(0);
        if (lVar != null) {
            lVar.T();
        }
    }

    @Override // y0.l
    public void V(l.e eVar) {
        super.V(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.L.get(i3).V(eVar);
        }
    }

    @Override // y0.l
    public void X(g gVar) {
        super.X(gVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i3 = 0; i3 < this.L.size(); i3++) {
                this.L.get(i3).X(gVar);
            }
        }
    }

    @Override // y0.l
    public void Y(o oVar) {
        super.Y(oVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.L.get(i3).Y(oVar);
        }
    }

    @Override // y0.l
    public String b0(String str) {
        String strB0 = super.b0(str);
        for (int i3 = 0; i3 < this.L.size(); i3++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strB0);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(this.L.get(i3).b0(str + "  "));
            strB0 = sb.toString();
        }
        return strB0;
    }

    @Override // y0.l
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public p a(l.f fVar) {
        return (p) super.a(fVar);
    }

    @Override // y0.l
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public p b(View view) {
        for (int i3 = 0; i3 < this.L.size(); i3++) {
            this.L.get(i3).b(view);
        }
        return (p) super.b(view);
    }

    public p e0(l lVar) {
        f0(lVar);
        long j3 = this.f5939g;
        if (j3 >= 0) {
            lVar.U(j3);
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
        lVar.f5954v = this;
    }

    @Override // y0.l
    public void g(r rVar) {
        if (G(rVar.f5981b)) {
            for (l lVar : this.L) {
                if (lVar.G(rVar.f5981b)) {
                    lVar.g(rVar);
                    rVar.f5982c.add(lVar);
                }
            }
        }
    }

    public l g0(int i3) {
        if (i3 < 0 || i3 >= this.L.size()) {
            return null;
        }
        return this.L.get(i3);
    }

    public int h0() {
        return this.L.size();
    }

    @Override // y0.l
    public void i(r rVar) {
        super.i(rVar);
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.L.get(i3).i(rVar);
        }
    }

    @Override // y0.l
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public p P(l.f fVar) {
        return (p) super.P(fVar);
    }

    @Override // y0.l
    public void j(r rVar) {
        if (G(rVar.f5981b)) {
            for (l lVar : this.L) {
                if (lVar.G(rVar.f5981b)) {
                    lVar.j(rVar);
                    rVar.f5982c.add(lVar);
                }
            }
        }
    }

    @Override // y0.l
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public p Q(View view) {
        for (int i3 = 0; i3 < this.L.size(); i3++) {
            this.L.get(i3).Q(view);
        }
        return (p) super.Q(view);
    }

    @Override // y0.l
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public p U(long j3) {
        ArrayList<l> arrayList;
        super.U(j3);
        if (this.f5939g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.L.get(i3).U(j3);
            }
        }
        return this;
    }

    @Override // y0.l
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public p W(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<l> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.L.get(i3).W(timeInterpolator);
            }
        }
        return (p) super.W(timeInterpolator);
    }

    @Override // y0.l
    /* JADX INFO: renamed from: m */
    public l clone() {
        p pVar = (p) super.clone();
        pVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            pVar.f0(this.L.get(i3).clone());
        }
        return pVar;
    }

    public p m0(int i3) {
        if (i3 == 0) {
            this.M = true;
        } else {
            if (i3 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i3);
            }
            this.M = false;
        }
        return this;
    }

    @Override // y0.l
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public p Z(long j3) {
        return (p) super.Z(j3);
    }

    @Override // y0.l
    public void o(ViewGroup viewGroup, s sVar, s sVar2, ArrayList<r> arrayList, ArrayList<r> arrayList2) {
        long jY = y();
        int size = this.L.size();
        for (int i3 = 0; i3 < size; i3++) {
            l lVar = this.L.get(i3);
            if (jY > 0 && (this.M || i3 == 0)) {
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
