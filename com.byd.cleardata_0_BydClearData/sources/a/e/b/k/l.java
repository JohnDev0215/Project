package a.e.b.k;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class l extends e {
    public ArrayList<e> w0 = new ArrayList<>();

    public void H0() {
        ArrayList<e> arrayList = this.w0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            e eVar = this.w0.get(i);
            if (eVar instanceof l) {
                ((l) eVar).H0();
            }
        }
    }

    public void I0(e eVar) {
        this.w0.remove(eVar);
        eVar.t0(null);
    }

    public void J0() {
        this.w0.clear();
    }

    @Override // a.e.b.k.e
    public void W() {
        this.w0.clear();
        super.W();
    }

    @Override // a.e.b.k.e
    public void X(a.e.b.c cVar) {
        super.X(cVar);
        int size = this.w0.size();
        for (int i = 0; i < size; i++) {
            this.w0.get(i).X(cVar);
        }
    }

    public void a(e eVar) {
        this.w0.add(eVar);
        if (eVar.E() != null) {
            ((l) eVar.E()).I0(eVar);
        }
        eVar.t0(this);
    }
}
