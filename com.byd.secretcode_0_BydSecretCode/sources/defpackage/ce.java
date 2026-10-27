package defpackage;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import defpackage.yd;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class ce extends yd {
    public int N;
    public ArrayList<yd> L = new ArrayList<>();
    public boolean M = true;
    public boolean O = false;
    public int P = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    public class a extends zd {
        public final /* synthetic */ yd a;

        public a(yd ydVar) {
            this.a = ydVar;
        }

        @Override // yd.f
        public void e(yd ydVar) {
            this.a.T();
            ydVar.P(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    public static class b extends zd {
        public ce a;

        public b(ce ceVar) {
            this.a = ceVar;
        }

        @Override // defpackage.zd, yd.f
        public void c(yd ydVar) {
            ce ceVar = this.a;
            if (ceVar.O) {
                return;
            }
            ceVar.a0();
            this.a.O = true;
        }

        @Override // yd.f
        public void e(yd ydVar) {
            ce ceVar = this.a;
            int i = ceVar.N - 1;
            ceVar.N = i;
            if (i == 0) {
                ceVar.O = false;
                ceVar.p();
            }
            ydVar.P(this);
        }
    }

    @Override // defpackage.yd
    public void N(View view) {
        super.N(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).N(view);
        }
    }

    @Override // defpackage.yd
    public void R(View view) {
        super.R(view);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).R(view);
        }
    }

    @Override // defpackage.yd
    public void T() {
        if (this.L.isEmpty()) {
            a0();
            p();
            return;
        }
        o0();
        if (this.M) {
            Iterator<yd> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().T();
            }
            return;
        }
        for (int i = 1; i < this.L.size(); i++) {
            this.L.get(i - 1).a(new a(this.L.get(i)));
        }
        yd ydVar = this.L.get(0);
        if (ydVar != null) {
            ydVar.T();
        }
    }

    @Override // defpackage.yd
    public void V(yd.e eVar) {
        super.V(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).V(eVar);
        }
    }

    @Override // defpackage.yd
    public void X(td tdVar) {
        super.X(tdVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i = 0; i < this.L.size(); i++) {
                this.L.get(i).X(tdVar);
            }
        }
    }

    @Override // defpackage.yd
    public void Y(be beVar) {
        super.Y(beVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).Y(beVar);
        }
    }

    @Override // defpackage.yd
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

    @Override // defpackage.yd
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public ce a(yd.f fVar) {
        return (ce) super.a(fVar);
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public ce b(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).b(view);
        }
        return (ce) super.b(view);
    }

    public ce e0(yd ydVar) {
        f0(ydVar);
        long j = this.g;
        if (j >= 0) {
            ydVar.U(j);
        }
        if ((this.P & 1) != 0) {
            ydVar.W(s());
        }
        if ((this.P & 2) != 0) {
            ydVar.Y(w());
        }
        if ((this.P & 4) != 0) {
            ydVar.X(v());
        }
        if ((this.P & 8) != 0) {
            ydVar.V(r());
        }
        return this;
    }

    public final void f0(yd ydVar) {
        this.L.add(ydVar);
        ydVar.v = this;
    }

    @Override // defpackage.yd
    public void g(ee eeVar) {
        if (G(eeVar.b)) {
            for (yd ydVar : this.L) {
                if (ydVar.G(eeVar.b)) {
                    ydVar.g(eeVar);
                    eeVar.c.add(ydVar);
                }
            }
        }
    }

    public yd g0(int i) {
        if (i < 0 || i >= this.L.size()) {
            return null;
        }
        return this.L.get(i);
    }

    public int h0() {
        return this.L.size();
    }

    @Override // defpackage.yd
    public void i(ee eeVar) {
        super.i(eeVar);
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            this.L.get(i).i(eeVar);
        }
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public ce P(yd.f fVar) {
        return (ce) super.P(fVar);
    }

    @Override // defpackage.yd
    public void j(ee eeVar) {
        if (G(eeVar.b)) {
            for (yd ydVar : this.L) {
                if (ydVar.G(eeVar.b)) {
                    ydVar.j(eeVar);
                    eeVar.c.add(ydVar);
                }
            }
        }
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public ce Q(View view) {
        for (int i = 0; i < this.L.size(); i++) {
            this.L.get(i).Q(view);
        }
        return (ce) super.Q(view);
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public ce U(long j) {
        ArrayList<yd> arrayList;
        super.U(j);
        if (this.g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).U(j);
            }
        }
        return this;
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public ce W(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<yd> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.L.get(i).W(timeInterpolator);
            }
        }
        return (ce) super.W(timeInterpolator);
    }

    @Override // defpackage.yd
    /* JADX INFO: renamed from: m */
    public yd clone() {
        ce ceVar = (ce) super.clone();
        ceVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            ceVar.f0(this.L.get(i).clone());
        }
        return ceVar;
    }

    public ce m0(int i) {
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

    @Override // defpackage.yd
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public ce Z(long j) {
        return (ce) super.Z(j);
    }

    @Override // defpackage.yd
    public void o(ViewGroup viewGroup, fe feVar, fe feVar2, ArrayList<ee> arrayList, ArrayList<ee> arrayList2) {
        long jY = y();
        int size = this.L.size();
        for (int i = 0; i < size; i++) {
            yd ydVar = this.L.get(i);
            if (jY > 0 && (this.M || i == 0)) {
                long jY2 = ydVar.y();
                if (jY2 > 0) {
                    ydVar.Z(jY2 + jY);
                } else {
                    ydVar.Z(jY);
                }
            }
            ydVar.o(viewGroup, feVar, feVar2, arrayList, arrayList2);
        }
    }

    public final void o0() {
        b bVar = new b(this);
        Iterator<yd> it = this.L.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.N = this.L.size();
    }
}
