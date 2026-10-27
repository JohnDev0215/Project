package a.r;

import a.r.i;
import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class o extends i {
    public int K;
    public ArrayList<i> I = new ArrayList<>();
    public boolean J = true;
    public boolean L = false;
    public int M = 0;

    public class a extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f1346a;

        public a(o oVar, i iVar) {
            this.f1346a = iVar;
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            this.f1346a.z();
            iVar.w(this);
        }
    }

    public static class b extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o f1347a;

        public b(o oVar) {
            this.f1347a = oVar;
        }

        @Override // a.r.l, a.r.i.d
        public void a(i iVar) {
            o oVar = this.f1347a;
            if (oVar.L) {
                return;
            }
            oVar.G();
            this.f1347a.L = true;
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            o oVar = this.f1347a;
            int i = oVar.K - 1;
            oVar.K = i;
            if (i == 0) {
                oVar.L = false;
                oVar.n();
            }
            iVar.w(this);
        }
    }

    @Override // a.r.i
    public i A(long j) {
        ArrayList<i> arrayList;
        this.f1327c = j;
        if (j >= 0 && (arrayList = this.I) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.I.get(i).A(j);
            }
        }
        return this;
    }

    @Override // a.r.i
    public void B(i.c cVar) {
        this.D = cVar;
        this.M |= 8;
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).B(cVar);
        }
    }

    @Override // a.r.i
    public i C(TimeInterpolator timeInterpolator) {
        this.M |= 1;
        ArrayList<i> arrayList = this.I;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.I.get(i).C(timeInterpolator);
            }
        }
        this.f1328d = timeInterpolator;
        return this;
    }

    @Override // a.r.i
    public void D(e eVar) {
        this.E = eVar == null ? i.G : eVar;
        this.M |= 4;
        if (this.I != null) {
            for (int i = 0; i < this.I.size(); i++) {
                this.I.get(i).D(eVar);
            }
        }
    }

    @Override // a.r.i
    public void E(n nVar) {
        this.C = nVar;
        this.M |= 2;
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).E(nVar);
        }
    }

    @Override // a.r.i
    public i F(long j) {
        this.f1326b = j;
        return this;
    }

    @Override // a.r.i
    public String H(String str) {
        String strH = super.H(str);
        for (int i = 0; i < this.I.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strH);
            sb.append("\n");
            sb.append(this.I.get(i).H(str + "  "));
            strH = sb.toString();
        }
        return strH;
    }

    public o I(i iVar) {
        this.I.add(iVar);
        iVar.r = this;
        long j = this.f1327c;
        if (j >= 0) {
            iVar.A(j);
        }
        if ((this.M & 1) != 0) {
            iVar.C(this.f1328d);
        }
        if ((this.M & 2) != 0) {
            iVar.E(null);
        }
        if ((this.M & 4) != 0) {
            iVar.D(this.E);
        }
        if ((this.M & 8) != 0) {
            iVar.B(this.D);
        }
        return this;
    }

    public i J(int i) {
        if (i < 0 || i >= this.I.size()) {
            return null;
        }
        return this.I.get(i);
    }

    public o K(int i) {
        if (i == 0) {
            this.J = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
            }
            this.J = false;
        }
        return this;
    }

    @Override // a.r.i
    public i a(i.d dVar) {
        super.a(dVar);
        return this;
    }

    @Override // a.r.i
    public i b(View view) {
        for (int i = 0; i < this.I.size(); i++) {
            this.I.get(i).b(view);
        }
        this.f1330f.add(view);
        return this;
    }

    @Override // a.r.i
    public void d() {
        super.d();
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).d();
        }
    }

    @Override // a.r.i
    public void e(p pVar) {
        if (t(pVar.f1349b)) {
            for (i iVar : this.I) {
                if (iVar.t(pVar.f1349b)) {
                    iVar.e(pVar);
                    pVar.f1350c.add(iVar);
                }
            }
        }
    }

    @Override // a.r.i
    public void g(p pVar) {
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).g(pVar);
        }
    }

    @Override // a.r.i
    public void h(p pVar) {
        if (t(pVar.f1349b)) {
            for (i iVar : this.I) {
                if (iVar.t(pVar.f1349b)) {
                    iVar.h(pVar);
                    pVar.f1350c.add(iVar);
                }
            }
        }
    }

    @Override // a.r.i
    /* JADX INFO: renamed from: k */
    public i clone() {
        o oVar = (o) super.clone();
        oVar.I = new ArrayList<>();
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            i iVarClone = this.I.get(i).clone();
            oVar.I.add(iVarClone);
            iVarClone.r = oVar;
        }
        return oVar;
    }

    @Override // a.r.i
    public void m(ViewGroup viewGroup, q qVar, q qVar2, ArrayList<p> arrayList, ArrayList<p> arrayList2) {
        long j = this.f1326b;
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            i iVar = this.I.get(i);
            if (j > 0 && (this.J || i == 0)) {
                long j2 = iVar.f1326b;
                if (j2 > 0) {
                    iVar.F(j2 + j);
                } else {
                    iVar.F(j);
                }
            }
            iVar.m(viewGroup, qVar, qVar2, arrayList, arrayList2);
        }
    }

    @Override // a.r.i
    public void v(View view) {
        super.v(view);
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).v(view);
        }
    }

    @Override // a.r.i
    public i w(i.d dVar) {
        super.w(dVar);
        return this;
    }

    @Override // a.r.i
    public i x(View view) {
        for (int i = 0; i < this.I.size(); i++) {
            this.I.get(i).x(view);
        }
        this.f1330f.remove(view);
        return this;
    }

    @Override // a.r.i
    public void y(View view) {
        super.y(view);
        int size = this.I.size();
        for (int i = 0; i < size; i++) {
            this.I.get(i).y(view);
        }
    }

    @Override // a.r.i
    public void z() {
        if (this.I.isEmpty()) {
            G();
            n();
            return;
        }
        b bVar = new b(this);
        Iterator<i> it = this.I.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.K = this.I.size();
        if (this.J) {
            Iterator<i> it2 = this.I.iterator();
            while (it2.hasNext()) {
                it2.next().z();
            }
            return;
        }
        for (int i = 1; i < this.I.size(); i++) {
            this.I.get(i - 1).a(new a(this, this.I.get(i)));
        }
        i iVar = this.I.get(0);
        if (iVar != null) {
            iVar.z();
        }
    }
}
