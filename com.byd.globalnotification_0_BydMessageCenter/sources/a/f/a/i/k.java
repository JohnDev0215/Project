package a.f.a.i;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class k extends d {
    public ArrayList<d> v0 = new ArrayList<>();

    @Override // a.f.a.i.d
    public void F() {
        this.v0.clear();
        super.F();
    }

    @Override // a.f.a.i.d
    public void H(a.f.a.c cVar) {
        super.H(cVar);
        int size = this.v0.size();
        for (int i = 0; i < size; i++) {
            this.v0.get(i).H(cVar);
        }
    }

    public void V() {
        ArrayList<d> arrayList = this.v0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            d dVar = this.v0.get(i);
            if (dVar instanceof k) {
                ((k) dVar).V();
            }
        }
    }
}
