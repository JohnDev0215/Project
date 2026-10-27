package defpackage;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ub2 extends tb2 implements vr0<Object> {
    public final int f;

    public ub2(b00 b00Var) {
        super(b00Var);
        this.f = 2;
    }

    @Override // defpackage.vr0
    public final int b() {
        return this.f;
    }

    @Override // defpackage.ed
    public final String toString() {
        if (this.e != null) {
            return super.toString();
        }
        fa2.a.getClass();
        String strA = ga2.a(this);
        i21.d(strA, "renderLambdaToString(this)");
        return strA;
    }
}
