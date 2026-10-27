package A7;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class o implements InterfaceC0060h, Serializable {
    public final int l;

    public o(int i7) {
        this.l = i7;
    }

    @Override // A7.InterfaceC0060h
    public final int getArity() {
        return this.l;
    }

    public final String toString() {
        String strK = A.f443a.k(this);
        m.e(strK, "renderLambdaToString(...)");
        return strK;
    }
}
