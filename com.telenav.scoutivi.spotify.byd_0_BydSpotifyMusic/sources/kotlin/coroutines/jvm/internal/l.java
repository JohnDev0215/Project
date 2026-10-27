package kotlin.coroutines.jvm.internal;

import kotlin.jvm.internal.s;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class l extends d implements kotlin.jvm.internal.h<Object> {
    private final int arity;

    public l(int i7, k5.d<Object> dVar) {
        super(dVar);
        this.arity = i7;
    }

    @Override // kotlin.jvm.internal.h
    public int getArity() {
        return this.arity;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strD = s.d(this);
        kotlin.jvm.internal.l.d(strD, "renderLambdaToString(this)");
        return strD;
    }

    public l(int i7) {
        this(i7, null);
    }
}
