package kotlin.jvm.internal;

import java.io.Serializable;
import p.qz0;
import p.r22;
import p.t22;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lambda<R> implements qz0, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // p.qz0
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        r22.a.getClass();
        String strA = t22.a(this);
        v03.i(strA, "renderLambdaToString(this)");
        return strA;
    }
}
