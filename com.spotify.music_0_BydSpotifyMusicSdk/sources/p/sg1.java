package p;

/* JADX INFO: loaded from: classes.dex */
public class sg1 extends pg1 {
    public final /* synthetic */ bh1 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public sg1(bh1 bh1Var) {
        super(bh1Var);
        this.f = bh1Var;
    }

    @Override // p.ng1
    public final void a(String str) {
        super.a(str);
    }

    public final void b() {
        rg1 rg1Var = new rg1(this, this.f);
        this.b = rg1Var;
        rg1Var.onCreate();
    }
}
