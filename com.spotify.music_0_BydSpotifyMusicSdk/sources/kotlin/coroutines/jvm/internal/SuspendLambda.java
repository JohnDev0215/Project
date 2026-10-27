package kotlin.coroutines.jvm.internal;

import p.c00;
import p.qz0;
import p.r22;
import p.t22;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class SuspendLambda extends ContinuationImpl implements qz0 {
    private final int arity;

    public SuspendLambda(int i, c00 c00Var) {
        super(c00Var);
        this.arity = i;
    }

    @Override // p.qz0
    public final int getArity() {
        return this.arity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final String toString() {
        if (d() != null) {
            return super.toString();
        }
        r22.a.getClass();
        String strA = t22.a(this);
        v03.i(strA, "renderLambdaToString(this)");
        return strA;
    }
}
