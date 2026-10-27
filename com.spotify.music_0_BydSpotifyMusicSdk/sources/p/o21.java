package p;

import okhttp3.Call;

/* JADX INFO: loaded from: classes.dex */
public abstract class o21 extends ub2 {
    public final s32 a;
    public final Call.Factory b;
    public final h00 c;

    public o21(s32 s32Var, Call.Factory factory, h00 h00Var) {
        this.a = s32Var;
        this.b = factory;
        this.c = h00Var;
    }

    public abstract Object b(ip1 ip1Var, Object[] objArr);
}
