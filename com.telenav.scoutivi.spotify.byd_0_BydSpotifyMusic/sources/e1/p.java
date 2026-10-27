package e1;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import e1.l;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends l {
    int Q;
    private ArrayList<l> O = new ArrayList<>();
    private boolean P = true;
    boolean R = false;
    private int S = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    class a extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ l f6154a;

        a(l lVar) {
            this.f6154a = lVar;
        }

        @Override // e1.l.f
        public void e(l lVar) {
            this.f6154a.T();
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    static class b extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        p f6156a;

        b(p pVar) {
            this.f6156a = pVar;
        }

        @Override // e1.m, e1.l.f
        public void a(l lVar) {
            p pVar = this.f6156a;
            if (pVar.R) {
                return;
            }
            pVar.a0();
            this.f6156a.R = true;
        }

        @Override // e1.l.f
        public void e(l lVar) {
            p pVar = this.f6156a;
            int i7 = pVar.Q - 1;
            pVar.Q = i7;
            if (i7 == 0) {
                pVar.R = false;
                pVar.p();
            }
            lVar.P(this);
        }
    }

    private void f0(l lVar) {
        this.O.add(lVar);
        lVar.f6135w = this;
    }

    private void o0() {
        b bVar = new b(this);
        Iterator<l> it = this.O.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.Q = this.O.size();
    }

    @Override // e1.l
    public void N(View view) {
        super.N(view);
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).N(view);
        }
    }

    @Override // e1.l
    public void R(View view) {
        super.R(view);
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).R(view);
        }
    }

    @Override // e1.l
    protected void T() {
        if (this.O.isEmpty()) {
            a0();
            p();
            return;
        }
        o0();
        if (this.P) {
            Iterator<l> it = this.O.iterator();
            while (it.hasNext()) {
                it.next().T();
            }
            return;
        }
        for (int i7 = 1; i7 < this.O.size(); i7++) {
            this.O.get(i7 - 1).a(new a(this.O.get(i7)));
        }
        l lVar = this.O.get(0);
        if (lVar != null) {
            lVar.T();
        }
    }

    @Override // e1.l
    public void V(l.e eVar) {
        super.V(eVar);
        this.S |= 8;
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).V(eVar);
        }
    }

    @Override // e1.l
    public void X(g gVar) {
        super.X(gVar);
        this.S |= 4;
        if (this.O != null) {
            for (int i7 = 0; i7 < this.O.size(); i7++) {
                this.O.get(i7).X(gVar);
            }
        }
    }

    @Override // e1.l
    public void Y(o oVar) {
        super.Y(oVar);
        this.S |= 2;
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).Y(oVar);
        }
    }

    @Override // e1.l
    String b0(String str) {
        String strB0 = super.b0(str);
        for (int i7 = 0; i7 < this.O.size(); i7++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strB0);
            sb.append("\n");
            sb.append(this.O.get(i7).b0(str + "  "));
            strB0 = sb.toString();
        }
        return strB0;
    }

    @Override // e1.l
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public p a(l.f fVar) {
        return (p) super.a(fVar);
    }

    @Override // e1.l
    protected void cancel() {
        super.cancel();
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).cancel();
        }
    }

    @Override // e1.l
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public p b(View view) {
        for (int i7 = 0; i7 < this.O.size(); i7++) {
            this.O.get(i7).b(view);
        }
        return (p) super.b(view);
    }

    public p e0(l lVar) {
        f0(lVar);
        long j7 = this.f6120h;
        if (j7 >= 0) {
            lVar.U(j7);
        }
        if ((this.S & 1) != 0) {
            lVar.W(s());
        }
        if ((this.S & 2) != 0) {
            w();
            lVar.Y(null);
        }
        if ((this.S & 4) != 0) {
            lVar.X(v());
        }
        if ((this.S & 8) != 0) {
            lVar.V(r());
        }
        return this;
    }

    @Override // e1.l
    public void f(r rVar) {
        if (G(rVar.f6161b)) {
            for (l lVar : this.O) {
                if (lVar.G(rVar.f6161b)) {
                    lVar.f(rVar);
                    rVar.f6162c.add(lVar);
                }
            }
        }
    }

    public l g0(int i7) {
        if (i7 < 0 || i7 >= this.O.size()) {
            return null;
        }
        return this.O.get(i7);
    }

    @Override // e1.l
    void h(r rVar) {
        super.h(rVar);
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.O.get(i7).h(rVar);
        }
    }

    public int h0() {
        return this.O.size();
    }

    @Override // e1.l
    public void i(r rVar) {
        if (G(rVar.f6161b)) {
            for (l lVar : this.O) {
                if (lVar.G(rVar.f6161b)) {
                    lVar.i(rVar);
                    rVar.f6162c.add(lVar);
                }
            }
        }
    }

    @Override // e1.l
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public p P(l.f fVar) {
        return (p) super.P(fVar);
    }

    @Override // e1.l
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public p Q(View view) {
        for (int i7 = 0; i7 < this.O.size(); i7++) {
            this.O.get(i7).Q(view);
        }
        return (p) super.Q(view);
    }

    @Override // e1.l
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public p U(long j7) {
        ArrayList<l> arrayList;
        super.U(j7);
        if (this.f6120h >= 0 && (arrayList = this.O) != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.O.get(i7).U(j7);
            }
        }
        return this;
    }

    @Override // e1.l
    /* JADX INFO: renamed from: l */
    public l clone() {
        p pVar = (p) super.clone();
        pVar.O = new ArrayList<>();
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            pVar.f0(this.O.get(i7).clone());
        }
        return pVar;
    }

    @Override // e1.l
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public p W(TimeInterpolator timeInterpolator) {
        this.S |= 1;
        ArrayList<l> arrayList = this.O;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                this.O.get(i7).W(timeInterpolator);
            }
        }
        return (p) super.W(timeInterpolator);
    }

    public p m0(int i7) {
        if (i7 == 0) {
            this.P = true;
        } else {
            if (i7 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i7);
            }
            this.P = false;
        }
        return this;
    }

    @Override // e1.l
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public p Z(long j7) {
        return (p) super.Z(j7);
    }

    @Override // e1.l
    protected void o(ViewGroup viewGroup, s sVar, s sVar2, ArrayList<r> arrayList, ArrayList<r> arrayList2) {
        long jY = y();
        int size = this.O.size();
        for (int i7 = 0; i7 < size; i7++) {
            l lVar = this.O.get(i7);
            if (jY > 0 && (this.P || i7 == 0)) {
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
}
