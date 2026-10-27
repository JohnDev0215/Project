package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public final class wm extends gt3 {
    public final ArrayList<gt3> k;
    public int l;

    public wm(int i, qx qxVar) {
        super(qxVar);
        this.k = new ArrayList<>();
        this.f = i;
        qx qxVar2 = this.b;
        qx qxVarQ = qxVar2.q(i);
        while (qxVarQ != null) {
            qxVar2 = qxVarQ;
            qxVarQ = qxVarQ.q(this.f);
        }
        this.b = qxVar2;
        int i2 = this.f;
        gt3 gt3Var = i2 == 0 ? qxVar2.d : i2 == 1 ? qxVar2.e : null;
        ArrayList<gt3> arrayList = this.k;
        arrayList.add(gt3Var);
        qx qxVarP = qxVar2.p(this.f);
        while (qxVarP != null) {
            int i3 = this.f;
            arrayList.add(i3 == 0 ? qxVarP.d : i3 == 1 ? qxVarP.e : null);
            qxVarP = qxVarP.p(this.f);
        }
        for (gt3 gt3Var2 : arrayList) {
            int i4 = this.f;
            if (i4 == 0) {
                gt3Var2.b.b = this;
            } else if (i4 == 1) {
                gt3Var2.b.c = this;
            }
        }
        if ((this.f == 0 && ((rx) this.b.W).B0) && arrayList.size() > 1) {
            this.b = arrayList.get(arrayList.size() - 1).b;
        }
        this.l = this.f == 0 ? this.b.n0 : this.b.o0;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e1  */
    @Override // defpackage.gt3, defpackage.m80
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(defpackage.m80 r28) {
        /*
            Method dump skipped, instruction units count: 998
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wm.a(m80):void");
    }

    @Override // defpackage.gt3
    public final void d() {
        ArrayList<gt3> arrayList = this.k;
        Iterator<gt3> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = arrayList.size();
        if (size < 1) {
            return;
        }
        qx qxVar = arrayList.get(0).b;
        qx qxVar2 = arrayList.get(size - 1).b;
        int i = this.f;
        o80 o80Var = this.i;
        o80 o80Var2 = this.h;
        if (i == 0) {
            nx nxVar = qxVar.K;
            nx nxVar2 = qxVar2.M;
            o80 o80VarI = gt3.i(nxVar, 0);
            int iE = nxVar.e();
            qx qxVarM = m();
            if (qxVarM != null) {
                iE = qxVarM.K.e();
            }
            if (o80VarI != null) {
                gt3.b(o80Var2, o80VarI, iE);
            }
            o80 o80VarI2 = gt3.i(nxVar2, 0);
            int iE2 = nxVar2.e();
            qx qxVarN = n();
            if (qxVarN != null) {
                iE2 = qxVarN.M.e();
            }
            if (o80VarI2 != null) {
                gt3.b(o80Var, o80VarI2, -iE2);
            }
        } else {
            nx nxVar3 = qxVar.L;
            nx nxVar4 = qxVar2.N;
            o80 o80VarI3 = gt3.i(nxVar3, 1);
            int iE3 = nxVar3.e();
            qx qxVarM2 = m();
            if (qxVarM2 != null) {
                iE3 = qxVarM2.L.e();
            }
            if (o80VarI3 != null) {
                gt3.b(o80Var2, o80VarI3, iE3);
            }
            o80 o80VarI4 = gt3.i(nxVar4, 1);
            int iE4 = nxVar4.e();
            qx qxVarN2 = n();
            if (qxVarN2 != null) {
                iE4 = qxVarN2.N.e();
            }
            if (o80VarI4 != null) {
                gt3.b(o80Var, o80VarI4, -iE4);
            }
        }
        o80Var2.a = this;
        o80Var.a = this;
    }

    @Override // defpackage.gt3
    public final void e() {
        int i = 0;
        while (true) {
            ArrayList<gt3> arrayList = this.k;
            if (i >= arrayList.size()) {
                return;
            }
            arrayList.get(i).e();
            i++;
        }
    }

    @Override // defpackage.gt3
    public final void f() {
        this.c = null;
        Iterator<gt3> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // defpackage.gt3
    public final long j() {
        ArrayList<gt3> arrayList = this.k;
        int size = arrayList.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            gt3 gt3Var = arrayList.get(i);
            j = ((long) gt3Var.i.f) + gt3Var.j() + j + ((long) gt3Var.h.f);
        }
        return j;
    }

    @Override // defpackage.gt3
    public final boolean k() {
        ArrayList<gt3> arrayList = this.k;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (!arrayList.get(i).k()) {
                return false;
            }
        }
        return true;
    }

    public final qx m() {
        int i = 0;
        while (true) {
            ArrayList<gt3> arrayList = this.k;
            if (i >= arrayList.size()) {
                return null;
            }
            qx qxVar = arrayList.get(i).b;
            if (qxVar.j0 != 8) {
                return qxVar;
            }
            i++;
        }
    }

    public final qx n() {
        ArrayList<gt3> arrayList = this.k;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            qx qxVar = arrayList.get(size).b;
            if (qxVar.j0 != 8) {
                return qxVar;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        for (gt3 gt3Var : this.k) {
            sb.append("<");
            sb.append(gt3Var);
            sb.append("> ");
        }
        return sb.toString();
    }
}
