package b.e.b.k;

import java.util.ArrayList;

/* JADX INFO: compiled from: WidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends e {
    public ArrayList<e> G0 = new ArrayList<>();

    public ArrayList<e> L0() {
        return this.G0;
    }

    public void M0() {
        ArrayList<e> arrayList = this.G0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            e eVar = this.G0.get(i2);
            if (eVar instanceof m) {
                ((m) eVar).M0();
            }
        }
    }

    public void N0(e eVar) {
        this.G0.remove(eVar);
        eVar.x0(null);
    }

    public void O0() {
        this.G0.clear();
    }

    @Override // b.e.b.k.e
    public void Z() {
        this.G0.clear();
        super.Z();
    }

    public void a(e eVar) {
        this.G0.add(eVar);
        if (eVar.H() != null) {
            ((m) eVar.H()).N0(eVar);
        }
        eVar.x0(this);
    }

    @Override // b.e.b.k.e
    public void b0(b.e.b.c cVar) {
        super.b0(cVar);
        int size = this.G0.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.G0.get(i2).b0(cVar);
        }
    }
}
