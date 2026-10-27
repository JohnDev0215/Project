package w9;

import n3.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends z {
    @Override // n3.z
    public final z d() {
        return (a) ((z) this.f19921b);
    }

    @Override // n3.z
    public final void f(z zVar) {
        if (!(zVar instanceof a)) {
            throw new IllegalArgumentException("Parent of block must also be block (can not be inline)");
        }
        this.f19921b = zVar;
    }
}
