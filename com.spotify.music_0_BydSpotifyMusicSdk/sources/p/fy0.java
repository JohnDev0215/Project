package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class fy0 extends xn2 {
    public final /* synthetic */ Object a;
    public final /* synthetic */ ArrayList b;
    public final /* synthetic */ Object c = null;
    public final /* synthetic */ ArrayList d = null;
    public final /* synthetic */ Object e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ gy0 g;

    public fy0(gy0 gy0Var, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.g = gy0Var;
        this.a = obj;
        this.b = arrayList;
        this.e = obj2;
        this.f = arrayList2;
    }

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        wn2Var.z(this);
    }

    @Override // p.xn2, p.vn2
    public final void b() {
        gy0 gy0Var = this.g;
        Object obj = this.a;
        if (obj != null) {
            gy0Var.s(obj, this.b, null);
        }
        Object obj2 = this.c;
        if (obj2 != null) {
            gy0Var.s(obj2, this.d, null);
        }
        Object obj3 = this.e;
        if (obj3 != null) {
            gy0Var.s(obj3, this.f, null);
        }
    }
}
