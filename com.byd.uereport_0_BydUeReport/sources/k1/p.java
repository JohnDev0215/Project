package k1;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Iterator;
import k1.l;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends l {
    public int P;
    public ArrayList<l> N = new ArrayList<>();
    public boolean O = true;
    public boolean Q = false;
    public int R = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    public class a extends m {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ l f5816d;

        public a(l lVar) {
            this.f5816d = lVar;
        }

        @Override // k1.l.f
        public void e(l lVar) {
            this.f5816d.T();
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    public static class b extends m {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public p f5818d;

        public b(p pVar) {
            this.f5818d = pVar;
        }

        @Override // k1.m, k1.l.f
        public void a(l lVar) {
            p pVar = this.f5818d;
            if (pVar.Q) {
                return;
            }
            pVar.a0();
            this.f5818d.Q = true;
        }

        @Override // k1.l.f
        public void e(l lVar) {
            p pVar = this.f5818d;
            int i8 = pVar.P - 1;
            pVar.P = i8;
            if (i8 == 0) {
                pVar.Q = false;
                pVar.p();
            }
            lVar.P(this);
        }
    }

    @Override // k1.l
    public void N(View view) {
        super.N(view);
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.N.get(i8).N(view);
        }
    }

    @Override // k1.l
    public void R(View view) {
        super.R(view);
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.N.get(i8).R(view);
        }
    }

    @Override // k1.l
    public void T() {
        if (this.N.isEmpty()) {
            a0();
            p();
            return;
        }
        o0();
        if (this.O) {
            Iterator<l> it = this.N.iterator();
            while (it.hasNext()) {
                it.next().T();
            }
            return;
        }
        for (int i8 = 1; i8 < this.N.size(); i8++) {
            this.N.get(i8 - 1).a(new a(this.N.get(i8)));
        }
        l lVar = this.N.get(0);
        if (lVar != null) {
            lVar.T();
        }
    }

    @Override // k1.l
    public void V(l.e eVar) {
        super.V(eVar);
        this.R |= 8;
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.N.get(i8).V(eVar);
        }
    }

    @Override // k1.l
    public void X(g gVar) {
        super.X(gVar);
        this.R |= 4;
        if (this.N != null) {
            for (int i8 = 0; i8 < this.N.size(); i8++) {
                this.N.get(i8).X(gVar);
            }
        }
    }

    @Override // k1.l
    public void Y(o oVar) {
        super.Y(oVar);
        this.R |= 2;
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.N.get(i8).Y(oVar);
        }
    }

    @Override // k1.l
    public String b0(String str) {
        String strB0 = super.b0(str);
        for (int i8 = 0; i8 < this.N.size(); i8++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strB0);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(this.N.get(i8).b0(str + "  "));
            strB0 = sb.toString();
        }
        return strB0;
    }

    @Override // k1.l
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public p a(l.f fVar) {
        return (p) super.a(fVar);
    }

    @Override // k1.l
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public p b(View view) {
        for (int i8 = 0; i8 < this.N.size(); i8++) {
            this.N.get(i8).b(view);
        }
        return (p) super.b(view);
    }

    public p e0(l lVar) {
        f0(lVar);
        long j8 = this.f5783i;
        if (j8 >= 0) {
            lVar.U(j8);
        }
        if ((this.R & 1) != 0) {
            lVar.W(s());
        }
        if ((this.R & 2) != 0) {
            lVar.Y(w());
        }
        if ((this.R & 4) != 0) {
            lVar.X(v());
        }
        if ((this.R & 8) != 0) {
            lVar.V(r());
        }
        return this;
    }

    public final void f0(l lVar) {
        this.N.add(lVar);
        lVar.f5798x = this;
    }

    @Override // k1.l
    public void g(r rVar) {
        if (G(rVar.f5823b)) {
            for (l lVar : this.N) {
                if (lVar.G(rVar.f5823b)) {
                    lVar.g(rVar);
                    rVar.f5824c.add(lVar);
                }
            }
        }
    }

    public l g0(int i8) {
        if (i8 < 0 || i8 >= this.N.size()) {
            return null;
        }
        return this.N.get(i8);
    }

    public int h0() {
        return this.N.size();
    }

    @Override // k1.l
    public void i(r rVar) {
        super.i(rVar);
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.N.get(i8).i(rVar);
        }
    }

    @Override // k1.l
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public p P(l.f fVar) {
        return (p) super.P(fVar);
    }

    @Override // k1.l
    public void j(r rVar) {
        if (G(rVar.f5823b)) {
            for (l lVar : this.N) {
                if (lVar.G(rVar.f5823b)) {
                    lVar.j(rVar);
                    rVar.f5824c.add(lVar);
                }
            }
        }
    }

    @Override // k1.l
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public p Q(View view) {
        for (int i8 = 0; i8 < this.N.size(); i8++) {
            this.N.get(i8).Q(view);
        }
        return (p) super.Q(view);
    }

    @Override // k1.l
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public p U(long j8) {
        ArrayList<l> arrayList;
        super.U(j8);
        if (this.f5783i >= 0 && (arrayList = this.N) != null) {
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.N.get(i8).U(j8);
            }
        }
        return this;
    }

    @Override // k1.l
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public p W(TimeInterpolator timeInterpolator) {
        this.R |= 1;
        ArrayList<l> arrayList = this.N;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.N.get(i8).W(timeInterpolator);
            }
        }
        return (p) super.W(timeInterpolator);
    }

    @Override // k1.l
    /* JADX INFO: renamed from: m */
    public l clone() {
        p pVar = (p) super.clone();
        pVar.N = new ArrayList<>();
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            pVar.f0(this.N.get(i8).clone());
        }
        return pVar;
    }

    public p m0(int i8) {
        if (i8 == 0) {
            this.O = true;
        } else {
            if (i8 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i8);
            }
            this.O = false;
        }
        return this;
    }

    @Override // k1.l
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public p Z(long j8) {
        return (p) super.Z(j8);
    }

    @Override // k1.l
    public void o(ViewGroup viewGroup, s sVar, s sVar2, ArrayList<r> arrayList, ArrayList<r> arrayList2) {
        long jY = y();
        int size = this.N.size();
        for (int i8 = 0; i8 < size; i8++) {
            l lVar = this.N.get(i8);
            if (jY > 0 && (this.O || i8 == 0)) {
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
        Iterator<l> it = this.N.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.P = this.N.size();
    }
}
