package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class hw2 extends ty {
    public ArrayList p0;

    @Override // p.ty
    public void A() {
        this.p0.clear();
        super.A();
    }

    @Override // p.ty
    public final void C(gh2 gh2Var) {
        super.C(gh2Var);
        int size = this.p0.size();
        for (int i = 0; i < size; i++) {
            ((ty) this.p0.get(i)).C(gh2Var);
        }
    }

    public abstract void N();
}
