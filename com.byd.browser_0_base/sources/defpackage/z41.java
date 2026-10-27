package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: Lambda.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class z41<R> implements vr0<R>, Serializable {
    public final int e;

    public z41(int i) {
        this.e = i;
    }

    @Override // defpackage.vr0
    public final int b() {
        return this.e;
    }

    public final String toString() {
        fa2.a.getClass();
        String strA = ga2.a(this);
        i21.d(strA, "renderLambdaToString(this)");
        return strA;
    }
}
