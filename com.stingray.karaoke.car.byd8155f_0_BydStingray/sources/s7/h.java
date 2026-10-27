package s7;

import A7.A;
import A7.InterfaceC0060h;
import A7.m;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends g implements InterfaceC0060h {
    public final int l;

    public h(InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.l = 2;
    }

    @Override // A7.InterfaceC0060h
    public final int getArity() {
        return this.l;
    }

    @Override // s7.AbstractC2408a
    public final String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strJ = A.f443a.j(this);
        m.e(strJ, "renderLambdaToString(...)");
        return strJ;
    }
}
