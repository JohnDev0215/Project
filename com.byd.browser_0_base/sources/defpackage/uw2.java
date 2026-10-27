package defpackage;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class uw2 extends c00 implements vr0<Object> {
    public final int h;

    public uw2(b00 b00Var) {
        super(b00Var, b00Var != null ? b00Var.getContext() : null);
        this.h = 2;
    }

    @Override // defpackage.vr0
    public final int b() {
        return this.h;
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
