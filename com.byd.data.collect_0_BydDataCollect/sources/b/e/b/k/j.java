package b.e.b.k;

import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends e implements i {
    public e[] G0 = new e[4];
    public int H0 = 0;

    @Override // b.e.b.k.i
    public void a(e eVar) {
        if (eVar == this || eVar == null) {
            return;
        }
        int i2 = this.H0 + 1;
        e[] eVarArr = this.G0;
        if (i2 > eVarArr.length) {
            this.G0 = (e[]) Arrays.copyOf(eVarArr, eVarArr.length * 2);
        }
        e[] eVarArr2 = this.G0;
        int i3 = this.H0;
        eVarArr2[i3] = eVar;
        this.H0 = i3 + 1;
    }

    @Override // b.e.b.k.i
    public void b(f fVar) {
    }

    @Override // b.e.b.k.i
    public void c() {
        this.H0 = 0;
        Arrays.fill(this.G0, (Object) null);
    }

    @Override // b.e.b.k.e
    public void l(e eVar, HashMap<e, e> map) {
        super.l(eVar, map);
        j jVar = (j) eVar;
        this.H0 = 0;
        int i2 = jVar.H0;
        for (int i3 = 0; i3 < i2; i3++) {
            a(map.get(jVar.G0[i3]));
        }
    }
}
