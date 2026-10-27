package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class ku0 extends qx implements iu0 {
    public qx[] w0 = new qx[4];
    public int x0 = 0;

    public final void U(int i, ft3 ft3Var, ArrayList arrayList) {
        for (int i2 = 0; i2 < this.x0; i2++) {
            qx qxVar = this.w0[i2];
            ArrayList<qx> arrayList2 = ft3Var.a;
            if (!arrayList2.contains(qxVar)) {
                arrayList2.add(qxVar);
            }
        }
        for (int i3 = 0; i3 < this.x0; i3++) {
            lt0.a(this.w0[i3], i, arrayList, ft3Var);
        }
    }

    @Override // defpackage.iu0
    public final void a(qx qxVar) {
        if (qxVar == this || qxVar == null) {
            return;
        }
        int i = this.x0 + 1;
        qx[] qxVarArr = this.w0;
        if (i > qxVarArr.length) {
            this.w0 = (qx[]) Arrays.copyOf(qxVarArr, qxVarArr.length * 2);
        }
        qx[] qxVarArr2 = this.w0;
        int i2 = this.x0;
        qxVarArr2[i2] = qxVar;
        this.x0 = i2 + 1;
    }

    @Override // defpackage.iu0
    public final void b() {
        this.x0 = 0;
        Arrays.fill(this.w0, (Object) null);
    }

    @Override // defpackage.qx
    public void j(qx qxVar, HashMap<qx, qx> map) {
        super.j(qxVar, map);
        ku0 ku0Var = (ku0) qxVar;
        this.x0 = 0;
        int i = ku0Var.x0;
        for (int i2 = 0; i2 < i; i2++) {
            a(map.get(ku0Var.w0[i2]));
        }
    }

    @Override // defpackage.iu0
    public void c() {
    }
}
