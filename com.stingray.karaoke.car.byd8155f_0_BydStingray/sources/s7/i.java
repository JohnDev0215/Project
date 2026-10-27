package s7;

import A7.A;
import A7.InterfaceC0060h;
import A7.m;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends AbstractC2410c implements InterfaceC0060h {
    private final int arity;

    public i(int i7, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.arity = i7;
    }

    @Override // A7.InterfaceC0060h
    public int getArity() {
        return this.arity;
    }

    @Override // s7.AbstractC2408a
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strJ = A.f443a.j(this);
        m.e(strJ, "renderLambdaToString(...)");
        return strJ;
    }
}
