package p;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class yl0 extends u1 {
    public final et1 k = new et1(2);

    @Override // p.u1
    public final Random b() {
        Object obj = this.k.get();
        v03.i(obj, "implStorage.get()");
        return (Random) obj;
    }
}
