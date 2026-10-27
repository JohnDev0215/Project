package a.e.b.k;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class i extends e implements h {
    public e[] w0 = new e[4];
    public int x0 = 0;

    @Override // a.e.b.k.h
    public void a(e eVar) {
        if (eVar == this || eVar == null) {
            return;
        }
        int i = this.x0 + 1;
        e[] eVarArr = this.w0;
        if (i > eVarArr.length) {
            this.w0 = (e[]) Arrays.copyOf(eVarArr, eVarArr.length * 2);
        }
        e[] eVarArr2 = this.w0;
        int i2 = this.x0;
        eVarArr2[i2] = eVar;
        this.x0 = i2 + 1;
    }

    @Override // a.e.b.k.h
    public void b(f fVar) {
    }

    @Override // a.e.b.k.h
    public void c() {
        this.x0 = 0;
        Arrays.fill(this.w0, (Object) null);
    }
}
