package r;

import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends e {
    public ArrayList<e> U0 = new ArrayList<>();

    public void c(e eVar) {
        this.U0.add(eVar);
        if (eVar.M() != null) {
            ((n) eVar.M()).w1(eVar);
        }
        eVar.f1(this);
    }

    public ArrayList<e> u1() {
        return this.U0;
    }

    @Override // r.e
    public void v0() {
        this.U0.clear();
        super.v0();
    }

    public void v1() {
        ArrayList<e> arrayList = this.U0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            e eVar = this.U0.get(i7);
            if (eVar instanceof n) {
                ((n) eVar).v1();
            }
        }
    }

    public void w1(e eVar) {
        this.U0.remove(eVar);
        eVar.v0();
    }

    public void x1() {
        this.U0.clear();
    }

    @Override // r.e
    public void z0(o.c cVar) {
        super.z0(cVar);
        int size = this.U0.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.U0.get(i7).z0(cVar);
        }
    }
}
