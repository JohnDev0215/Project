package b.x;

import android.animation.TimeInterpolator;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.view.ViewGroup;
import b.x.m;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionSet.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends m {
    public int N;
    public ArrayList<m> L = new ArrayList<>();
    public boolean M = true;
    public boolean O = false;
    public int P = 0;

    /* JADX INFO: compiled from: TransitionSet.java */
    public class a extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ m f3309a;

        public a(m mVar) {
            this.f3309a = mVar;
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            this.f3309a.U();
            mVar.Q(this);
        }
    }

    /* JADX INFO: compiled from: TransitionSet.java */
    public static class b extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q f3311a;

        public b(q qVar) {
            this.f3311a = qVar;
        }

        @Override // b.x.n, b.x.m.f
        public void c(m mVar) {
            q qVar = this.f3311a;
            if (qVar.O) {
                return;
            }
            qVar.b0();
            this.f3311a.O = true;
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            q qVar = this.f3311a;
            int i2 = qVar.N - 1;
            qVar.N = i2;
            if (i2 == 0) {
                qVar.O = false;
                qVar.o();
            }
            mVar.Q(this);
        }
    }

    @Override // b.x.m
    public void O(View view) {
        super.O(view);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).O(view);
        }
    }

    @Override // b.x.m
    public void S(View view) {
        super.S(view);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).S(view);
        }
    }

    @Override // b.x.m
    public void U() {
        if (this.L.isEmpty()) {
            b0();
            o();
            return;
        }
        p0();
        if (this.M) {
            Iterator<m> it = this.L.iterator();
            while (it.hasNext()) {
                it.next().U();
            }
            return;
        }
        for (int i2 = 1; i2 < this.L.size(); i2++) {
            this.L.get(i2 - 1).a(new a(this.L.get(i2)));
        }
        m mVar = this.L.get(0);
        if (mVar != null) {
            mVar.U();
        }
    }

    @Override // b.x.m
    public void W(m.e eVar) {
        super.W(eVar);
        this.P |= 8;
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).W(eVar);
        }
    }

    @Override // b.x.m
    public void Y(g gVar) {
        super.Y(gVar);
        this.P |= 4;
        if (this.L != null) {
            for (int i2 = 0; i2 < this.L.size(); i2++) {
                this.L.get(i2).Y(gVar);
            }
        }
    }

    @Override // b.x.m
    public void Z(p pVar) {
        super.Z(pVar);
        this.P |= 2;
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).Z(pVar);
        }
    }

    @Override // b.x.m
    public String c0(String str) {
        String strC0 = super.c0(str);
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strC0);
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            sb.append(this.L.get(i2).c0(str + "  "));
            strC0 = sb.toString();
        }
        return strC0;
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: d0, reason: merged with bridge method [inline-methods] */
    public q a(m.f fVar) {
        return (q) super.a(fVar);
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: e0, reason: merged with bridge method [inline-methods] */
    public q b(View view) {
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            this.L.get(i2).b(view);
        }
        return (q) super.b(view);
    }

    @Override // b.x.m
    public void f(s sVar) {
        if (H(sVar.f3316b)) {
            for (m mVar : this.L) {
                if (mVar.H(sVar.f3316b)) {
                    mVar.f(sVar);
                    sVar.f3317c.add(mVar);
                }
            }
        }
    }

    public q f0(m mVar) {
        g0(mVar);
        long j = this.f3291g;
        if (j >= 0) {
            mVar.V(j);
        }
        if ((this.P & 1) != 0) {
            mVar.X(r());
        }
        if ((this.P & 2) != 0) {
            mVar.Z(v());
        }
        if ((this.P & 4) != 0) {
            mVar.Y(u());
        }
        if ((this.P & 8) != 0) {
            mVar.W(q());
        }
        return this;
    }

    public final void g0(m mVar) {
        this.L.add(mVar);
        mVar.v = this;
    }

    @Override // b.x.m
    public void h(s sVar) {
        super.h(sVar);
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.L.get(i2).h(sVar);
        }
    }

    public m h0(int i2) {
        if (i2 < 0 || i2 >= this.L.size()) {
            return null;
        }
        return this.L.get(i2);
    }

    @Override // b.x.m
    public void i(s sVar) {
        if (H(sVar.f3316b)) {
            for (m mVar : this.L) {
                if (mVar.H(sVar.f3316b)) {
                    mVar.i(sVar);
                    sVar.f3317c.add(mVar);
                }
            }
        }
    }

    public int i0() {
        return this.L.size();
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: j0, reason: merged with bridge method [inline-methods] */
    public q Q(m.f fVar) {
        return (q) super.Q(fVar);
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: k0, reason: merged with bridge method [inline-methods] */
    public q R(View view) {
        for (int i2 = 0; i2 < this.L.size(); i2++) {
            this.L.get(i2).R(view);
        }
        return (q) super.R(view);
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: l */
    public m clone() {
        q qVar = (q) super.clone();
        qVar.L = new ArrayList<>();
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            qVar.g0(this.L.get(i2).clone());
        }
        return qVar;
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: l0, reason: merged with bridge method [inline-methods] */
    public q V(long j) {
        ArrayList<m> arrayList;
        super.V(j);
        if (this.f3291g >= 0 && (arrayList = this.L) != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.L.get(i2).V(j);
            }
        }
        return this;
    }

    @Override // b.x.m
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public q X(TimeInterpolator timeInterpolator) {
        this.P |= 1;
        ArrayList<m> arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.L.get(i2).X(timeInterpolator);
            }
        }
        return (q) super.X(timeInterpolator);
    }

    @Override // b.x.m
    public void n(ViewGroup viewGroup, t tVar, t tVar2, ArrayList<s> arrayList, ArrayList<s> arrayList2) {
        long jY = y();
        int size = this.L.size();
        for (int i2 = 0; i2 < size; i2++) {
            m mVar = this.L.get(i2);
            if (jY > 0 && (this.M || i2 == 0)) {
                long jY2 = mVar.y();
                if (jY2 > 0) {
                    mVar.a0(jY2 + jY);
                } else {
                    mVar.a0(jY);
                }
            }
            mVar.n(viewGroup, tVar, tVar2, arrayList, arrayList2);
        }
    }

    public q n0(int i2) {
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

    @Override // b.x.m
    /* JADX INFO: renamed from: o0, reason: merged with bridge method [inline-methods] */
    public q a0(long j) {
        return (q) super.a0(j);
    }

    public final void p0() {
        b bVar = new b(this);
        Iterator<m> it = this.L.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.N = this.L.size();
    }
}
