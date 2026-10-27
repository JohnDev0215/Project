package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class nx0 implements mx0 {
    public final int a;
    public final int b = 1;
    public final /* synthetic */ ox0 c;

    public nx0(ox0 ox0Var, int i) {
        this.c = ox0Var;
        this.a = i;
    }

    @Override // p.mx0
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        ox0 ox0Var = this.c;
        ww0 ww0Var = ox0Var.w;
        int i = this.a;
        if (ww0Var == null || i >= 0 || !ww0Var.d().L(-1, 0)) {
            return ox0Var.N(arrayList, arrayList2, i, this.b);
        }
        return false;
    }
}
