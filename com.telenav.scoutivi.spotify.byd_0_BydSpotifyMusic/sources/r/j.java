package r;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import s.o;

/* JADX INFO: compiled from: HelperWidget.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends e implements i {
    public e[] U0 = new e[4];
    public int V0 = 0;

    @Override // r.i
    public void a(f fVar) {
    }

    @Override // r.i
    public void b() {
        this.V0 = 0;
        Arrays.fill(this.U0, (Object) null);
    }

    @Override // r.i
    public void c(e eVar) {
        if (eVar == this || eVar == null) {
            return;
        }
        int i7 = this.V0 + 1;
        e[] eVarArr = this.U0;
        if (i7 > eVarArr.length) {
            this.U0 = (e[]) Arrays.copyOf(eVarArr, eVarArr.length * 2);
        }
        e[] eVarArr2 = this.U0;
        int i8 = this.V0;
        eVarArr2[i8] = eVar;
        this.V0 = i8 + 1;
    }

    @Override // r.e
    public void n(e eVar, HashMap<e, e> map) {
        super.n(eVar, map);
        j jVar = (j) eVar;
        this.V0 = 0;
        int i7 = jVar.V0;
        for (int i8 = 0; i8 < i7; i8++) {
            c(map.get(jVar.U0[i8]));
        }
    }

    public void u1(ArrayList<o> arrayList, int i7, o oVar) {
        for (int i8 = 0; i8 < this.V0; i8++) {
            oVar.a(this.U0[i8]);
        }
        for (int i9 = 0; i9 < this.V0; i9++) {
            s.i.a(this.U0[i9], i7, arrayList, oVar);
        }
    }

    public int v1(int i7) {
        int i8;
        int i9;
        for (int i10 = 0; i10 < this.V0; i10++) {
            e eVar = this.U0[i10];
            if (i7 == 0 && (i9 = eVar.R0) != -1) {
                return i9;
            }
            if (i7 == 1 && (i8 = eVar.S0) != -1) {
                return i8;
            }
        }
        return -1;
    }
}
