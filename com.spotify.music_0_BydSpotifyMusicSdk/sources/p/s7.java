package p;

import android.view.LayoutInflater;

/* JADX INFO: loaded from: classes.dex */
public final class s7 implements sp1 {
    public final /* synthetic */ t7 a;

    public s7(t7 t7Var) {
        this.a = t7Var;
    }

    @Override // p.sp1
    public final void a() {
        t7 t7Var = this.a;
        e8 e8VarU = t7Var.u();
        s8 s8Var = (s8) e8VarU;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(s8Var.s);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(s8Var);
        } else {
            boolean z = layoutInflaterFrom.getFactory2() instanceof s8;
        }
        t7Var.m.b.a("androidx:appcompat");
        e8VarU.b();
    }
}
