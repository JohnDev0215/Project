package kotlin.jvm.internal;

import java.io.Serializable;

/* JADX INFO: compiled from: Lambda.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class m<R> implements h<R>, Serializable {
    private final int arity;

    public m(int i7) {
        this.arity = i7;
    }

    @Override // kotlin.jvm.internal.h
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        String strE = s.e(this);
        l.d(strE, "renderLambdaToString(this)");
        return strE;
    }
}
