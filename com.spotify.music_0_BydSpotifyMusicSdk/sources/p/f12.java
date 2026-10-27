package p;

/* JADX INFO: loaded from: classes.dex */
public final class f12 extends ae1 {
    public final /* synthetic */ g12 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f12(int i, g12 g12Var) {
        super(i);
        this.g = g12Var;
    }

    @Override // p.ae1
    public final void a(Object obj, Object obj2, Object obj3) {
        e12 e12Var = (e12) obj2;
        this.g.a.g((mi1) obj, e12Var.a, e12Var.b, e12Var.c);
    }

    @Override // p.ae1
    public final int e(Object obj, Object obj2) {
        return ((e12) obj2).c;
    }
}
